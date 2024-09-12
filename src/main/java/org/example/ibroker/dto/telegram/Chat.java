package org.example.ibroker.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Chat {
    public int id;
    @JsonProperty("first_name")
    public String firstName;
    public String username;
    public String type;

}
