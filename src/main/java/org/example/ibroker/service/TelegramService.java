package org.example.ibroker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ibroker.client.TelegramFeignClient;
import org.example.ibroker.dto.telegram.Result;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class TelegramService {

    private final TelegramFeignClient telegramFeignClient;
    private Long offset = 0L;

    @Scheduled(fixedDelay = 2000)
    public void sendMessage() {
        for (Result r : telegramFeignClient.getUpdates(offset + 1).getResult()) {
            if ("/start".equals(r.getMessage().getText())) {
                long chatId = r.getMessage().getChat().id;
                telegramFeignClient.sendMessage(chatId,"Sizin identifikasiya nömrəniz : "+ chatId);
            }
            offset = r.updateId;
        }
    }


}
