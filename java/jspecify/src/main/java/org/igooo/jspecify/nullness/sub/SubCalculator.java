package org.igooo.jspecify.nullness.sub;

import org.jspecify.annotations.NonNull;

public class SubCalculator {
    public Integer add(Integer a, Integer b) {
        return a + b;
    }

    public Integer minus(@NonNull Integer a, @NonNull Integer b) {
        return a + b;
    }
}
