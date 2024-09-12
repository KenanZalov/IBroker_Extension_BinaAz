package org.example.ibroker.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ibroker.client.GeminiApiClient;
import org.example.ibroker.dto.gemini.Candidate;
import org.example.ibroker.dto.gemini.Root;
import org.example.ibroker.dto.request.GeneralRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DescriptionService implements GeminiService {
    private final GeminiApiClient client;

    @Value("${gemini.api.key}")
    private String key;

    private Root latestResponse;

    @Override
    public Root processUSerRequest(GeneralRequestDto dto) {
        String instruction = constructInstruction(dto);
        Root updates = getUpdates(instruction);
        String extractedText = extractedTextFromGeminiResponse(updates);
        dto.setGeminiResponse(extractedText);

        log.info("User request has been received for AI.");
        latestResponse = updates;
        return latestResponse;
    }

    @Override
    public Root getLatestResponse() {
        return latestResponse;
    }

    private String constructInstruction(GeneralRequestDto dto) {
        StringBuilder instruction = new StringBuilder();
        instruction.append("Chat ID: ").append(dto.getChatId()).append("\n");
        instruction.append("General URL: ").append(dto.getChatId()).append("\n");
        instruction.append("Keyword: ").append(dto.getKeyword()).append("\n");
        instruction.append("Agent: ").append(dto.getAgent()).append("\n");
        instruction.append("Date: ").append(dto.getAgent()).append("\n");
        instruction.append("Please check the announcement of buildings in \"https://bina.az/\"." +
                " Check the description of announcement, which is link's coming from" +
                " \"General URL\". If it says in" +
                " Azerbaijani Language tecili, təcili or something like that, give response. " +
                "Give response in Azerbaijan Language.");
        return instruction.toString();
    }

    private Root getUpdates(String instruction) {
        try {
            JsonObject json = new JsonObject();
            JsonArray contentsArray = new JsonArray();
            JsonObject contentsObject = new JsonObject();
            JsonArray partsArray = new JsonArray();
            JsonObject partsObject = new JsonObject();

            partsObject.add("text", new JsonPrimitive(instruction));
            partsArray.add(partsObject);
            contentsObject.add("parts", partsArray);
            contentsArray.add(contentsObject);
            json.add("contents", contentsArray);

            String content = json.toString();

            return client.getData(key, content);
        } catch (Exception e) {
            log.error("Error while getting response from Gemini AI: ", e);
            throw e;
        }
    }

    private String extractedTextFromGeminiResponse(Root updates) {
        StringBuilder textBuilder = new StringBuilder();

        if (updates.getCandidates() != null) {
            for (Candidate candidate : updates.getCandidates()) {
                String text = candidate.getContent().getParts().get(0).getText();
                text = text.replace("*", "");
                textBuilder.append(text).append("\n\n");
            }
        }

        String response = textBuilder.toString().trim();

        return response
                .replaceAll("(?i)\\bChat ID:\\b", "\nChat ID:\n")
                .replaceAll("(?i)\\bGeneral URL:\\b", "\nGeneral URL:\n")
                .replaceAll("(?i)\\bKeyword:\\b", "\nKeyword:\n")
                .replaceAll("(?i)\\bAgent:\\b", "\nAgent:\n")
                .replaceAll("(?i)\\bDate:\\b", "\nDate:\n")
                .replaceAll("(?i)\\bDate:\\b", "\nDate:\n");
    }
}
