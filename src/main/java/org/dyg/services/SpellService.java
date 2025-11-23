package org.dyg.services;

import org.springframework.stereotype.Service;
import zemberek.morphology.TurkishMorphology;
import zemberek.normalization.TurkishSpellChecker;
import zemberek.tokenization.Token;
import zemberek.tokenization.TurkishTokenizer;

import java.io.IOException;
import java.util.*;

@Service
public class SpellService
{
    private final TurkishSpellChecker spellChecker;
    private final TurkishTokenizer tokenizer;

    public SpellService(TurkishMorphology morphology) throws IOException
    {
        this.spellChecker = new TurkishSpellChecker(morphology);
        this.tokenizer = TurkishTokenizer.DEFAULT;
    }

    public boolean isCorrect(String word)
    {
        return spellChecker.check(word);
    }

    public List<String> suggestions(String word)
    {
        return spellChecker.suggestForWord(word);
    }

    public List<Map<String, Object>> checkSentence(String sentence)
    {
        List<Map<String, Object>> results = new ArrayList<>();
        for (Token token : tokenizer.tokenize(sentence))
        {
            String word = token.content;
            boolean correct = spellChecker.check(word);
            List<String> suggestions = correct ? List.of() : spellChecker.suggestForWord(word);
            results.add(Map.of(
                    "word", word,
                    "correct", correct,
                    "suggestions", suggestions
            ));
        }

        return results;
    }
}