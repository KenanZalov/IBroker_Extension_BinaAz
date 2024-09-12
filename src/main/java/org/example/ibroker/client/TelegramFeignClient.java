package org.example.ibroker.client;

import org.example.ibroker.dto.telegram.TelegramDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "telegram-service", url = "https://api.telegram.org/bot7365899762:AAHyGSrZcgYOBP24pr5Taf-m9TP2y7ld9Ik")
public interface TelegramFeignClient {

    @GetMapping("/getUpdates")
    TelegramDto getUpdates(@RequestParam(name = "offset") Long offset);

    @PostMapping("/sendMessage")
    void sendMessage(@RequestParam(name = "chat_id") long chatId,
                     @RequestParam(name = "text") String text
    );
}
