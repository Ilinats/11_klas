package test;

import main.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void testSum() {
        int result = Calculator.add(2, 3);
        Assertions.assertEquals(5, result);
    }
}
