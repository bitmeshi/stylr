package io.github.bitmeshi.stylr;

import io.github.bitmeshi.stylr.builder.StyleDefinitionBuilder;
import io.github.bitmeshi.stylr.builder.TextStyler;

import java.util.Objects;

/**
 * Main entry point for the Stylr library, providing factory methods to create styled text.
 * <p>
 * This class offers two approaches to styling text:
 * <ul>
 *   <li><b>Direct styling:</b> Use {@link #of(String)} to style text immediately</li>
 *   <li><b>Reusable styles:</b> Use {@link #style()} to create reusable {@link Style} objects</li>
 * </ul>
 *
 * <h2>Usage Examples</h2>
 * <pre>{@code
 * // Direct styling - one-off text styling
 * String result = Stylr.of("Hello World")
 *     .color(BasicColor.RED)
 *     .bold()
 *     .render();
 *
 * // Reusable styles - define once, apply multiple times
 * Style errorStyle = Stylr.style()
 *     .color(BasicColor.RED)
 *     .bold()
 *     .build();
 *
 * String error1 = errorStyle.apply("Error 1");
 * String error2 = errorStyle.apply("Error 2");
 * }</pre>
 *
 * @since 1.0
 */
public final class Stylr {
    private Stylr() {
        throw new IllegalStateException("Utility class cannot be instantiated");
    }

    /**
     * Creates a {@link TextStyler} for direct text styling.
     * <p>
     * Use this method when you want to style a specific text once without creating
     * a reusable style definition.
     *
     * @param text the text to be styled, must not be null
     * @return a new {@link TextStyler} instance for the given text
     * @throws NullPointerException if text is null
     * @see TextStyler
     */
    public static TextStyler of(String text) {
        Objects.requireNonNull(text, "Text cannot be null");
        return new TextStyler(text);
    }

    /**
     * Creates a {@link StyleDefinitionBuilder} for building reusable styles.
     * <p>
     * Use this method when you want to define a style once and apply it to multiple
     * different texts. The builder creates a {@link Style} object that can be reused.
     *
     * @return a new {@link StyleDefinitionBuilder} instance
     * @see StyleDefinitionBuilder
     * @see Style
     */
    public static StyleDefinitionBuilder style() {
        return new StyleDefinitionBuilder();
    }
}
