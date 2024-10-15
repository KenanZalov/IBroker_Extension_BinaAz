package org.example.ibroker.dto.gemini.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class GeminiRequest {
    @JsonProperty
    private final ArrayList<RequestContent> contents;

    public GeminiRequest(String text) {
        contents = new ArrayList<>();
        contents.add(new RequestContent(text));
    }

    static class RequestContent {
        @JsonProperty
        private final ArrayList<RequestPart> parts;

        public RequestContent(String text) {
            parts = new ArrayList<>();
            parts.add(new RequestPart(text));
        }

        static class RequestPart {
            @JsonProperty
            private final String text;

            public RequestPart(String text) {
                this.text = text;
            }
        }
    }
}