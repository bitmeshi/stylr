package io.github.bitmeshi.stylr.builder;

import io.github.bitmeshi.stylr.Style;
import io.github.bitmeshi.stylr.internal.AnsiCodeGenerator;

/**
 * Builder for creating reusable {@link Style} definitions.
 * <p>
 * This class provides a fluent API to configure colors, backgrounds, and text attributes
 * that can be applied to multiple different texts. The style is finalized by calling
 * {@link #build()}, which returns a reusable {@link Style} object.
 * <p>
 * Use this class when you need to apply the same styling to multiple texts.
 * For one-off text styling, use {@link TextStyler} instead.
 *
 * <h2>Usage Example</h2>
 * <pre>{@code
 * // Create a reusable error style
 * Style errorStyle = Stylr.style()
 *     .color(BasicColor.RED)
 *     .bold()
 *     .build();
 *
 * // Apply to multiple texts
 * String error1 = errorStyle.apply("Connection failed");
 * String error2 = errorStyle.apply("File not found");
 * String error3 = errorStyle.apply("Access denied");
 * }</pre>
 *
 * @since 1.0
 * @see io.github.bitmeshi.stylr.Stylr#style()
 * @see Style
 * @see TextStyler
 */
public final class StyleDefinitionBuilder extends BaseStyleBuilder<StyleDefinitionBuilder> {
    @Override
    protected StyleDefinitionBuilder self() {
        return this;
    }

    /**
     * Builds and returns a reusable {@link Style} with the configured settings.
     * <p>
     * Creates a {@link Style} object that encapsulates all the configured colors,
     * background, and text attributes. The returned style can be applied to
     * multiple different texts without rebuilding the configuration.
     * <p>
     * Internally uses {@link io.github.bitmeshi.stylr.internal.AnsiCodeGenerator}
     * to convert the style configuration into ANSI escape codes.
     *
     * @return a new {@link Style} instance with the configured styling
     */
    public Style build() {
        String ansiPrefix = AnsiCodeGenerator.getAnsiPrefix(buildStyleConfig());
        return new Style(ansiPrefix);
    }
}
