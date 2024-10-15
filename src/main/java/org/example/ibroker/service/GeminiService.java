package org.example.ibroker.service;

import lombok.RequiredArgsConstructor;
import org.example.ibroker.client.GeminiApiClient;
import org.example.ibroker.dto.gemini.Root;
import org.example.ibroker.dto.gemini.request.GeminiRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeminiService {
    @Value("${gemini.api.key}")
    private String key;
    private final GeminiApiClient gemini;

    public boolean analyze (String keywords, String description) {
        String prompt = """
                Here is Please check the description of the announcement of building:
                %s
                Here is the keywords that user wants to be considered as a filter:
                %s
                Analyze the announcement and if it is suitable for the user answer only \"yes\" or \"no\"""".formatted(description, keywords);
        Root response = gemini.getData(key, new GeminiRequest(prompt));
        return response.getCandidates().get(0).getContent().getParts().get(0).getText().contains("yes");
    }
}
