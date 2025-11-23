package org.dyg.services;

import org.springframework.stereotype.Service;
import zemberek.morphology.TurkishMorphology;
import zemberek.morphology.analysis.SingleAnalysis;
import zemberek.morphology.analysis.WordAnalysis;

import java.util.ArrayList;
import java.util.List;

@Service
public class MorphologyService
{
    private final TurkishMorphology morphology;

    public MorphologyService(TurkishMorphology morphology)
    {
        this.morphology = morphology;
    }

    public List<MorphResult> analyze(String input)
    {
        WordAnalysis wa = morphology.analyze(input);
        List<MorphResult> results = new ArrayList<>();
        for (SingleAnalysis sa : wa)
        {
            results.add(new MorphResult(
                    sa.getStem(),
                    sa.formatLong(),
                    sa.getMorphemeDataList().stream().map(m -> m.morpheme.id).toList()
            ));
        }

        return results;
    }

    public record MorphResult(String lemma, String formatted, List<String> morphemes) {}
}