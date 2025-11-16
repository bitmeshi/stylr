package io.github.bitmeshi.stylr;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StylrTest {

    @Test
    @DisplayName("Test Stylr.of() creates StyleBuilder")
    void createStyleBuilder() {
        StyleBuilder builder = Stylr.of("Hello");
        assertNotNull(builder);
        String result = builder.render();
        assertEquals("Hello\u001b[0m", result);
    }

    @Test
    @DisplayName("Test Stylr.of() with null text throws exception")
    void nullTextThrowsException() {
        assertThrows(NullPointerException.class, () -> Stylr.of(null));
    }

    @Test
    @DisplayName("Test Stylr.of() with empty text")
    void emptyTextCreatesBuilder() {
        StyleBuilder builder = Stylr.of("");
        assertNotNull(builder);
        String result = builder.render();
        assertEquals("", result);
    }

    @Test
    @DisplayName("Test Stylr cannot be instantiated")
    void cannotInstantiate() {
        // Using reflection to test private constructor
        assertThrows(Exception.class, () -> {
            try {
                var constructor = Stylr.class.getDeclaredConstructor();
                constructor.setAccessible(true);
                constructor.newInstance();
            } catch (Exception e) {
                if (e.getCause() instanceof IllegalStateException cause) {
                    throw cause;
                }
                throw e;
            }
        });
    }

    @Test
    @DisplayName("Test fluent API with Stylr.of()")
    void fluentApiWithStylr() {
        String result = Stylr.of("Hello World")
                .color(BasicColor.GREEN)
                .bold()
                .italic()
                .render();
        assertEquals("\u001b[32;1;3mHello World\u001b[0m", result);
    }

    @Test
    @DisplayName("Test Stylr.of() with special characters")
    void specialCharacters() {
        String result = Stylr.of("Hello\nWorld\t!")
                .color(BasicColor.CYAN)
                .render();
        assertEquals("\u001b[36mHello\nWorld\t!\u001b[0m", result);
    }

    @Test
    @DisplayName("Test Stylr.of() with Unicode characters")
    void unicodeCharacters() {
        String result = Stylr.of("Hello 🌍 世界")
                .color(BasicColor.YELLOW)
                .render();
        assertEquals("\u001b[33mHello 🌍 世界\u001b[0m", result);
    }
}
