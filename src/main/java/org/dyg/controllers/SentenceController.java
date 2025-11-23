package org.dyg.controllers;

import org.dyg.models.SentenceResponse;
import org.dyg.models.TextRequest;
import org.dyg.services.SentenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sentence")
public class SentenceController
{
    private final SentenceService service;

    public SentenceController(SentenceService service)
    {
        this.service = service;
    }

    @PostMapping("/split")
    public ResponseEntity<SentenceResponse> split(@Validated @RequestBody TextRequest req)
    {
        return ResponseEntity.ok(new SentenceResponse(service.sentences(req.text())));
    }
}