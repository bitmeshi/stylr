package io.github.bitmeshi.stylr.internal;

import io.github.bitmeshi.stylr.BasicColor;
import io.github.bitmeshi.stylr.internal.color.Rgb;

public record StyleConfig(
        BasicColor basicColor,
        BasicColor bgBasicColor,
        Rgb rgbColor,
        Rgb bgRgbColor,
        boolean isBold,
        boolean isDim,
        boolean isItalic,
        boolean isUnderlined,
        boolean isSlowBlink,
        boolean isRapidBlink,
        boolean isReverse,
        boolean isHide) {
}
