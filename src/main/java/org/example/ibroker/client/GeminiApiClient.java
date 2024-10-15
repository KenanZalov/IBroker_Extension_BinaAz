package org.example.ibroker.client;

import org.example.ibroker.dto.gemini.Root;
import org.example.ibroker.dto.gemini.request.GeminiRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "geminiApiClient", url = "https://generativelanguage.googleapis.com/v1beta")
public interface GeminiApiClient {

    @PostMapping("/models/gemini-1.5-flash-latest:generateContent")
    Root getData(@RequestParam("key") String apiKey, @RequestBody GeminiRequest content);
}