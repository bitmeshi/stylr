package io.github.bitmeshi.stylr;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StyleBuilderTest {

    private StyleBuilder styleWithText;

    @BeforeAll
    void setup() {
        styleWithText = Stylr.of("Sample Text");
    }

    @Test
    @DisplayName("Test with of method with null text")
    void testOfMethodWithNullText() {
        Exception exception = assertThrows(NullPointerException.class, () -> Stylr.of(null));
        String expectedMessage = "Text cannot be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Test with of method and render method")
    void testOfMethodAndRender() {
        String text = styleWithText.bold().render();
        assertEquals("\u001B[1mSample Text\u001B[0m", text);
    }
}