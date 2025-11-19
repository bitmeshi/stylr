package io.github.bitmeshi.stylr.builder;

import io.github.bitmeshi.stylr.BasicColor;
import io.github.bitmeshi.stylr.internal.StyleConfig;
import io.github.bitmeshi.stylr.internal.color.Rgb;

import java.util.Objects;

/**
 * Abstract base class for style builders providing common styling functionality.
 * <p>
 * This sealed class contains shared logic for configuring text styles including:
 * <ul>
 *   <li>Foreground and background colors (basic and RGB)</li>
 *   <li>Text attributes (bold, italic, underline, etc.)</li>
 *   <li>Fluent API implementation using self-returning methods</li>
 * </ul>
 * <p>
 * This class is sealed and can only be extended by {@link TextStyler} and
 * {@link StyleDefinitionBuilder} within the same package.
 *
 * @param <T> the concrete builder type for self-returning methods
 * @since 1.0
 */
abstract sealed class BaseStyleBuilder<T extends BaseStyleBuilder<T>> permits StyleDefinitionBuilder, TextStyler {
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

    /**
     * Returns the concrete builder instance for method chaining.
     *
     * @return this builder instance
     */
    protected abstract T self();

    /**
     * Sets the foreground text color using a basic ANSI color.
     * <p>
     * Setting this will override any previously set RGB or hex color.
     *
     * @param basicColor the basic color to use, must not be null
     * @return this builder for method chaining
     * @throws NullPointerException if basicColor is null
     */
    public T color(BasicColor basicColor) {
        Objects.requireNonNull(basicColor, "color cannot be null");
        this.basicColor = basicColor;
        rgbColor = null;
        return self();
    }

    /**
     * Sets the foreground text color using RGB values.
     * <p>
     * Setting this will override any previously set basic or hex color.
     *
     * @param r the red component (0-255)
     * @param g the green component (0-255)
     * @param b the blue component (0-255)
     * @return this builder for method chaining
     * @throws IllegalArgumentException if any RGB value is outside the range 0-255
     */
    public T color(int r, int g, int b) {
        rgbColor = new Rgb(r, g, b);
        basicColor = null;
        return self();
    }

    /**
     * Sets the foreground text color using a hexadecimal color code.
     * <p>
     * Supports both 3-digit and 6-digit formats (e.g., "#RGB" or "#RRGGBB").
     * Setting this will override any previously set basic or RGB color.
     *
     * @param hexadecimal the hex color code, must not be null
     * @return this builder for method chaining
     * @throws NullPointerException if hexadecimal is null
     * @throws IllegalArgumentException if the hex format is invalid
     */
    public T color(String hexadecimal) {
        Objects.requireNonNull(hexadecimal, "color cannot be null");
        rgbColor = Rgb.fromHex(hexadecimal);
        basicColor = null;
        return self();
    }

    /**
     * Sets the background color using a basic ANSI color.
     * <p>
     * Setting this will override any previously set RGB or hex background color.
     *
     * @param basicColor the basic background color to use, must not be null
     * @return this builder for method chaining
     * @throws NullPointerException if basicColor is null
     */
    public T bgColor(BasicColor basicColor) {
        Objects.requireNonNull(basicColor, "bgColor cannot be null");
        bgBasicColor = basicColor;
        bgRgbColor = null;
        return self();
    }

    /**
     * Sets the background color using RGB values.
     * <p>
     * Setting this will override any previously set basic or hex background color.
     *
     * @param r the red component (0-255)
     * @param g the green component (0-255)
     * @param b the blue component (0-255)
     * @return this builder for method chaining
     * @throws IllegalArgumentException if any RGB value is outside the range 0-255
     */
    public T bgColor(int r, int g, int b) {
        bgRgbColor = new Rgb(r, g, b);
        bgBasicColor = null;
        return self();
    }

    /**
     * Sets the background color using a hexadecimal color code.
     * <p>
     * Supports both 3-digit and 6-digit formats (e.g., "#RGB" or "#RRGGBB").
     * Setting this will override any previously set basic or RGB background color.
     *
     * @param hexadecimal the hex color code, must not be null
     * @return this builder for method chaining
     * @throws NullPointerException if hexadecimal is null
     * @throws IllegalArgumentException if the hex format is invalid
     */
    public T bgColor(String hexadecimal) {
        Objects.requireNonNull(hexadecimal, "bgColor cannot be null");
        bgRgbColor = Rgb.fromHex(hexadecimal);
        bgBasicColor = null;
        return self();
    }

    /**
     * Applies bold text attribute.
     *
     * @return this builder for method chaining
     */
    public T bold() {
        isBold = true;
        return self();
    }

    /**
     * Applies dim (faint) text attribute.
     *
     * @return this builder for method chaining
     */
    public T dim() {
        isDim = true;
        return self();
    }

    /**
     * Applies italic text attribute.
     *
     * @return this builder for method chaining
     */
    public T italic() {
        isItalic = true;
        return self();
    }

    /**
     * Applies underlined text attribute.
     *
     * @return this builder for method chaining
     */
    public T underlined() {
        isUnderlined = true;
        return self();
    }

    /**
     * Applies slow blink text attribute.
     *
     * @return this builder for method chaining
     */
    public T slowBlink() {
        isSlowBlink = true;
        return self();
    }

    /**
     * Applies rapid blink text attribute.
     *
     * @return this builder for method chaining
     */
    public T rapidBlink() {
        isRapidBlink = true;
        return self();
    }

    /**
     * Applies reverse attribute (inverts foreground and background colors).
     *
     * @return this builder for method chaining
     */
    public T reverse() {
        isReverse = true;
        return self();
    }

    /**
     * Applies hidden text attribute.
     *
     * @return this builder for method chaining
     */
    public T hide() {
        isHide = true;
        return self();
    }

    /**
     * Builds the internal style configuration from the current builder state.
     *
     * @return a {@link StyleConfig} with all configured settings
     */
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
