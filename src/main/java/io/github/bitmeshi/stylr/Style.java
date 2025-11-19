package io.github.bitmeshi.stylr;

import java.util.Objects;

/**
 * Represents a reusable style definition that can be applied to multiple texts.
 * <p>
 * A {@code Style} encapsulates ANSI escape codes for colors, background colors,
 * and text attributes (bold, italic, underline, etc.). Once created, it can be
 * applied to different text strings without rebuilding the style configuration.
 * <p>
 * Instances are immutable and thread-safe.
 *
 * <h2>Usage Example</h2>
 * <pre>{@code
 * Style errorStyle = Stylr.style()
 *     .color(BasicColor.RED)
 *     .bold()
 *     .build();
 *
 * String error1 = errorStyle.apply("Connection failed");
 * String error2 = errorStyle.apply("File not found");
 * String error3 = errorStyle.apply("Access denied");
 * }</pre>
 *
 * @since 1.0
 * @see Stylr#style()
 */
public final class Style {
    private static final String ANSI_RESET = "\u001b[0m";
    private final String ansiPrefix;

    /**
     * Constructs a new Style with the given ANSI prefix.
     * <p>
     * This constructor is typically called by {@link io.github.bitmeshi.stylr.builder.StyleDefinitionBuilder}
     * and should not be called directly by users.
     *
     * @param ansiPrefix the ANSI escape sequence prefix to apply to text
     */
    public Style(String ansiPrefix) {
        this.ansiPrefix = ansiPrefix;
    }

    /**
     * Applies this style to the given text.
     * <p>
     * The method wraps the text with the appropriate ANSI escape codes and
     * automatically appends a reset sequence to restore default terminal styling.
     * <p>
     * If the text is empty, an empty string is returned without any ANSI codes.
     *
     * @param text the text to style, must not be null
     * @return the styled text with ANSI codes, or empty string if text is empty
     * @throws NullPointerException if text is null
     */
    public String apply(String text) {
        Objects.requireNonNull(text, "Text cannot be null");

        if (text.isEmpty()) return "";

        return ansiPrefix + text + ANSI_RESET;
    }
}
