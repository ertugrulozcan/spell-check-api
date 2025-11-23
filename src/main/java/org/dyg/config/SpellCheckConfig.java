package org.dyg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zemberek.morphology.TurkishMorphology;
import zemberek.langid.LanguageIdentifier;
import zemberek.normalization.TurkishSentenceNormalizer;
import zemberek.tokenization.TurkishTokenizer;

import java.io.IOException;
import java.nio.file.Path;

@Configuration
public class SpellCheckConfig
{
    @Bean
    public TurkishMorphology morphology()
    {
        return TurkishMorphology.createWithDefaults();
    }

    @Bean
    public TurkishTokenizer tokenizer()
    {
        return TurkishTokenizer.ALL;
    }

    @Bean
    public LanguageIdentifier languageIdentifier() throws IOException
    {
        return LanguageIdentifier.fromInternalModels();
    }

    @Bean
    public TurkishSentenceNormalizer normalizer(TurkishMorphology morphology) throws IOException
    {
        var lookupRoot = Path.of("/app/normalization/data");
        var lmDir = Path.of("/app/normalization/lm/lm.2gram.slm");
        return new TurkishSentenceNormalizer(morphology, lookupRoot, lmDir);
    }
}