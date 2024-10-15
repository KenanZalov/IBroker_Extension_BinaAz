package org.example.ibroker.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
public class GeneralRequestDto {

    private Long chatId;

    private String generalUrl;

    private String keyword;

    private int agent;

}
