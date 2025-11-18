package io.github.bitmeshi.stylr.builder;

import io.github.bitmeshi.stylr.internal.AnsiCodeGenerator;

public final class TextStyler extends BaseStyleBuilder<TextStyler> {
    private final String text;

    public TextStyler(String text) {
        this.text = text;
    }

    @Override
    protected TextStyler self() {
        return this;
    }

    public String render() {
        String ansiPrefix = AnsiCodeGenerator.getAnsiPrefix(buildStyleConfig());

        if (text.isEmpty()) return "";

        return ansiPrefix + text + "\u001b[0m";
    }
}
