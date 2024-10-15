package org.example.ibroker.controller;

import lombok.RequiredArgsConstructor;
import org.example.ibroker.dto.request.GeneralRequestDto;
import org.example.ibroker.dto.response.GeneralResponseDto;
import org.example.ibroker.service.GeneralService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/general")
public class GeneralController {

    private final GeneralService generalService;

    @PostMapping("/saveSearchDetails")
    public ResponseEntity<String> saveSearchDetails (@RequestBody GeneralRequestDto generalRequestDto) {
        return ResponseEntity.ok(generalService.saveSearchDetails(generalRequestDto));
    }

    @GetMapping("/getSearchDetails")
    public ResponseEntity<List<GeneralResponseDto>> getSearchDetails () {
        return ResponseEntity.ok(generalService.getSearchDetails());
    }

    @DeleteMapping("/deleteSearchDetails")
    public ResponseEntity<String> deleteSearchDetails (@RequestParam Long chatId) {
        return ResponseEntity.ok(generalService.deleteSearchDetails(chatId));
    }




}



