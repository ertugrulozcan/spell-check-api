package org.dyg.models;

import java.util.List;

public record TokenResponse(List<Token> tokens)
{
    public static record Token(String surface, String type, int start, int end) {}
}