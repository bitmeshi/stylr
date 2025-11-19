package io.github.bitmeshi.stylr.builder;

import io.github.bitmeshi.stylr.internal.AnsiCodeGenerator;

/**
 * Builder for styling text directly without creating a reusable style definition.
 * <p>
 * This class provides a fluent API to apply colors, backgrounds, and text attributes
 * to a specific text string. The styled text is produced by calling {@link #render()}.
 * <p>
 * Use this class when you need to style a specific text once. For reusable styles,
 * use {@link StyleDefinitionBuilder} instead.
 *
 * <h2>Usage Example</h2>
 * <pre>{@code
 * String styled = Stylr.of("Hello World")
 *     .color(BasicColor.RED)
 *     .bold()
 *     .underlined()
 *     .render();
 *
 * // With RGB colors
 * String rgb = Stylr.of("Custom Color")
 *     .color(255, 100, 50)
 *     .render();
 *
 * // With hex colors
 * String hex = Stylr.of("Hex Color")
 *     .color("#FF6432")
 *     .render();
 * }</pre>
 *
 * @since 1.0
 * @see io.github.bitmeshi.stylr.Stylr#of(String)
 * @see StyleDefinitionBuilder
 */
public final class TextStyler extends BaseStyleBuilder<TextStyler> {
    private final String text;

    /**
     * Constructs a new TextStyler for the given text.
     * <p>
     * This constructor is typically called by {@link io.github.bitmeshi.stylr.Stylr#of(String)}
     * and should not be called directly by users.
     *
     * @param text the text to be styled
     */
    public TextStyler(String text) {
        this.text = text;
    }

    @Override
    protected TextStyler self() {
        return this;
    }

    /**
     * Renders the styled text with all applied formatting.
     * <p>
     * Generates the complete ANSI-escaped string with the configured colors,
     * background, and text attributes. The result includes a reset sequence
     * at the end to restore default terminal styling.
     * <p>
     * If the text is empty, returns an empty string without any ANSI codes.
     * <p>
     * Internally uses {@link io.github.bitmeshi.stylr.internal.AnsiCodeGenerator}
     * to convert the style configuration into ANSI escape codes.
     *
     * @return the styled text with ANSI escape codes, or empty string if text is empty
     */
    public String render() {
        String ansiPrefix = AnsiCodeGenerator.getAnsiPrefix(buildStyleConfig());

        if (text.isEmpty()) return "";

        return ansiPrefix + text + "\u001b[0m";
    }
}
