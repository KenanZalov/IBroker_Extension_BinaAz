package org.example.ibroker.dto.gemini;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class Content {
    private ArrayList<Part> parts;
    private String role;

    @Override
    public String toString() {
        return "Content{" +
                "parts=" + parts +
                ", role='" + role + '\'' +
                '}';
    }
}