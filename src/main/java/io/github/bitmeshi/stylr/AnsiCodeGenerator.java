package io.github.bitmeshi.stylr;

import java.util.StringJoiner;

final class AnsiCodeGenerator {
    public static String getAnsiPrefix(StyleConfig config) {
        StringJoiner ansiPrefix = new StringJoiner(";", "\u001b[", "m");

        String colorPrefix = getColorTextPrefix(config);
        String bgColorPrefix = getColorBackgroundPrefix(config);
        String attributePrefix = getAttributePrefix(config);

        if (colorPrefix.isEmpty() && bgColorPrefix.isEmpty() && attributePrefix.isEmpty()) {
            return "";
        }

        if (!colorPrefix.isEmpty()) ansiPrefix.add(colorPrefix);
        if (!bgColorPrefix.isEmpty()) ansiPrefix.add(bgColorPrefix);
        if (!attributePrefix.isEmpty()) ansiPrefix.add(attributePrefix);

        return ansiPrefix.toString();
    }

    private static String getColorTextPrefix(StyleConfig config) {
        if (config.rgbColor() != null) {
            Rgb rgb = config.rgbColor();
            return String.format("38;2;%d;%d;%d", rgb.r(), rgb.g(), rgb.b());
        }

        if (config.basicColor() != null) {
            return config.basicColor().getAnsiCode(false);
        }

        return "";
    }

    private static String getColorBackgroundPrefix(StyleConfig config) {
        if (config.bgRgbColor() != null) {
            Rgb rgb = config.bgRgbColor();
            return String.format("48;2;%d;%d;%d", rgb.r(), rgb.g(), rgb.b());
        }

        if (config.bgBasicColor() != null) {
            return config.bgBasicColor().getAnsiCode(true);
        }

        return "";
    }

    private static String getAttributePrefix(StyleConfig config) {
        StringJoiner prefix = new StringJoiner(";");

        if (config.isBold()) prefix.add("1");
        if (config.isDim()) prefix.add("2");
        if (config.isItalic()) prefix.add("3");
        if (config.isUnderlined()) prefix.add("4");
        if (config.isSlowBlink()) prefix.add("5");
        if (config.isRapidBlink()) prefix.add("6");
        if (config.isReverse()) prefix.add("7");
        if (config.isHide()) prefix.add("8");

        return prefix.toString();
    }
}
