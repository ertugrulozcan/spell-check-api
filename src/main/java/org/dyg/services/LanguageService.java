package org.dyg.services;

import org.springframework.stereotype.Service;
import zemberek.langid.LanguageIdentifier;

@Service
public class LanguageService
{
    private final LanguageIdentifier languageIdentifier;

    public LanguageService(LanguageIdentifier languageIdentifier)
    {
        this.languageIdentifier = languageIdentifier;
    }

    public String detect(String text)
    {
        return languageIdentifier.identify(text);
    }

    public double confidence(String text)
    {
        if (text == null)
        {
            return 0.0;
        }

        String trimmed = text.trim();
        if (trimmed.length() < 5)
        {
            return 0.3;
        }

        boolean hasTurkishChars = trimmed.matches(".*[çğıöşüÇĞİÖŞÜ].*");
        double base = hasTurkishChars ? 0.7 : 0.6;

        double lengthBoost = Math.min(0.3, Math.log10(trimmed.length()) / 10.0);
        return Math.min(1.0, base + lengthBoost);
    }
}