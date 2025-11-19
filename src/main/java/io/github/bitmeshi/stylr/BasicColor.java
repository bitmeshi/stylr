package io.github.bitmeshi.stylr;

/**
 * Enumeration of basic ANSI colors supported by most terminal emulators.
 * <p>
 * Provides both standard (8 colors) and bright variants (16 colors total).
 * Colors can be used for both foreground text and background styling.
 *
 * <h2>Color Categories</h2>
 * <ul>
 *   <li><b>Standard colors:</b> BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE</li>
 *   <li><b>Bright colors:</b> BRIGHT_BLACK, BRIGHT_RED, BRIGHT_GREEN, etc.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>{@code
 * // Foreground color
 * String red = Stylr.of("Error")
 *     .color(BasicColor.RED)
 *     .render();
 *
 * // Background color
 * String highlighted = Stylr.of("Important")
 *     .bgColor(BasicColor.YELLOW)
 *     .render();
 *
 * // Both foreground and background
 * String styled = Stylr.of("Alert")
 *     .color(BasicColor.WHITE)
 *     .bgColor(BasicColor.RED)
 *     .render();
 * }</pre>
 *
 * @since 1.0
 */
public enum BasicColor {
    /** Standard black color */
    BLACK(0),
    /** Standard red color */
    RED(1),
    /** Standard green color */
    GREEN(2),
    /** Standard yellow color */
    YELLOW(3),
    /** Standard blue color */
    BLUE(4),
    /** Standard magenta color */
    MAGENTA(5),
    /** Standard cyan color */
    CYAN(6),
    /** Standard white color */
    WHITE(7),

    /** Bright black color (gray) */
    BRIGHT_BLACK(60),
    /** Bright red color */
    BRIGHT_RED(61),
    /** Bright green color */
    BRIGHT_GREEN(62),
    /** Bright yellow color */
    BRIGHT_YELLOW(63),
    /** Bright blue color */
    BRIGHT_BLUE(64),
    /** Bright magenta color */
    BRIGHT_MAGENTA(65),
    /** Bright cyan color */
    BRIGHT_CYAN(66),
    /** Bright white color */
    BRIGHT_WHITE(67);

    private final int ansiCodeOffset;

    BasicColor(int ansiCodeOffset) {
        this.ansiCodeOffset = ansiCodeOffset;
    }

    /**
     * Returns the ANSI escape code for this color.
     *
     * @param isBackground if true, returns the background color code; otherwise, foreground
     * @return the ANSI color code as a string
     */
    public String getAnsiCode(boolean isBackground) {
        int baseCode = isBackground ? 40 : 30;
        return String.format("%d", baseCode + this.ansiCodeOffset);
    }
}
