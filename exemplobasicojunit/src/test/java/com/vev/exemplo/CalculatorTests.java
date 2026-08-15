package com.vev.exemplo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculatorTests {
	@Test
	@DisplayName("1 + 1 = 2")
	void addsTwoNumbers() {
		//arrange
		Calculator calculator = new Calculator();
		int a = 1;
		int b = 1;
		int expected = 2;
		//act
		int result = calculator.add(a, b);
		//assert
		assertEquals(expected, result, "1 + 1 should equal 2");
	}
	
	@ParameterizedTest(name = "{0} + {1} = {2}")
	@CsvSource({
			"0,    1,   1",
			"1,    2,   3",
			"49,  51, 100",
			"1,  100, 101"
	})
	void add(int first, int second, int expectedResult) {
		Calculator calculator = new Calculator();
		assertEquals(expectedResult, calculator.add(first, second),
				() -> first + " + " + second + " should equal " + expectedResult);
	}

	@Test
	void addsTwoNumbersWithDefect() {
		Calculator calculator = new Calculator();
		long r = Integer.MAX_VALUE + 1L;
		assertEquals(r, calculator.add(Integer.MAX_VALUE, 1));
	}
}
