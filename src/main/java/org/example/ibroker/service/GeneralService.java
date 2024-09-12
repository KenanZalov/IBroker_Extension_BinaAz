package org.example.ibroker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ibroker.client.TelegramFeignClient;
import org.example.ibroker.dto.request.GeneralRequestDto;
import org.example.ibroker.dto.response.GeneralResponseDto;
import org.example.ibroker.entity.GeneralSearch;
import org.example.ibroker.repository.GeneralRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GeneralService {

    private final GeneralRepository generalRepository;
    private final ModelMapper modelMapper;
    private final TelegramFeignClient telegramFeignClient;

    public String saveSearchDetails(GeneralRequestDto generalRequestDto) {
        GeneralSearch generalSearch = modelMapper.map(generalRequestDto, GeneralSearch.class);
        generalSearch.setSearchDate(LocalDateTime.now());
        generalRepository.save(generalSearch);
        return "Saved";
    }

    public List<GeneralResponseDto> getSearchDetails() {
        return generalRepository
                .findAll()
                .stream()
                .map(d -> modelMapper.map(d, GeneralResponseDto.class))
                .toList();
    }

    public String deleteSearchDetails(Long chatId) {
        generalRepository.deleteById(chatId);
        return "Deleted";
    }

    public static boolean isTimeAfter(String timeString, LocalDateTime dateTime) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime parsedTime = LocalTime.parse(timeString, timeFormatter);
        LocalTime timeFromDateTime = dateTime.toLocalTime();
        return parsedTime.isAfter(timeFromDateTime);
    }



    @Scheduled(fixedDelay = 6000)
    public void getFilters() throws IOException {
        List<GeneralSearch> searchDetails = generalRepository.findAll();
        for (GeneralSearch search : searchDetails) {
            Document doc = Jsoup.connect(search.getGeneralUrl()).get();
            Elements elements = doc.getElementsByClass("items-i");
            for (Element element : elements) {
                Element broker = element.getElementsByClass("products-label").first();
                if (broker == null || search.getAgent() == 1) {
                    Element time = element.getElementsByClass("city_when").first();
                    assert time != null;
                    String timeText = time.text();
                    if (timeText.contains("bugün")){
                        String second = timeText.split(",")[1].trim();
                        String result = second.split(" ")[1];
                        if (isTimeAfter(result, search.getSearchDate())) {
                            Element description = element.getElementsByClass("product-description__content").first();
                            if (description != null) {
                                String link = element.getElementsByClass ("item_link").first().attr("href");
                                String cleanLink = link.substring(0,13);
                                String baseUrl = "https://bina.az";
                                StringBuilder fullUrl = new StringBuilder();
                                fullUrl.append(baseUrl).append(cleanLink);
                                telegramFeignClient.sendMessage(search.getChatId(),"Yeni elan: \n" + fullUrl.toString() );

                            }
                        }
                    }
                }



            }
        }
    }

    @Scheduled(fixedDelay = 6000)
    public void testFilters() throws IOException {
        Document doc = Jsoup.connect("https://bina.az").get();
        Elements elements = doc.getElementsByClass("items-i");
        for (Element element : elements) {
            Element time = element.getElementsByClass("city_when").first();

            String link = element.getElementsByClass ("item_link").first().attr("href");
            String linkR = link.substring(0,13);
            log.info("{}", linkR);


//            assert time != null;
//            String timeText = time.text();
//            if (timeText.contains("bugün")){
//                String second = timeText.split(",")[1].trim();
//                String result = second.split(" ")[1];
//                System.out.println(result);
//            }





        }
}











}
