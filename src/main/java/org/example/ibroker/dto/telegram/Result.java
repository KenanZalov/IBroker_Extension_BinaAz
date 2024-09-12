package org.example.ibroker.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result {
    @JsonProperty("update_id")
    public Long updateId;
    public Message message;
}
