package org.example.ibroker.dto.request;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class GeneralRequestDto {

    private Long chatId;

    private String generalUrl;

    private String keyword;

    private int agent;

}
