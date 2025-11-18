package io.github.bitmeshi.stylr.builder;

import io.github.bitmeshi.stylr.BasicColor;
import io.github.bitmeshi.stylr.Style;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StyleDefinitionBuilderTest {
    @Test
    @DisplayName("Test build creates Style with no configuration")
    void buildWithNoConfiguration() {
        Style style = new StyleDefinitionBuilder().build();
        String result = style.apply("Hello");
        assertEquals("Hello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test build creates Style with basic color")
    void buildWithBasicColor() {
        Style style = new StyleDefinitionBuilder()
                .color(BasicColor.RED)
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[31mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test build creates Style with background color")
    void buildWithBackgroundColor() {
        Style style = new StyleDefinitionBuilder()
                .bgColor(BasicColor.BLUE)
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[44mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test build creates Style with both colors")
    void buildWithBothColors() {
        Style style = new StyleDefinitionBuilder()
                .color(BasicColor.RED)
                .bgColor(BasicColor.GREEN)
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[31;42mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test build creates Style with bold attribute")
    void buildWithBold() {
        Style style = new StyleDefinitionBuilder()
                .bold()
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[1mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test build creates Style with multiple attributes")
    void buildWithMultipleAttributes() {
        Style style = new StyleDefinitionBuilder()
                .bold()
                .italic()
                .underlined()
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[1;3;4mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test build creates Style with color and attributes")
    void buildWithColorAndAttributes() {
        Style style = new StyleDefinitionBuilder()
                .color(BasicColor.YELLOW)
                .bold()
                .italic()
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[33;1;3mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test build creates Style with all styles")
    void buildWithAllStyles() {
        Style style = new StyleDefinitionBuilder()
                .color(BasicColor.MAGENTA)
                .bgColor(BasicColor.CYAN)
                .bold()
                .dim()
                .italic()
                .underlined()
                .slowBlink()
                .rapidBlink()
                .reverse()
                .hide()
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[35;46;1;2;3;4;5;6;7;8mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test method chaining returns same instance")
    void methodChaining() {
        StyleDefinitionBuilder builder = new StyleDefinitionBuilder();
        assertSame(builder, builder.color(BasicColor.RED));
        assertSame(builder, builder.bgColor(BasicColor.BLUE));
        assertSame(builder, builder.bold());
        assertSame(builder, builder.italic());
    }

    @Test
    @DisplayName("Test build creates reusable Style")
    void buildCreatesReusableStyle() {
        Style style = new StyleDefinitionBuilder()
                .color(BasicColor.GREEN)
                .bold()
                .build();

        String result1 = style.apply("First");
        String result2 = style.apply("Second");
        String result3 = style.apply("Third");

        assertEquals("\u001b[32;1mFirst\u001b[0m", result1);
        assertEquals("\u001b[32;1mSecond\u001b[0m", result2);
        assertEquals("\u001b[32;1mThird\u001b[0m", result3);
    }

    @Test
    @DisplayName("Test build with RGB color")
    void buildWithRgbColor() {
        Style style = new StyleDefinitionBuilder()
                .color(255, 128, 0)
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[38;2;255;128;0mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test build with RGB background color")
    void buildWithRgbBackgroundColor() {
        Style style = new StyleDefinitionBuilder()
                .bgColor(100, 200, 150)
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[48;2;100;200;150mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test build with both RGB colors")
    void buildWithBothRgbColors() {
        Style style = new StyleDefinitionBuilder()
                .color(255, 0, 0)
                .bgColor(0, 255, 0)
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[38;2;255;0;0;48;2;0;255;0mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test build with RGB color and attributes")
    void buildWithRgbColorAndAttributes() {
        Style style = new StyleDefinitionBuilder()
                .color(128, 128, 255)
                .bold()
                .italic()
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[38;2;128;128;255;1;3mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test build with hexadecimal color")
    void buildWithHexColor() {
        Style style = new StyleDefinitionBuilder()
                .color("#FF8000")
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[38;2;255;128;0mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test build with hexadecimal background color")
    void buildWithHexBackgroundColor() {
        Style style = new StyleDefinitionBuilder()
                .bgColor("#64C896")
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[48;2;100;200;150mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test build with both hexadecimal colors")
    void buildWithBothHexColors() {
        Style style = new StyleDefinitionBuilder()
                .color("#FF0000")
                .bgColor("#00FF00")
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[38;2;255;0;0;48;2;0;255;0mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test build with short hexadecimal color (3-digit)")
    void buildWithShortHexColor() {
        Style style = new StyleDefinitionBuilder()
                .color("#F80")
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[38;2;255;136;0mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test RGB colors override basic colors")
    void rgbOverridesBasicColors() {
        Style style = new StyleDefinitionBuilder()
                .color(BasicColor.RED)
                .color(0, 255, 0)
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[38;2;0;255;0mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test basic colors override RGB colors when set later")
    void basicOverridesRgbColors() {
        Style style = new StyleDefinitionBuilder()
                .color(255, 0, 0)
                .color(BasicColor.BLUE)
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[34mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test hexadecimal colors override basic colors")
    void hexOverridesBasicColors() {
        Style style = new StyleDefinitionBuilder()
                .color(BasicColor.RED)
                .color("#00FF00")
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[38;2;0;255;0mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test null color throws exception")
    void nullColorThrowsException() {
        StyleDefinitionBuilder builder = new StyleDefinitionBuilder();
        assertThrows(NullPointerException.class, () -> builder.color((BasicColor) null));
        assertThrows(NullPointerException.class, () -> builder.color((String) null));
    }

    @Test
    @DisplayName("Test null background color throws exception")
    void nullBackgroundColorThrowsException() {
        StyleDefinitionBuilder builder = new StyleDefinitionBuilder();
        assertThrows(NullPointerException.class, () -> builder.bgColor((BasicColor) null));
        assertThrows(NullPointerException.class, () -> builder.bgColor((String) null));
    }

    @Test
    @DisplayName("Test invalid RGB values throw exception")
    void invalidRgbValuesThrowException() {
        StyleDefinitionBuilder builder = new StyleDefinitionBuilder();

        assertThrows(IllegalArgumentException.class, () -> builder.color(-1, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> builder.color(0, -1, 0));
        assertThrows(IllegalArgumentException.class, () -> builder.color(0, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> builder.color(256, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> builder.color(0, 256, 0));
        assertThrows(IllegalArgumentException.class, () -> builder.color(0, 0, 256));

        assertThrows(IllegalArgumentException.class, () -> builder.bgColor(-1, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> builder.bgColor(256, 0, 0));
    }

    @Test
    @DisplayName("Test invalid hexadecimal values throw exception")
    void invalidHexValuesThrowException() {
        StyleDefinitionBuilder builder = new StyleDefinitionBuilder();

        assertThrows(IllegalArgumentException.class, () -> builder.color("FF0000"));  // Missing #
        assertThrows(IllegalArgumentException.class, () -> builder.color("#FFFF"));   // Wrong length
        assertThrows(IllegalArgumentException.class, () -> builder.color("#GG0000")); // Invalid hex chars
        assertThrows(IllegalArgumentException.class, () -> builder.color("#2 3432")); // Contains space
        assertThrows(IllegalArgumentException.class, () -> builder.color("#1-32-1")); // Contains dash

        assertThrows(IllegalArgumentException.class, () -> builder.bgColor("00FF00")); // Missing #
        assertThrows(IllegalArgumentException.class, () -> builder.bgColor("#FFFF"));  // Wrong length
    }

    @Test
    @DisplayName("Test dim attribute")
    void buildWithDim() {
        Style style = new StyleDefinitionBuilder()
                .dim()
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[2mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test slow blink attribute")
    void buildWithSlowBlink() {
        Style style = new StyleDefinitionBuilder()
                .slowBlink()
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[5mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test rapid blink attribute")
    void buildWithRapidBlink() {
        Style style = new StyleDefinitionBuilder()
                .rapidBlink()
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[6mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test reverse attribute")
    void buildWithReverse() {
        Style style = new StyleDefinitionBuilder()
                .reverse()
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[7mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test hide attribute")
    void buildWithHide() {
        Style style = new StyleDefinitionBuilder()
                .hide()
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[8mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test bright colors")
    void buildWithBrightColors() {
        Style style = new StyleDefinitionBuilder()
                .color(BasicColor.BRIGHT_GREEN)
                .bgColor(BasicColor.BRIGHT_RED)
                .build();
        String result = style.apply("Hello");
        assertEquals("\u001b[92;101mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test complex combination with all features")
    void buildComplexCombination() {
        Style style = new StyleDefinitionBuilder()
                .color(120, 80, 200)
                .bgColor(255, 240, 100)
                .bold()
                .italic()
                .underlined()
                .reverse()
                .build();
        String result = style.apply("Complex");
        assertEquals("\u001b[38;2;120;80;200;48;2;255;240;100;1;3;4;7mComplex\u001b[0m", result);
    }
}

