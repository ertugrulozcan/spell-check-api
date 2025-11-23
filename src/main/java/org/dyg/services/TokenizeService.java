package org.dyg.services;

import org.springframework.stereotype.Service;
import zemberek.tokenization.Token;
import zemberek.tokenization.TurkishTokenizer;

import java.util.List;

@Service
public class TokenizeService
{
    private final TurkishTokenizer tokenizer;

    public TokenizeService(TurkishTokenizer tokenizer)
    {
        this.tokenizer = tokenizer;
    }

    public List<Token> tokenize(String text)
    {
        return tokenizer.tokenize(text);
    }
}