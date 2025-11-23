package org.dyg.controllers;

import org.dyg.models.TextRequest;
import org.dyg.models.TokenResponse;
import org.dyg.services.TokenizeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tokenize")
public class TokenizeController
{
    private final TokenizeService service;

    public TokenizeController(TokenizeService service)
    {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TokenResponse> tokenize(@Validated @RequestBody TextRequest req)
    {
        var tokens = service.tokenize(req.text()).stream()
            .map(t -> new TokenResponse.Token(t.content, t.type.name(), t.start, t.end))
            .toList();

        return ResponseEntity.ok(new TokenResponse(tokens));
    }
}