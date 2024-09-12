package org.example.ibroker.dto.telegram;

import lombok.Builder;
import lombok.Data;
import java.util.ArrayList;

@Data
@Builder
public class TelegramDto {
    public boolean ok;
    public ArrayList<Result> result;
}
