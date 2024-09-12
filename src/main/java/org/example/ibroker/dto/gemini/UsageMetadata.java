package org.example.ibroker.dto.gemini;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsageMetadata {
    private Integer promptTokenCount;
    private Integer candidatesTokenCount;
    private Integer totalTokenCount;

    @Override
    public String toString() {
        return "UsageMetadata{" +
                "promptTokenCount=" + promptTokenCount +
                ", candidatesTokenCount=" + candidatesTokenCount +
                ", totalTokenCount=" + totalTokenCount +
                '}';
    }
}