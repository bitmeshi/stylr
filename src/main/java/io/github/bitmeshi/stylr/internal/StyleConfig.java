package io.github.bitmeshi.stylr.internal;

import io.github.bitmeshi.stylr.BasicColor;
import io.github.bitmeshi.stylr.internal.color.Rgb;

/**
 * Internal configuration record that encapsulates all style settings.
 * <p>
 * This immutable record holds the complete configuration for text styling,
 * including colors (basic and RGB), background colors, and text attributes.
 * It is used internally to pass styling information to the ANSI code generator.
 * <p>
 * This class is part of the internal API and should not be used directly
 * by library consumers.
 *
 * @param basicColor the basic foreground color, or null if not set
 * @param bgBasicColor the basic background color, or null if not set
 * @param rgbColor the RGB foreground color, or null if not set
 * @param bgRgbColor the RGB background color, or null if not set
 * @param isBold true to apply bold text attribute
 * @param isDim true to apply dim (faint) text attribute
 * @param isItalic true to apply italic text attribute
 * @param isUnderlined true to apply underlined text attribute
 * @param isSlowBlink true to apply slow blink text attribute
 * @param isRapidBlink true to apply rapid blink text attribute
 * @param isReverse true to apply reverse attribute (inverts foreground and background colors)
 * @param isHide true to apply hidden text attribute
 * @since 1.0
 */
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
