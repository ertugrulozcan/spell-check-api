package org.dyg.controllers;

import org.dyg.models.NormalizeResponse;
import org.dyg.models.TextRequest;
import org.dyg.services.NormalizeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/normalize")
public class NormalizeController
{
    private final NormalizeService service;

    public NormalizeController(NormalizeService service)
    {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<NormalizeResponse> normalize(@Validated @RequestBody TextRequest req)
    {
        return ResponseEntity.ok(new NormalizeResponse(service.normalize(req.text())));
    }
}