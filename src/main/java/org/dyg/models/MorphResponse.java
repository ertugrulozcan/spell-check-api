package org.dyg.models;

import java.util.List;

public record MorphResponse(String input, List<AnalysisResult> analyses)
{
    public static record AnalysisResult(String lemma, String formatted, List<String> morphemes) {}
}