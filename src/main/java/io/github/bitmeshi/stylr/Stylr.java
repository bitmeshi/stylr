package io.github.bitmeshi.stylr;

import java.util.Objects;

public final class Stylr {

    private Stylr() {
        throw new IllegalStateException("Utility class cannot be instantiated");
    }

    public static StyleBuilder of(String text) {
        Objects.requireNonNull(text, "Text cannot be null");
        return new StyleBuilder(text);
    }
}
