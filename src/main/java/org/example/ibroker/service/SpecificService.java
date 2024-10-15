package org.example.ibroker.service;

import lombok.RequiredArgsConstructor;
import org.example.ibroker.client.TelegramFeignClient;
import org.example.ibroker.dto.request.SpecificRequestDto;
import org.example.ibroker.dto.response.SpecificResponseDto;
import org.example.ibroker.entity.SpecificSearch;
import org.example.ibroker.repository.SpecificRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecificService {
    private final TelegramFeignClient telegramClient;
    private final SpecificRepository specificRepository;
    private final ModelMapper modelMapper;

    public String saveDetails(SpecificRequestDto specificRequestDto) {
        SpecificSearch specificSearch = modelMapper.map(specificRequestDto, SpecificSearch.class);
        specificRepository.save(specificSearch);
        return "Saved";
    }


    public List<SpecificResponseDto> getDetails() {
        return specificRepository
                .findAll()
                .stream()
                .map(d -> modelMapper.map(d, SpecificResponseDto.class))
                .toList();
    }

    public String deleteDetails(Long chatId) {
        specificRepository.deleteById(chatId);
        return "Deleted";
    }



    @Scheduled(fixedDelay = 300000)
    public void getUpdates() throws IOException {
        List<SpecificSearch> details = specificRepository.findAll();
        for (SpecificSearch specificSearch : details) {
            Document doc = Jsoup.connect(specificSearch.getSpecificUrl()).get();
            Element price = doc.getElementsByClass("price").first();
            assert price != null;
            String priceText = price.html();
            String number = priceText.replaceAll(" ", "");
            int priceValue = Integer.parseInt(number);
            if (priceValue < specificSearch.getCurrentPrice()){
                telegramClient.sendMessage(specificSearch.getChatId(), "Qiymət endi: \n" + specificSearch.getSpecificUrl());
            }
        }
        }



}
