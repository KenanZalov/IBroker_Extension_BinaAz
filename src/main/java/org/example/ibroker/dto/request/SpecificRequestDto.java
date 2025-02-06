package org.example.ibroker.dto.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SpecificRequestDto {

    private Long chatId;

    private String specificUrl;

    private Integer currentPrice;
}
