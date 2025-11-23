package org.dyg.models;

import jakarta.validation.constraints.NotBlank;

public record TextRequest(@NotBlank String text) {}