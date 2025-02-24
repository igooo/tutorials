package org.igooo.jspecify.nullness.sub;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubCalculatorTests {
    @Test
    void add() {
        var subCalculator = new SubCalculator();
        assertEquals(3, subCalculator.add(1, 2));
    }

    @Test
    void addWithNull() {
        var subCalculator = new SubCalculator();
        assertEquals(2, subCalculator.add(null, 2));
    }

    @Test
    void minus() {
        var subCalculator = new SubCalculator();
        assertEquals(2, subCalculator.minus(2, null));
    }
}