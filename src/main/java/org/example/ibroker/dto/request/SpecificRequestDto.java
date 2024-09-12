package org.example.ibroker.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SpecificRequestDto {

    private Long chatId;

    private String specificUrl;

    private Integer currentPrice;
}
