package org.dyg.controllers;

import org.dyg.services.SpellService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spellcheck")
public class SpellController {
    private final SpellService spellService;

    public SpellController(SpellService spellService)
    {
        this.spellService = spellService;
    }

    @PostMapping
    public ResponseEntity<?> check(@Validated @RequestBody Map<String, String> body)
    {
        if (body.containsKey("word"))
        {
            String word = body.get("word");
            boolean correct = spellService.isCorrect(word);
            List<String> suggestions = correct ? List.of() : spellService.suggestions(word);
            return ResponseEntity.ok(Map.of(
                    "input", word,
                    "correct", correct,
                    "suggestions", suggestions
            ));
        }
        else if (body.containsKey("text"))
        {
            String text = body.get("text");
            List<Map<String, Object>> results = spellService.checkSentence(text);
            return ResponseEntity.ok(results);
        }
        else
        {
            return ResponseEntity.badRequest().body("Either 'word' or 'text' must be provided.");
        }
    }
}