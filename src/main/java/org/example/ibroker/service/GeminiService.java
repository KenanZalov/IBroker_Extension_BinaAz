package org.example.ibroker.service;

import org.example.ibroker.dto.gemini.Root;
import org.example.ibroker.dto.request.GeneralRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface GeminiService {
    Root processUSerRequest(GeneralRequestDto dto);

    Root getLatestResponse();
}
