package io.github.bitmeshi.stylr.internal.color;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RgbTest {

    @Test
    @DisplayName("Test fromHex with 6-digit format (#RRGGBB)")
    void testFromHexSixDigit() {
        Rgb rgb = Rgb.fromHex("#FF0000");
        assertEquals(255, rgb.r());
        assertEquals(0, rgb.g());
        assertEquals(0, rgb.b());

        Rgb rgb2 = Rgb.fromHex("#00FF00");
        assertEquals(0, rgb2.r());
        assertEquals(255, rgb2.g());
        assertEquals(0, rgb2.b());

        Rgb rgb3 = Rgb.fromHex("#0000FF");
        assertEquals(0, rgb3.r());
        assertEquals(0, rgb3.g());
        assertEquals(255, rgb3.b());
    }

    @Test
    @DisplayName("Test fromHex with 3-digit format (#RGB)")
    void testFromHexThreeDigit() {
        Rgb rgb = Rgb.fromHex("#F00");
        assertEquals(255, rgb.r());
        assertEquals(0, rgb.g());
        assertEquals(0, rgb.b());

        Rgb rgb2 = Rgb.fromHex("#0F0");
        assertEquals(0, rgb2.r());
        assertEquals(255, rgb2.g());
        assertEquals(0, rgb2.b());

        Rgb rgb3 = Rgb.fromHex("#00F");
        assertEquals(0, rgb3.r());
        assertEquals(0, rgb3.g());
        assertEquals(255, rgb3.b());
    }

    @Test
    @DisplayName("Test fromHex with mixed values")
    void testFromHexMixedValues() {
        Rgb rgb = Rgb.fromHex("#FF8040");
        assertEquals(255, rgb.r());
        assertEquals(128, rgb.g());
        assertEquals(64, rgb.b());

        Rgb rgb2 = Rgb.fromHex("#ABC");
        assertEquals(170, rgb2.r()); // A = 10, AA = 170
        assertEquals(187, rgb2.g()); // B = 11, BB = 187
        assertEquals(204, rgb2.b()); // C = 12, CC = 204
    }

    @Test
    @DisplayName("Test fromHex with lowercase letters")
    void testFromHexLowercase() {
        Rgb rgb = Rgb.fromHex("#ff0000");
        assertEquals(255, rgb.r());
        assertEquals(0, rgb.g());
        assertEquals(0, rgb.b());

        Rgb rgb2 = Rgb.fromHex("#abc");
        assertEquals(170, rgb2.r());
        assertEquals(187, rgb2.g());
        assertEquals(204, rgb2.b());
    }

    @Test
    @DisplayName("Test fromHex with mixed case")
    void testFromHexMixedCase() {
        Rgb rgb = Rgb.fromHex("#AbC");
        assertEquals(170, rgb.r());
        assertEquals(187, rgb.g());
        assertEquals(204, rgb.b());

        Rgb rgb2 = Rgb.fromHex("#FfAa00");
        assertEquals(255, rgb2.r());
        assertEquals(170, rgb2.g());
        assertEquals(0, rgb2.b());
    }

    @Test
    @DisplayName("Test fromHex with boundary values")
    void testFromHexBoundaryValues() {
        // Minimum values (000000)
        Rgb rgb1 = Rgb.fromHex("#000000");
        assertEquals(0, rgb1.r());
        assertEquals(0, rgb1.g());
        assertEquals(0, rgb1.b());

        // Maximum values (FFFFFF)
        Rgb rgb2 = Rgb.fromHex("#FFFFFF");
        assertEquals(255, rgb2.r());
        assertEquals(255, rgb2.g());
        assertEquals(255, rgb2.b());

        // Minimum 3-digit (000)
        Rgb rgb3 = Rgb.fromHex("#000");
        assertEquals(0, rgb3.r());
        assertEquals(0, rgb3.g());
        assertEquals(0, rgb3.b());

        // Maximum 3-digit (FFF)
        Rgb rgb4 = Rgb.fromHex("#FFF");
        assertEquals(255, rgb4.r());
        assertEquals(255, rgb4.g());
        assertEquals(255, rgb4.b());
    }

    @Test
    @DisplayName("Test fromHex throws exception for invalid format")
    void testFromHexInvalidFormat() {
        // Missing # prefix
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("FF0000"));
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("ABC"));

        // Wrong length
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("#FF"));
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("#FFFF"));
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("#FFFFF"));
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("#FFFFFFF"));

        // Empty string
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex(""));
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("#"));

        // Only # with no hex digits
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("#"));
    }

    @Test
    @DisplayName("Test fromHex throws exception for invalid hex characters")
    void testFromHexInvalidHexCharacters() {
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("#GGGGGG"));
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("#XYZ"));
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("#12345G"));
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("#FF00ZZ"));
    }

    @Test
    @DisplayName("Test fromHex with empty input")
    void testFromHexEmpty() {
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex(""));
    }

    @Test
    @DisplayName("Test fromHex exception messages")
    void testFromHexExceptionMessages() {
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
            () -> Rgb.fromHex("FF0000"));
        assertTrue(exception1.getMessage().contains("Invalid hexadecimal color format"));
        assertTrue(exception1.getMessage().contains("FF0000"));

        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
            () -> Rgb.fromHex("#GGGGGG"));
        assertTrue(exception2.getMessage().contains("Invalid hexadecimal color value"));
        assertTrue(exception2.getMessage().contains("#GGGGGG"));

        IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class,
            () -> Rgb.fromHex("#FFFF"));
        assertTrue(exception3.getMessage().contains("Invalid hexadecimal color format"));
        assertTrue(exception3.getMessage().contains("#FFFF"));
    }

    @Test
    @DisplayName("Test fromHex throws exception for various invalid cases")
    void testFromHexVariousInvalidCases() {
        // Test cases with non-hexadecimal characters
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("#12345G"));
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("#FF00ZZ"));

        // Additional test cases with spaces and special characters
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("#12 456"));
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("#AB CD"));
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("#A B"));
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("#1-2"));
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("# 12345"));
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("#12345 "));

        // Test with other non-hexadecimal characters
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("#GHIJKL"));
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("#12@456"));
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("#FF&00A"));
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("#12+456"));
        assertThrows(IllegalArgumentException.class, () -> Rgb.fromHex("#FF*00A"));
    }
}
