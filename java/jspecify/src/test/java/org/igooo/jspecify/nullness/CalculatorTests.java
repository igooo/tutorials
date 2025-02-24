package org.igooo.jspecify.nullness;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTests {
    @Test
    void add() {
        var calculator = new Calculator();
        assertEquals(3, calculator.add(1,2));
    }

    @Test
    void addWithNull    () {
        var calculator = new Calculator();
        assertEquals(2, calculator.add(null,2));
    }

    @Test
    void minus() {
        var calculator = new Calculator();
        assertEquals(2, calculator.minus(2,null));
    }
}