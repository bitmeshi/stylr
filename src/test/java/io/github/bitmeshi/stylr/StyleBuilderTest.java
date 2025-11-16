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
    @DisplayName("Test null color throws exception")
    void nullColorThrowsException() {
        StyleBuilder builder = new StyleBuilder("Hello");
        assertThrows(NullPointerException.class, () -> builder.color(null));
    }

    @Test
    @DisplayName("Test null background color throws exception")
    void nullBackgroundColorThrowsException() {
        StyleBuilder builder = new StyleBuilder("Hello");
        assertThrows(NullPointerException.class, () -> builder.bgColor(null));
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
}
