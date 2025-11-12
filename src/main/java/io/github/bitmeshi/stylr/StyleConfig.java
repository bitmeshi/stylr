package io.github.bitmeshi.stylr;

record StyleConfig(
        BasicColor basicColor,
        BasicColor bgBasicColor,
        boolean isBold,
        boolean isDim,
        boolean isItalic,
        boolean isUnderlined,
        boolean isSlowBlink,
        boolean isRapidBlink,
        boolean isReverse,
        boolean isHide) {
}
