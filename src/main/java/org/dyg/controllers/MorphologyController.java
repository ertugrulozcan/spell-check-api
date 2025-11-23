package org.dyg.controllers;

import org.dyg.models.MorphResponse;
import org.dyg.models.TextRequest;
import org.dyg.services.MorphologyService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/morphology")
public class MorphologyController
{
    private final MorphologyService service;

    public MorphologyController(MorphologyService service)
    {
        this.service = service;
    }

    @PostMapping("/analyze")
    public ResponseEntity<MorphResponse> analyze(@Validated @RequestBody TextRequest req)
    {
        var analyses = service.analyze(req.text()).stream()
            .map(a -> new MorphResponse.AnalysisResult(a.lemma(), a.formatted(), a.morphemes()))
            .toList();

        return ResponseEntity.ok(new MorphResponse(req.text(), analyses));
    }
}