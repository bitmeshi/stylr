package io.github.bitmeshi.stylr;

import java.util.Objects;

public final class Style {
    private static final String ANSI_RESET = "\u001b[0m";
    private final String ansiPrefix;

    public Style(String ansiPrefix) {
        this.ansiPrefix = ansiPrefix;
    }

    public String apply(String text) {
        Objects.requireNonNull(text, "Text cannot be null");

        if (text.isEmpty()) return "";

        return ansiPrefix + text + ANSI_RESET;
    }
}
