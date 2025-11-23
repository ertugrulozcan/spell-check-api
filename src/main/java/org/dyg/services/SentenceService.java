package org.dyg.services;

import org.springframework.stereotype.Service;
import zemberek.tokenization.TurkishSentenceExtractor;

import java.util.List;

@Service
public class SentenceService
{
    private final TurkishSentenceExtractor extractor;

    public SentenceService()
    {
        this.extractor = TurkishSentenceExtractor.DEFAULT;
    }

    public List<String> sentences(String text)
    {
        return extractor.fromParagraph(text);
    }
}