package io.github.bitmeshi.stylr;

import java.util.Objects;

public final class StyleBuilder {
    private final String text;
    private BasicColor basicColor;
    private BasicColor bgBasicColor;
    private Rgb rgbColor;
    private Rgb bgRgbColor;
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
        Objects.requireNonNull(basicColor, "color cannot be null");
        this.basicColor = basicColor;
        this.rgbColor = null;
        return this;
    }

    public StyleBuilder color(int r, int g, int b) {
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
            throw new IllegalArgumentException("RGB values must be between 0 and 255");
        }
        this.rgbColor = new Rgb(r, g, b);
        this.basicColor = null;
        return this;
    }

    public StyleBuilder color(String hexadecimal) {
        Objects.requireNonNull(hexadecimal, "color cannot be null");
        this.rgbColor = Rgb.fromHex(hexadecimal);
        this.basicColor = null;
        return this;
    }

    public StyleBuilder bgColor(BasicColor basicColor) {
        Objects.requireNonNull(basicColor, "bgColor cannot be null");
        this.bgBasicColor = basicColor;
        this.bgRgbColor = null;
        return this;
    }

    public StyleBuilder bgColor(int r, int g, int b) {
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
            throw new IllegalArgumentException("RGB values must be between 0 and 255");
        }
        this.bgRgbColor = new Rgb(r, g, b);
        this.bgBasicColor = null;
        return this;
    }

    public StyleBuilder bgColor(String hexadecimal) {
        Objects.requireNonNull(hexadecimal, "bgColor cannot be null");
        this.bgRgbColor = Rgb.fromHex(hexadecimal);
        this.bgBasicColor = null;
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
                this.rgbColor,
                this.bgRgbColor,
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
