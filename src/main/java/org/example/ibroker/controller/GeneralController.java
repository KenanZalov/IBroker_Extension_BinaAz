package org.example.ibroker.controller;

import lombok.RequiredArgsConstructor;
import org.example.ibroker.dto.request.GeneralRequestDto;
import org.example.ibroker.dto.response.GeneralResponseDto;
import org.example.ibroker.service.GeneralService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/general")
public class GeneralController {

    private final GeneralService generalService;

    @PostMapping("/saveSearchDetails")
    public String saveSearchDetails (@RequestBody GeneralRequestDto generalRequestDto) {
        return generalService.saveSearchDetails(generalRequestDto);
    }

    @GetMapping("/getSearchDetails")
    public List<GeneralResponseDto> getSearchDetails () {
        return generalService.getSearchDetails();
    }

    @DeleteMapping("/deleteSearchDetails")
    public String deleteSearchDetails (@RequestParam Long chatId) {
        return generalService.deleteSearchDetails(chatId);
    }




}



