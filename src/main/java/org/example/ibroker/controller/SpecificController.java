package org.example.ibroker.controller;

import lombok.RequiredArgsConstructor;
import org.example.ibroker.dto.request.SpecificRequestDto;
import org.example.ibroker.dto.response.SpecificResponseDto;
import org.example.ibroker.service.SpecificService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/specific")
public class SpecificController {

    private final SpecificService specificService;

    @PostMapping("/saveDetails")
    public ResponseEntity<String> saveDetails (@RequestBody SpecificRequestDto specificRequestDto) {
        return ResponseEntity.ok(specificService.saveDetails(specificRequestDto));
    }

    @GetMapping("/getDetails")
    public ResponseEntity<List<SpecificResponseDto>> getDetails () {
        return ResponseEntity.ok(specificService.getDetails());
    }

    @DeleteMapping("/deleteDetails")
    public ResponseEntity<String> deleteDetails (@RequestParam Long chatId) {
        return ResponseEntity.ok(specificService.deleteDetails(chatId));
    }

}
