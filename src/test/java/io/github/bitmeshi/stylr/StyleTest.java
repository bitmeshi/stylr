package io.github.bitmeshi.stylr;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StyleTest {
    @Test
    @DisplayName("Test Style apply with simple text")
    void applySimpleText() {
        Style style = new Style("\u001b[31m");
        String result = style.apply("Hello");
        assertEquals("\u001b[31mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test Style apply with empty ANSI prefix")
    void applyEmptyAnsiPrefix() {
        Style style = new Style("");
        String result = style.apply("Hello");
        assertEquals("Hello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test Style apply with multiple attributes")
    void applyMultipleAttributes() {
        Style style = new Style("\u001b[31;1;3m");
        String result = style.apply("Hello");
        assertEquals("\u001b[31;1;3mHello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test Style apply with empty text")
    void applyEmptyText() {
        Style style = new Style("\u001b[31m");
        String result = style.apply("");
        assertEquals("", result);
    }

    @Test
    @DisplayName("Test Style apply with null text throws exception")
    void applyNullTextThrowsException() {
        Style style = new Style("\u001b[31m");
        assertThrows(NullPointerException.class, () -> style.apply(null));
    }

    @Test
    @DisplayName("Test Style apply with special characters")
    void applySpecialCharacters() {
        Style style = new Style("\u001b[32m");
        String result = style.apply("Hello\nWorld\t!");
        assertEquals("\u001b[32mHello\nWorld\t!\u001b[0m", result);
    }

    @Test
    @DisplayName("Test Style apply with Unicode characters")
    void applyUnicodeCharacters() {
        Style style = new Style("\u001b[33m");
        String result = style.apply("Hello 🌍 世界");
        assertEquals("\u001b[33mHello 🌍 世界\u001b[0m", result);
    }

    @Test
    @DisplayName("Test Style apply multiple times with same style")
    void applyMultipleTimes() {
        Style style = new Style("\u001b[34;1m");
        String result1 = style.apply("First");
        String result2 = style.apply("Second");
        String result3 = style.apply("Third");

        assertEquals("\u001b[34;1mFirst\u001b[0m", result1);
        assertEquals("\u001b[34;1mSecond\u001b[0m", result2);
        assertEquals("\u001b[34;1mThird\u001b[0m", result3);
    }

    @Test
    @DisplayName("Test Style with RGB color ANSI code")
    void applyRgbColor() {
        Style style = new Style("\u001b[38;2;255;128;0m");
        String result = style.apply("RGB Text");
        assertEquals("\u001b[38;2;255;128;0mRGB Text\u001b[0m", result);
    }

    @Test
    @DisplayName("Test Style with RGB foreground and background")
    void applyRgbForegroundAndBackground() {
        Style style = new Style("\u001b[38;2;255;0;0;48;2;0;255;0m");
        String result = style.apply("Colorful");
        assertEquals("\u001b[38;2;255;0;0;48;2;0;255;0mColorful\u001b[0m", result);
    }

    @Test
    @DisplayName("Test Style with all attributes combined")
    void applyAllAttributes() {
        Style style = new Style("\u001b[31;42;1;2;3;4;5;6;7;8m");
        String result = style.apply("Complex");
        assertEquals("\u001b[31;42;1;2;3;4;5;6;7;8mComplex\u001b[0m", result);
    }

    @Test
    @DisplayName("Test Style always appends reset sequence")
    void alwaysAppendsResetSequence() {
        Style style = new Style("\u001b[31m");
        String result = style.apply("Text");
        assertTrue(result.endsWith("\u001b[0m"));
    }

    @Test
    @DisplayName("Test Style with long text")
    void applyLongText() {
        Style style = new Style("\u001b[35m");
        String longText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                         "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        String result = style.apply(longText);
        assertEquals("\u001b[35m" + longText + "\u001b[0m", result);
    }

    @Test
    @DisplayName("Test Style with numeric text")
    void applyNumericText() {
        Style style = new Style("\u001b[36m");
        String result = style.apply("12345");
        assertEquals("\u001b[36m12345\u001b[0m", result);
    }

    @Test
    @DisplayName("Test Style with mixed alphanumeric and symbols")
    void applyMixedText() {
        Style style = new Style("\u001b[37m");
        String result = style.apply("Test123!@#$%^&*()");
        assertEquals("\u001b[37mTest123!@#$%^&*()\u001b[0m", result);
    }
}

