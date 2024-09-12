package org.example.ibroker.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class From {
    public int id;
    @JsonProperty("is_bot")
    public boolean isBot;
    @JsonProperty("first_name")
    public String firstName;
    public String username;
    @JsonProperty("language_code")
    public String languageCode;

}
