package io.github.bitmeshi.stylr.internal;

import java.util.StringJoiner;

/**
 * Internal utility class for generating ANSI escape sequences from style configurations.
 * <p>
 * This class converts a {@link StyleConfig} into the appropriate ANSI escape codes
 * that can be used to style terminal output. It handles foreground colors, background
 * colors, and text attributes (bold, italic, underline, etc.).
 * <p>
 * The generated ANSI codes follow the standard format: {@code \u001b[<codes>m}
 * where codes are semicolon-separated numeric values.
 * <p>
 * This class is part of the internal API and should not be used directly
 * by library consumers.
 *
 * @since 1.0
 */
public final class AnsiCodeGenerator {
    /**
     * Generates the ANSI escape sequence prefix for the given style configuration.
     * <p>
     * Creates a complete ANSI escape code string that includes all configured
     * styling (colors, backgrounds, and attributes). If no styling is configured,
     * returns an empty string.
     *
     * @param config the style configuration to convert to ANSI codes
     * @return the ANSI escape sequence, or empty string if no styling is configured
     */
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

    /**
     * Generates the ANSI code for foreground text color.
     *
     * @param config the style configuration
     * @return the ANSI color code, or empty string if no color is set
     */
    private static String getColorTextPrefix(StyleConfig config) {
        if (config.rgbColor() != null) {
            return String.format("38;2;%d;%d;%d",
                    config.rgbColor().r(),
                    config.rgbColor().g(),
                    config.rgbColor().b()
            );
        }

        if (config.basicColor() != null) {
            return config.basicColor().getAnsiCode(false);
        }

        return "";
    }

    /**
     * Generates the ANSI code for background color.
     *
     * @param config the style configuration
     * @return the ANSI background color code, or empty string if no background is set
     */
    private static String getColorBackgroundPrefix(StyleConfig config) {
        if (config.bgRgbColor() != null) {
            return String.format("48;2;%d;%d;%d",
                    config.bgRgbColor().r(),
                    config.bgRgbColor().g(),
                    config.bgRgbColor().b()
            );
        }

        if (config.bgBasicColor() != null) {
            return config.bgBasicColor().getAnsiCode(true);
        }

        return "";
    }

    /**
     * Generates the ANSI codes for text attributes (bold, italic, etc.).
     *
     * @param config the style configuration
     * @return semicolon-separated ANSI attribute codes, or empty string if no attributes are set
     */
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
