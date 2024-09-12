package org.example.ibroker.dto.gemini;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class Candidate {
    private Content content;
    private String finishReason;
    private Integer index;
    private ArrayList<SafetyRating> safetyRatings;

    @Override
    public String toString() {
        return "Candidate{" +
                "content=" + content +
                ", finishReason='" + finishReason + '\'' +
                ", index=" + index +
                ", safetyRatings=" + safetyRatings +
                '}';
    }
}