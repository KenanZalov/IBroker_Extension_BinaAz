package org.example.ibroker.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class GeneralResponseDto {

    private Long id;

    private String generalUrl;

    private String keyword;

    private int agent;

    private LocalDateTime searchDate;

}
