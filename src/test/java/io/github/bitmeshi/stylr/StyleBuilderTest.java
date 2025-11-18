package io.github.bitmeshi.stylr;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StyleBuilderTest {
    @Test
    @DisplayName("Test plain text without styles")
    void plainText() {
        String result = new StyleBuilder("Hello World").render();
        assertEquals("Hello World\u001b[0m", result);
    }

    @Test
    @DisplayName("Test empty text")
    void emptyText() {
        String result = new StyleBuilder("").render();
        assertEquals("", result);
    }

    @Test
    @DisplayName("Test text with color only")
    void textWithColor() {
        String result = new StyleBuilder("Hello")
                .color(BasicColor.RED)
                .render();
        assertEquals("\u001b[31mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test text with background color only")
    void textWithBackgroundColor() {
        String result = new StyleBuilder("Hello")
                .bgColor(BasicColor.BLUE)
                .render();
        assertEquals("\u001b[44mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test text with both color and background")
    void textWithColorAndBackground() {
        String result = new StyleBuilder("Hello")
                .color(BasicColor.RED)
                .bgColor(BasicColor.BLUE)
                .render();
        assertEquals("\u001b[31;44mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test text with single attribute - bold")
    void textWithBold() {
        String result = new StyleBuilder("Hello")
                .bold()
                .render();
        assertEquals("\u001b[1mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test text with multiple attributes")
    void textWithMultipleAttributes() {
        String result = new StyleBuilder("Hello")
                .bold()
                .italic()
                .underlined()
                .render();
        assertEquals("\u001b[1;3;4mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test text with all styles combined")
    void textWithAllStyles() {
        String result = new StyleBuilder("Hello")
                .color(BasicColor.YELLOW)
                .bgColor(BasicColor.MAGENTA)
                .bold()
                .italic()
                .render();
        assertEquals("\u001b[33;45;1;3mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test dim attribute")
    void textWithDim() {
        String result = new StyleBuilder("Hello")
                .dim()
                .render();
        assertEquals("\u001b[2mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test slow blink attribute")
    void textWithSlowBlink() {
        String result = new StyleBuilder("Hello")
                .slowBlink()
                .render();
        assertEquals("\u001b[5mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test rapid blink attribute")
    void textWithRapidBlink() {
        String result = new StyleBuilder("Hello")
                .rapidBlink()
                .render();
        assertEquals("\u001b[6mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test reverse attribute")
    void textWithReverse() {
        String result = new StyleBuilder("Hello")
                .reverse()
                .render();
        assertEquals("\u001b[7mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test hide attribute")
    void textWithHide() {
        String result = new StyleBuilder("Hello")
                .hide()
                .render();
        assertEquals("\u001b[8mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test all attributes combined")
    void textWithAllAttributes() {
        String result = new StyleBuilder("Hello")
                .bold()
                .dim()
                .italic()
                .underlined()
                .slowBlink()
                .rapidBlink()
                .reverse()
                .hide()
                .render();
        assertEquals("\u001b[1;2;3;4;5;6;7;8mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test bright colors")
    void textWithBrightColors() {
        String result = new StyleBuilder("Hello")
                .color(BasicColor.BRIGHT_GREEN)
                .bgColor(BasicColor.BRIGHT_RED)
                .render();
        assertEquals("\u001b[92;101mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test method chaining returns same instance")
    void methodChaining() {
        StyleBuilder builder = new StyleBuilder("Hello");
        assertSame(builder, builder.color(BasicColor.RED));
        assertSame(builder, builder.bgColor(BasicColor.BLUE));
        assertSame(builder, builder.bold());
        assertSame(builder, builder.italic());
    }

    @Test
    @DisplayName("Test RGB method chaining returns same instance")
    void rgbMethodChaining() {
        StyleBuilder builder = new StyleBuilder("Hello");
        assertSame(builder, builder.color(255, 0, 0));
        assertSame(builder, builder.bgColor(0, 255, 0));
    }

    @Test
    @DisplayName("Test null color (basic color) throws exception")
    void nullColorBasicThrowsException() {
        StyleBuilder builder = new StyleBuilder("Hello");
        assertThrows(NullPointerException.class, () -> builder.color((BasicColor) null));
    }

    @Test
    @DisplayName("Test null color (hexadecimal) throws exception")
    void nullColorHexThrowsException() {
        StyleBuilder builder = new StyleBuilder("Hello");
        assertThrows(NullPointerException.class, () -> builder.color((String) null));
    }

    @Test
    @DisplayName("Test null background color (basic color) throws exception")
    void nullBackgroundColorBasicThrowsException() {
        StyleBuilder builder = new StyleBuilder("Hello");
        assertThrows(NullPointerException.class, () -> builder.bgColor((BasicColor) null));
    }

    @Test
    @DisplayName("Test null background color (hexadecimal) throws exception")
    void nullBackgroundColorHexThrowsException() {
        StyleBuilder builder = new StyleBuilder("Hello");
        assertThrows(NullPointerException.class, () -> builder.bgColor((String) null));
    }

    @Test
    @DisplayName("Test final reset sequence is always present for non-empty text")
    void finalResetSequence() {
        // Test that reset sequence \u001b[0m is always at the end for styled text
        String result1 = new StyleBuilder("test").bold().render();
        assertTrue(result1.endsWith("\u001b[0m"));

        String result2 = new StyleBuilder("test").color(BasicColor.RED).render();
        assertTrue(result2.endsWith("\u001b[0m"));

        String result3 = new StyleBuilder("test").render();
        assertTrue(result3.endsWith("\u001b[0m"));
    }

    @Test
    @DisplayName("Test no reset sequence for empty text")
    void noResetForEmptyText() {
        String result = new StyleBuilder("").bold().render();
        assertEquals("", result);
        assertFalse(result.contains("\u001b[0m"));
    }

    @Test
    @DisplayName("Test RGB color only")
    void testRgbColor() {
        String result = new StyleBuilder("Hello")
                .color(255, 128, 0)
                .render();
        assertEquals("\u001b[38;2;255;128;0mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test RGB background color only")
    void testRgbBackgroundColor() {
        String result = new StyleBuilder("Hello")
                .bgColor(100, 200, 150)
                .render();
        assertEquals("\u001b[48;2;100;200;150mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test both RGB colors")
    void testBothRgbColors() {
        String result = new StyleBuilder("Hello")
                .color(255, 0, 0)
                .bgColor(0, 255, 0)
                .render();
        assertEquals("\u001b[38;2;255;0;0;48;2;0;255;0mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test RGB color with attributes")
    void testRgbColorWithAttributes() {
        String result = new StyleBuilder("Hello")
                .color(128, 128, 255)
                .bold()
                .italic()
                .render();
        assertEquals("\u001b[38;2;128;128;255;1;3mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test RGB colors override basic colors")
    void testRgbOverrideBasicColors() {
        String result = new StyleBuilder("Hello")
                .color(BasicColor.RED)  // This should be overridden
                .color(0, 255, 0)       // RGB green should take precedence
                .render();
        assertEquals("\u001b[38;2;0;255;0mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test basic colors override RGB colors when set later")
    void testBasicOverrideRgbColors() {
        String result = new StyleBuilder("Hello")
                .color(255, 0, 0)       // RGB red
                .color(BasicColor.BLUE) // Basic blue should override
                .render();
        assertEquals("\u001b[34mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test invalid RGB values throw exception")
    void testInvalidRgbValues() {
        StyleBuilder builder = new StyleBuilder("Hello");

        // Test negative values
        assertThrows(IllegalArgumentException.class, () -> builder.color(-1, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> builder.color(0, -1, 0));
        assertThrows(IllegalArgumentException.class, () -> builder.color(0, 0, -1));

        // Test values > 255
        assertThrows(IllegalArgumentException.class, () -> builder.color(256, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> builder.color(0, 256, 0));
        assertThrows(IllegalArgumentException.class, () -> builder.color(0, 0, 256));

        // Test background colors
        assertThrows(IllegalArgumentException.class, () -> builder.bgColor(-1, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> builder.bgColor(256, 0, 0));
    }

    @Test
    @DisplayName("Test RGB boundary values")
    void testRgbBoundaryValues() {
        // Test minimum values (0)
        String result1 = new StyleBuilder("Test")
                .color(0, 0, 0)
                .render();
        assertEquals("\u001b[38;2;0;0;0mTest\u001b[0m", result1);

        // Test maximum values (255)
        String result2 = new StyleBuilder("Test")
                .color(255, 255, 255)
                .render();
        assertEquals("\u001b[38;2;255;255;255mTest\u001b[0m", result2);

        // Test mixed boundary values
        String result3 = new StyleBuilder("Test")
                .bgColor(0, 255, 128)
                .render();
        assertEquals("\u001b[48;2;0;255;128mTest\u001b[0m", result3);
    }

    @Test
    @DisplayName("Test complex RGB combination with all features")
    void testComplexRgbCombination() {
        String result = new StyleBuilder("Complex")
                .color(120, 80, 200)
                .bgColor(255, 240, 100)
                .bold()
                .italic()
                .underlined()
                .reverse()
                .render();
        assertEquals("\u001b[38;2;120;80;200;48;2;255;240;100;1;3;4;7mComplex\u001b[0m", result);
    }

    @Test
    @DisplayName("Test hexadecimal color only (6-digit)")
    void testHexColorSixDigit() {
        String result = new StyleBuilder("Hello")
                .color("#FF8000")
                .render();
        assertEquals("\u001b[38;2;255;128;0mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test hexadecimal color only (3-digit)")
    void testHexColorThreeDigit() {
        String result = new StyleBuilder("Hello")
                .color("#F80")
                .render();
        assertEquals("\u001b[38;2;255;136;0mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test hexadecimal background color only (6-digit)")
    void testHexBackgroundColorSixDigit() {
        String result = new StyleBuilder("Hello")
                .bgColor("#64C896")
                .render();
        assertEquals("\u001b[48;2;100;200;150mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test hexadecimal background color only (3-digit)")
    void testHexBackgroundColorThreeDigit() {
        String result = new StyleBuilder("Hello")
                .bgColor("#6C9")
                .render();
        assertEquals("\u001b[48;2;102;204;153mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test both hexadecimal colors")
    void testBothHexColors() {
        String result = new StyleBuilder("Hello")
                .color("#FF0000")
                .bgColor("#00FF00")
                .render();
        assertEquals("\u001b[38;2;255;0;0;48;2;0;255;0mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test hexadecimal color with attributes")
    void testHexColorWithAttributes() {
        String result = new StyleBuilder("Hello")
                .color("#8080FF")
                .bold()
                .italic()
                .render();
        assertEquals("\u001b[38;2;128;128;255;1;3mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test hexadecimal colors override basic colors")
    void testHexOverrideBasicColors() {
        String result = new StyleBuilder("Hello")
                .color(BasicColor.RED)    // This should be overridden
                .color("#00FF00")         // Hex green should take precedence
                .render();
        assertEquals("\u001b[38;2;0;255;0mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test basic colors override hexadecimal colors when set later")
    void testBasicOverrideHexColors() {
        String result = new StyleBuilder("Hello")
                .color("#FF0000")         // Hex red
                .color(BasicColor.BLUE)   // Basic blue should override
                .render();
        assertEquals("\u001b[34mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test RGB colors override hexadecimal colors when set later")
    void testRgbOverrideHexColors() {
        String result = new StyleBuilder("Hello")
                .color("#FF0000")         // Hex red
                .color(128, 128, 255)     // RGB should override
                .render();
        assertEquals("\u001b[38;2;128;128;255mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test hexadecimal colors override RGB colors when set later")
    void testHexOverrideRgbColors() {
        String result = new StyleBuilder("Hello")
                .color(255, 0, 0)         // RGB red
                .color("#00FF00")         // Hex green should override
                .render();
        assertEquals("\u001b[38;2;0;255;0mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test invalid hexadecimal values throw exception")
    void testInvalidHexValues() {
        StyleBuilder builder = new StyleBuilder("Hello");

        // Invalid format
        assertThrows(IllegalArgumentException.class, () -> builder.color("FF0000"));  // Missing #
        assertThrows(IllegalArgumentException.class, () -> builder.color("#FFFF"));   // Wrong length
        assertThrows(IllegalArgumentException.class, () -> builder.color("#GG0000")); // Invalid hex chars

        // Background colors
        assertThrows(IllegalArgumentException.class, () -> builder.bgColor("00FF00")); // Missing #
        assertThrows(IllegalArgumentException.class, () -> builder.bgColor("#FFFF"));  // Wrong length
    }

    @Test
    @DisplayName("Test invalid hexadecimal values with spaces and special characters throw exception")
    void testInvalidHexValuesWithSpecialChars() {
        StyleBuilder builder = new StyleBuilder("Hello");

        // Test specific cases with spaces and dashes
        assertThrows(IllegalArgumentException.class, () -> builder.color("#2 3432"));
        assertThrows(IllegalArgumentException.class, () -> builder.color("#1-32-1"));
        assertThrows(IllegalArgumentException.class, () -> builder.color("#12 456"));
        assertThrows(IllegalArgumentException.class, () -> builder.color("#AB CD"));
        assertThrows(IllegalArgumentException.class, () -> builder.color("#A B"));
        assertThrows(IllegalArgumentException.class, () -> builder.color("#1-2"));

        // Test with background colors as well
        assertThrows(IllegalArgumentException.class, () -> builder.bgColor("#2 3432"));
        assertThrows(IllegalArgumentException.class, () -> builder.bgColor("#1-32-1"));

        // Test with other special characters
        assertThrows(IllegalArgumentException.class, () -> builder.color("#12@456"));
        assertThrows(IllegalArgumentException.class, () -> builder.color("#FF&00A"));
        assertThrows(IllegalArgumentException.class, () -> builder.bgColor("#12+456"));
        assertThrows(IllegalArgumentException.class, () -> builder.bgColor("#FF*00A"));
    }

    @Test
    @DisplayName("Test hexadecimal method chaining returns same instance")
    void hexMethodChaining() {
        StyleBuilder builder = new StyleBuilder("Hello");
        assertSame(builder, builder.color("#FF0000"));
        assertSame(builder, builder.bgColor("#00FF00"));
    }

    @Test
    @DisplayName("Test complex hexadecimal combination with all features")
    void testComplexHexCombination() {
        String result = new StyleBuilder("Complex")
                .color("#7850C8")
                .bgColor("#FFF064")
                .bold()
                .italic()
                .underlined()
                .reverse()
                .render();
        assertEquals("\u001b[38;2;120;80;200;48;2;255;240;100;1;3;4;7mComplex\u001b[0m", result);
    }
}
