package org.example;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class TipServiceTest {
    TipService tipService = new TipService();

    @ParameterizedTest(name = "итерация #{index}")
    @DisplayName("Входящие значения: -1, 0, 999, 1000, 1001")
    @ValueSource(ints = {1, 0, 999, 1000, 1001})

    void positiveTestingWithNormalParams(int amount) {
        BigDecimal newAmount = BigDecimal.valueOf(amount);
        BigDecimal BOUNDARY = BigDecimal.valueOf(1000);
        BigDecimal TEN_PERCENT = BigDecimal.valueOf(1.1);
        BigDecimal FIVE_PERCENT = BigDecimal.valueOf(1.05);

        var result = tipService.roundTips(newAmount);
        var expected = BigDecimal.valueOf(0);

        if (newAmount.compareTo(BOUNDARY) < 0) {
            expected = newAmount.multiply(TEN_PERCENT);
        } else {
            expected = newAmount.multiply(FIVE_PERCENT);;
        }
        assertEquals (expected, result);

    }
        @ParameterizedTest
        @DisplayName("Входящие значение Null")
        @NullSource
        void negativeTestingWithNull(BigDecimal amount){

            assertThrows(NullPointerException.class,() -> tipService.roundTips(amount));

        }
}
