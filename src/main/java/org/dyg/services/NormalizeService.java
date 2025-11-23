package org.dyg.services;

import org.springframework.stereotype.Service;
import zemberek.normalization.TurkishSentenceNormalizer;

@Service
public class NormalizeService
{
    private final TurkishSentenceNormalizer normalizer;

    public NormalizeService(TurkishSentenceNormalizer normalizer)
    {
        this.normalizer = normalizer;
    }

    public String normalize(String text)
    {
        return normalizer.normalize(text);
    }
}