package io.github.bitmeshi.stylr;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StylrTest {
    @Test
    @DisplayName("Test Stylr.of() creates TextStyler")
    void createTextStyler() {
        TextStyler builder = Stylr.of("Hello");
        assertNotNull(builder);
    }

    @Test
    @DisplayName("Test Stylr.of() with null text throws exception")
    void nullTextThrowsException() {
        assertThrows(NullPointerException.class, () -> Stylr.of(null));
    }

    @Test
    @DisplayName("Test Stylr.of() with empty text")
    void emptyTextCreatesBuilder() {
        TextStyler builder = Stylr.of("");
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

    @Test
    @DisplayName("Test Stylr.style() creates StyleDefinitionBuilder")
    void createStyleDefinitionBuilder() {
        StyleDefinitionBuilder builder = Stylr.style();
        assertNotNull(builder);
    }

    @Test
    @DisplayName("Test Stylr.style() creates reusable Style")
    void createReusableStyle() {
        Style style = Stylr.style()
                .color(BasicColor.RED)
                .bold()
                .build();

        String result1 = style.apply("First");
        String result2 = style.apply("Second");

        assertEquals("\u001b[31;1mFirst\u001b[0m", result1);
        assertEquals("\u001b[31;1mSecond\u001b[0m", result2);
    }

    @Test
    @DisplayName("Test Stylr.style() with fluent API")
    void fluentApiWithStyle() {
        String result = Stylr.style()
                .color(BasicColor.CYAN)
                .italic()
                .build()
                .apply("Stylish Text");
        assertEquals("\u001b[36;3mStylish Text\u001b[0m", result);
    }

    @Test
    @DisplayName("Test Stylr.style() vs Stylr.of() behavior")
    void styleVsOfBehavior() {
        // Stylr.style() creates reusable Style
        Style reusableStyle = Stylr.style()
                .color(BasicColor.GREEN)
                .build();
        String result1 = reusableStyle.apply("Text1");
        String result2 = reusableStyle.apply("Text2");

        // Stylr.of() applies style directly to text
        String result3 = Stylr.of("Text3")
                .color(BasicColor.GREEN)
                .render();

        assertEquals("\u001b[32mText1\u001b[0m", result1);
        assertEquals("\u001b[32mText2\u001b[0m", result2);
        assertEquals("\u001b[32mText3\u001b[0m", result3);
    }
}
