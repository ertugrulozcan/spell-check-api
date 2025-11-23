package org.dyg.models;

import java.util.List;

public record SpellResponse(boolean correct, List<String> suggestions) {}