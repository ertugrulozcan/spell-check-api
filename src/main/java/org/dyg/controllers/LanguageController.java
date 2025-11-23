package org.dyg.controllers;

import org.dyg.models.LanguageResponse;
import org.dyg.models.TextRequest;
import org.dyg.services.LanguageService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/languages")
public class LanguageController
{
    private final LanguageService service;

    public LanguageController(LanguageService service)
    {
        this.service = service;
    }

    @PostMapping("/detect")
    public ResponseEntity<LanguageResponse> detect(@Validated @RequestBody TextRequest req)
    {
        String lang = service.detect(req.text());
        double conf = service.confidence(req.text());
        return ResponseEntity.ok(new LanguageResponse(lang, conf));
    }
}