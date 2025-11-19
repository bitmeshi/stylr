package io.github.bitmeshi.stylr.internal.color;

/**
 * Represents an RGB color with red, green, and blue components.
 * <p>
 * This record encapsulates RGB color values with validation to ensure
 * all components are within the valid range of 0-255. It provides
 * utility methods for creating RGB colors from hexadecimal strings.
 * <p>
 * This class is part of the internal API and should not be used directly
 * by library consumers.
 *
 * @param r the red component (0-255)
 * @param g the green component (0-255)
 * @param b the blue component (0-255)
 * @since 1.0
 */
public record Rgb(int r, int g, int b) {
    public Rgb {
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
            throw new IllegalArgumentException("RGB values must be between 0 and 255");
        }
    }

    /**
     * Creates an RGB color from a hexadecimal color string.
     * <p>
     * Supports both 3-digit and 6-digit hexadecimal formats:
     * <ul>
     *   <li>3-digit: {@code #RGB} (each digit is doubled, e.g., #F00 = #FF0000)</li>
     *   <li>6-digit: {@code #RRGGBB}</li>
     * </ul>
     *
     * @param hex the hexadecimal color string (e.g., "#FF0000" or "#F00")
     * @return a new Rgb instance with the parsed color values
     * @throws IllegalArgumentException if the format is invalid or contains non-hex characters
     */
    public static Rgb fromHex(String hex) throws IllegalArgumentException {
        if (!hex.startsWith("#") || (hex.length() != 4 && hex.length() != 7)) {
            throw new IllegalArgumentException("Invalid hexadecimal color format, expected #RGB or #RRGGBB. Got: " + hex);
        }

        // Validate that all characters after # are valid hexadecimal characters (0-9, a-f, A-F)
        String hexPart = hex.substring(1);
        if (!hexPart.matches("[0-9a-fA-F]+")) {
            throw new IllegalArgumentException("Invalid hexadecimal color value: " + hex);
        }

        if (hex.length() == 4) {
            char red = hex.charAt(1);
            char green = hex.charAt(2);
            char blue = hex.charAt(3);

            int r = Integer.parseInt("" + red + red, 16);
            int g = Integer.parseInt("" + green + green, 16);
            int b = Integer.parseInt("" + blue + blue, 16);

            return new Rgb(r, g, b);
        }

        String red = hex.substring(1, 3);
        String green = hex.substring(3, 5);
        String blue = hex.substring(5, 7);

        int r = Integer.parseInt(red, 16);
        int g = Integer.parseInt(green, 16);
        int b = Integer.parseInt(blue, 16);

        return new Rgb(r, g, b);
    }
}
