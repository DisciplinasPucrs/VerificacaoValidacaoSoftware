package com.vev;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RomanNumeralTest {
    RomanNumeral romanNumeral = new RomanNumeral();

    @ParameterizedTest
    @CsvSource({
        "I,1",
        "V,5",
        "X,10",
        "L,50",
        "C,100",
        "D,500",
        "M,1000"
    })
    void testSingleNumerals(String input, int expected) {
        assertEquals(expected, romanNumeral.convert(input));
    }

    @ParameterizedTest
    @CsvSource({
        "II,2",
        "III,3",
        "VI,6",
        "VII,7",
        "VIII,8"
    })
    void testSimpleCombinations(String input, int expected) {
        assertEquals(expected, romanNumeral.convert(input));
    }

    @ParameterizedTest
    @CsvSource({
        "IV,4",
        "IX,9",
        "XL,40",
        "XC,90",
        "CD,400",
        "CM,900"
    })
    void testSubtractiveNotation(String input, int expected) {
        assertEquals(expected, romanNumeral.convert(input));
    }

    @ParameterizedTest
    @CsvSource({
        "LVIII,58",
        "MCMXCIV,1994",
        "MMXXIII,2023"
    })
    void testComplexNumbers(String input, int expected) {
        assertEquals(expected, romanNumeral.convert(input));
    }

    @ParameterizedTest
    @CsvSource({
        "' ',0"
    })
    void testEmptyString(String input, int expected) {
        assertEquals(expected, romanNumeral.convert(input.trim()));
    }

    @Test
    void testInvalidCharacter() {
        assertThrows(NullPointerException.class, () -> romanNumeral.convert("A"));
    }
}
