package org.example.ibroker.controller;

import lombok.RequiredArgsConstructor;
import org.example.ibroker.dto.request.GeneralRequestDto;
import org.example.ibroker.dto.request.SpecificRequestDto;
import org.example.ibroker.dto.response.SpecificResponseDto;
import org.example.ibroker.service.SpecificService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/specific")
public class SpecificController {

    private final SpecificService specificService;

    @PostMapping("/saveDetails")
    public String saveDetails (@RequestBody SpecificRequestDto specificRequestDto) {
        return specificService.saveDetails(specificRequestDto);
    }

    @GetMapping("/getDetails")
    public List<SpecificResponseDto> getDetails () {
        return specificService.getDetails();
    }

    @DeleteMapping("/deleteDetails")
    public String deleteDetails (@RequestParam Long chatId) {
        return specificService.deleteDetails(chatId);
    }

}
