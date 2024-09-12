package org.example.ibroker.controller;

import lombok.RequiredArgsConstructor;
import org.example.ibroker.dto.gemini.Root;
import org.example.ibroker.dto.request.GeneralRequestDto;
import org.example.ibroker.service.DescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/description")
@RequiredArgsConstructor
public class DescriptionController {
    private final DescriptionService service;

    @PostMapping("/getResponse")
    public ResponseEntity<Root> getResponse(@RequestBody GeneralRequestDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.processUSerRequest(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/latestResponse")
    public ResponseEntity<Root> getLatestResponse() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getLatestResponse());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
