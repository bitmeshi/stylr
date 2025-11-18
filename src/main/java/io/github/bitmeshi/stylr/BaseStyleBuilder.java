package io.github.bitmeshi.stylr;

import java.util.Objects;

public abstract sealed class BaseStyleBuilder<T extends BaseStyleBuilder<T>> permits StyleDefinitionBuilder, TextStyler {
    protected BasicColor basicColor;
    protected BasicColor bgBasicColor;
    protected Rgb rgbColor;
    protected Rgb bgRgbColor;
    protected boolean isBold;
    protected boolean isDim;
    protected boolean isItalic;
    protected boolean isUnderlined;
    protected boolean isSlowBlink;
    protected boolean isRapidBlink;
    protected boolean isReverse;
    protected boolean isHide;

    protected abstract T self();

    public T color(BasicColor basicColor) {
        Objects.requireNonNull(basicColor, "color cannot be null");
        this.basicColor = basicColor;
        rgbColor = null;
        return self();
    }

    public T color(int r, int g, int b) {
        rgbColor = new Rgb(r, g, b);
        basicColor = null;
        return self();
    }

    public T color(String hexadecimal) {
        Objects.requireNonNull(hexadecimal, "color cannot be null");
        rgbColor = Rgb.fromHex(hexadecimal);
        basicColor = null;
        return self();
    }

    public T bgColor(BasicColor basicColor) {
        Objects.requireNonNull(basicColor, "bgColor cannot be null");
        bgBasicColor = basicColor;
        bgRgbColor = null;
        return self();
    }

    public T bgColor(int r, int g, int b) {
        bgRgbColor = new Rgb(r, g, b);
        bgBasicColor = null;
        return self();
    }

    public T bgColor(String hexadecimal) {
        Objects.requireNonNull(hexadecimal, "bgColor cannot be null");
        bgRgbColor = Rgb.fromHex(hexadecimal);
        bgBasicColor = null;
        return self();
    }

    public T bold() {
        isBold = true;
        return self();
    }

    public T dim() {
        isDim = true;
        return self();
    }

    public T italic() {
        isItalic = true;
        return self();
    }

    public T underlined() {
        isUnderlined = true;
        return self();
    }

    public T slowBlink() {
        isSlowBlink = true;
        return self();
    }

    public T rapidBlink() {
        isRapidBlink = true;
        return self();
    }

    public T reverse() {
        isReverse = true;
        return self();
    }

    public T hide() {
        isHide = true;
        return self();
    }

    protected StyleConfig buildStyleConfig() {
        return new StyleConfig(
            basicColor,
            bgBasicColor,
            rgbColor,
            bgRgbColor,
            isBold,
            isDim,
            isItalic,
            isUnderlined,
            isSlowBlink,
            isRapidBlink,
            isReverse,
            isHide
        );
    }
}
