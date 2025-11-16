package io.github.bitmeshi.stylr;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnsiCodeGeneratorTest {
    @Test
    @DisplayName("Test with all style configurations empty")
    void emptyStyleConfig() {
        StyleConfig config = new StyleConfig(
                null, null,
                false, false,
                false, false,
                false, false,
                false, false
        );
        String ansiCode = AnsiCodeGenerator.getAnsiPrefix(config);
        assertEquals("", ansiCode);
    }

    @Test
    @DisplayName("Test with only red text color")
    void redTextColor() {
        StyleConfig config = new StyleConfig(
                BasicColor.RED, null,
                false, false,
                false, false,
                false, false,
                false, false
        );
        String ansiCode = AnsiCodeGenerator.getAnsiPrefix(config);
        assertEquals("\u001b[31m", ansiCode);
    }

    @Test
    @DisplayName("Test with only green background color")
    void greenBackgroundColor() {
        StyleConfig config = new StyleConfig(
                null, BasicColor.GREEN,
                false, false,
                false, false,
                false, false,
                false, false
        );
        String ansiCode = AnsiCodeGenerator.getAnsiPrefix(config);
        assertEquals("\u001b[42m", ansiCode);
    }

    @Test
    @DisplayName("Test with bold")
    void boldAttribute() {
        StyleConfig config = new StyleConfig(
                null, null,
                true, false,
                false, false,
                false, false,
                false, false
        );
        String ansiCode = AnsiCodeGenerator.getAnsiPrefix(config);
        assertEquals("\u001b[1m", ansiCode);
    }

    @Test
    @DisplayName("Test with dim")
    void dimAttribute() {
        StyleConfig config = new StyleConfig(
                null, null,
                false, true,
                false, false,
                false, false,
                false, false
        );
        String ansiCode = AnsiCodeGenerator.getAnsiPrefix(config);
        assertEquals("\u001b[2m", ansiCode);
    }

    @Test
    @DisplayName("Test with italic")
    void italicAttribute() {
        StyleConfig config = new StyleConfig(
                null, null,
                false, false,
                true, false,
                false, false,
                false, false
        );
        String ansiCode = AnsiCodeGenerator.getAnsiPrefix(config);
        assertEquals("\u001b[3m", ansiCode);
    }

    @Test
    @DisplayName("Test with underline")
    void underlineAttribute() {
        StyleConfig config = new StyleConfig(
                null, null,
                false, false,
                false, true,
                false, false,
                false, false
        );
        String ansiCode = AnsiCodeGenerator.getAnsiPrefix(config);
        assertEquals("\u001b[4m", ansiCode);
    }

    @Test
    @DisplayName("Test with slow blink")
    void slowBlinkAttribute() {
        StyleConfig config = new StyleConfig(
                null, null,
                false, false,
                false, false,
                true, false,
                false, false
        );
        String ansiCode = AnsiCodeGenerator.getAnsiPrefix(config);
        assertEquals("\u001b[5m", ansiCode);
    }

    @Test
    @DisplayName("Test with rapid blink")
    void rapidBlinkAttribute() {
        StyleConfig config = new StyleConfig(
                null, null,
                false, false,
                false, false,
                false, true,
                false, false
        );
        String ansiCode = AnsiCodeGenerator.getAnsiPrefix(config);
        assertEquals("\u001b[6m", ansiCode);
    }

    @Test
    @DisplayName("Test with reverse")
    void reverseAttribute() {
        StyleConfig config = new StyleConfig(
                null, null,
                false, false,
                false, false,
                false, false,
                true, false
        );
        String ansiCode = AnsiCodeGenerator.getAnsiPrefix(config);
        assertEquals("\u001b[7m", ansiCode);
    }

    @Test
    @DisplayName("Test with hide")
    void hideAttribute() {
        StyleConfig config = new StyleConfig(
                null, null,
                false, false,
                false, false,
                false, false,
                false, true
        );
        String ansiCode = AnsiCodeGenerator.getAnsiPrefix(config);
        assertEquals("\u001b[8m", ansiCode);
    }

    @Test
    @DisplayName("Test with multiple attributes")
    void multipleAttributes() {
        StyleConfig config = new StyleConfig(
                BasicColor.CYAN, null,
                true, false,
                true, false,
                false, false,
                true, false
        );
        String ansiCode = AnsiCodeGenerator.getAnsiPrefix(config);
        assertEquals("\u001b[36;1;3;7m", ansiCode);
    }

    @Test
    @DisplayName("Test with color and background color combination")
    void colorAndBackgroundCombination() {
        StyleConfig config = new StyleConfig(
                BasicColor.RED, BasicColor.GREEN,
                false, false,
                false, false,
                false, false,
                false, false
        );
        String ansiCode = AnsiCodeGenerator.getAnsiPrefix(config);
        assertEquals("\u001b[31;42m", ansiCode);
    }

    @Test
    @DisplayName("Test with color, background and attributes")
    void colorBackgroundAndAttributes() {
        StyleConfig config = new StyleConfig(
                BasicColor.BLUE, BasicColor.YELLOW,
                true, false,
                true, true,
                false, false,
                false, false
        );
        String ansiCode = AnsiCodeGenerator.getAnsiPrefix(config);
        assertEquals("\u001b[34;43;1;3;4m", ansiCode);
    }

    @Test
    @DisplayName("Test with bright colors")
    void brightColors() {
        StyleConfig config = new StyleConfig(
                BasicColor.BRIGHT_WHITE, BasicColor.BRIGHT_BLACK,
                false, false,
                false, false,
                false, false,
                false, false
        );
        String ansiCode = AnsiCodeGenerator.getAnsiPrefix(config);
        assertEquals("\u001b[97;100m", ansiCode);
    }

    @Test
    @DisplayName("Test with all attributes enabled")
    void allAttributesEnabled() {
        StyleConfig config = new StyleConfig(
                null, null,
                true, true,
                true, true,
                true, true,
                true, true
        );
        String ansiCode = AnsiCodeGenerator.getAnsiPrefix(config);
        assertEquals("\u001b[1;2;3;4;5;6;7;8m", ansiCode);
    }

    @Test
    @DisplayName("Test with all styles enabled")
    void allStylesEnabled() {
        StyleConfig config = new StyleConfig(
                BasicColor.MAGENTA, BasicColor.CYAN,
                true, true,
                true, true,
                true, true,
                true, true
        );
        String ansiCode = AnsiCodeGenerator.getAnsiPrefix(config);
        assertEquals("\u001b[35;46;1;2;3;4;5;6;7;8m", ansiCode);
    }

    @Test
    @DisplayName("Validate null color and bgColor are handled correctly")
    void nullColorValidation() {
        StyleConfig config = new StyleConfig(
                null, null,
                false, false,
                false, false,
                false, false,
                false, false
        );
        String ansiCode = AnsiCodeGenerator.getAnsiPrefix(config);
        assertEquals("", ansiCode);
    }

    @Test
    @DisplayName("Test ANSI prefix structure with expected format")
    void ansiPrefixStructure() {
        StyleConfig config = new StyleConfig(
                BasicColor.RED, null,
                true, false,
                false, false,
                false, false,
                false, false
        );
        String ansiCode = AnsiCodeGenerator.getAnsiPrefix(config);
        assertTrue(ansiCode.startsWith("\u001b["));
        assertTrue(ansiCode.endsWith("m"));
        assertTrue(ansiCode.contains("31")); // red color code
        assertTrue(ansiCode.contains("1"));  // bold attribute
    }
}