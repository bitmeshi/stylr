package io.github.bitmeshi.stylr;

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
