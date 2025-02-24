package org.igooo.jspecify.nullness;

import org.jspecify.annotations.Nullable;

public class Calculator {
    public Integer add(Integer a, Integer b) {
        return a + b;
    }

    public Integer minus(Integer a, @Nullable Integer b) {
        return a + (b == null ? 0 : b);
    }
}
