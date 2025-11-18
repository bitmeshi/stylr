package io.github.bitmeshi.stylr;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BasicColorTest {


    @Test
    @DisplayName("Test all basic colors for text")
    void testAllBasicTextColors() {
        assertEquals("30", BasicColor.BLACK.getAnsiCode(false));
        assertEquals("31", BasicColor.RED.getAnsiCode(false));
        assertEquals("32", BasicColor.GREEN.getAnsiCode(false));
        assertEquals("33", BasicColor.YELLOW.getAnsiCode(false));
        assertEquals("34", BasicColor.BLUE.getAnsiCode(false));
        assertEquals("35", BasicColor.MAGENTA.getAnsiCode(false));
        assertEquals("36", BasicColor.CYAN.getAnsiCode(false));
        assertEquals("37", BasicColor.WHITE.getAnsiCode(false));
    }

    @Test
    @DisplayName("Test all basic colors for background")
    void testAllBasicBackgroundColors() {
        assertEquals("40", BasicColor.BLACK.getAnsiCode(true));
        assertEquals("41", BasicColor.RED.getAnsiCode(true));
        assertEquals("42", BasicColor.GREEN.getAnsiCode(true));
        assertEquals("43", BasicColor.YELLOW.getAnsiCode(true));
        assertEquals("44", BasicColor.BLUE.getAnsiCode(true));
        assertEquals("45", BasicColor.MAGENTA.getAnsiCode(true));
        assertEquals("46", BasicColor.CYAN.getAnsiCode(true));
        assertEquals("47", BasicColor.WHITE.getAnsiCode(true));
    }

    @Test
    @DisplayName("Test all bright colors for text")
    void testAllBrightTextColors() {
        assertEquals("90", BasicColor.BRIGHT_BLACK.getAnsiCode(false));
        assertEquals("91", BasicColor.BRIGHT_RED.getAnsiCode(false));
        assertEquals("92", BasicColor.BRIGHT_GREEN.getAnsiCode(false));
        assertEquals("93", BasicColor.BRIGHT_YELLOW.getAnsiCode(false));
        assertEquals("94", BasicColor.BRIGHT_BLUE.getAnsiCode(false));
        assertEquals("95", BasicColor.BRIGHT_MAGENTA.getAnsiCode(false));
        assertEquals("96", BasicColor.BRIGHT_CYAN.getAnsiCode(false));
        assertEquals("97", BasicColor.BRIGHT_WHITE.getAnsiCode(false));
    }

    @Test
    @DisplayName("Test all bright colors for background")
    void testAllBrightBackgroundColors() {
        assertEquals("100", BasicColor.BRIGHT_BLACK.getAnsiCode(true));
        assertEquals("101", BasicColor.BRIGHT_RED.getAnsiCode(true));
        assertEquals("102", BasicColor.BRIGHT_GREEN.getAnsiCode(true));
        assertEquals("103", BasicColor.BRIGHT_YELLOW.getAnsiCode(true));
        assertEquals("104", BasicColor.BRIGHT_BLUE.getAnsiCode(true));
        assertEquals("105", BasicColor.BRIGHT_MAGENTA.getAnsiCode(true));
        assertEquals("106", BasicColor.BRIGHT_CYAN.getAnsiCode(true));
        assertEquals("107", BasicColor.BRIGHT_WHITE.getAnsiCode(true));
    }

    @Test
    @DisplayName("Test color enum values consistency")
    void testColorEnumValues() {
        BasicColor[] colors = BasicColor.values();
        assertEquals(16, colors.length); // 8 basic + 8 bright colors

        // Verify that we have all expected colors
        assertNotNull(BasicColor.valueOf("BLACK"));
        assertNotNull(BasicColor.valueOf("BRIGHT_WHITE"));
    }
}