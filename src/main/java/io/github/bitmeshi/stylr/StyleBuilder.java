package io.github.bitmeshi.stylr;

import java.util.Objects;

public final class StyleBuilder {
    private final String text;
    private BasicColor basicColor;
    private BasicColor bgBasicColor;
    private boolean isBold;
    private boolean isDim;
    private boolean isItalic;
    private boolean isUnderlined;
    private boolean isSlowBlink;
    private boolean isRapidBlink;
    private boolean isReverse;
    private boolean isHide;

    public StyleBuilder(String text) {
        this.text = text;
    }

    public StyleBuilder color(BasicColor basicColor) {
        Objects.requireNonNull(basicColor, "BasicColor cannot be null");
        this.basicColor = basicColor;
        return this;
    }

    public StyleBuilder bgColor(BasicColor basicColor) {
        Objects.requireNonNull(basicColor, "Background BasicColor cannot be null");
        this.bgBasicColor = basicColor;
        return this;
    }

    public StyleBuilder bold() {
        this.isBold = true;
        return this;
    }

    public StyleBuilder dim() {
        this.isDim = true;
        return this;
    }

    public StyleBuilder italic() {
        this.isItalic = true;
        return this;
    }

    public StyleBuilder underlined() {
        this.isUnderlined = true;
        return this;
    }

    public StyleBuilder slowBlink() {
        this.isSlowBlink = true;
        return this;
    }

    public StyleBuilder rapidBlink() {
        this.isRapidBlink = true;
        return this;
    }

    public StyleBuilder reverse() {
        this.isReverse = true;
        return this;
    }

    public StyleBuilder hide() {
        this.isHide = true;
        return this;
    }

    public String render() {
        StyleConfig config = new StyleConfig(
                this.basicColor,
                this.bgBasicColor,
                this.isBold,
                this.isDim,
                this.isItalic,
                this.isUnderlined,
                this.isSlowBlink,
                this.isRapidBlink,
                this.isReverse,
                this.isHide
        );

        String ansiPrefix = AnsiCodeGenerator.getAnsiPrefix(config);

        if (text.isEmpty()) {
            return "";
        }

        return ansiPrefix + text + "\u001b[0m";
    }
}
