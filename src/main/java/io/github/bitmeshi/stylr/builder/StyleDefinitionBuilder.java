package io.github.bitmeshi.stylr.builder;

import io.github.bitmeshi.stylr.Style;
import io.github.bitmeshi.stylr.internal.AnsiCodeGenerator;

public final class StyleDefinitionBuilder extends BaseStyleBuilder<StyleDefinitionBuilder> {
    @Override
    protected StyleDefinitionBuilder self() {
        return this;
    }

    public Style build() {
        String ansiPrefix = AnsiCodeGenerator.getAnsiPrefix(buildStyleConfig());
        return new Style(ansiPrefix);
    }
}
