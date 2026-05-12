package com.vev.exemplo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.IntRange;

public class FizzBuzzPBTest {
    @Property
    boolean exclamation(@ForAll("notDivisibleBy3or5") int i) {
        return FizzBuzz.fizzbuzz(i).equals(i+"!");
    }

    @Property
    boolean fizz(@ForAll("divisibleBy3ButNot5") int i) {
        return FizzBuzz.fizzbuzz(i).equals("Fizz!");
    }

    @Property
    boolean buzz(@ForAll("divisibleBy5ButNot3") int i) {
        return FizzBuzz.fizzbuzz(i).equals("Buzz!");
    }

    @Property
    boolean fizzbuzz(@ForAll("divisibleBy3and5") int i) {
        return FizzBuzz.fizzbuzz(i).equals("FizzBuzz!");
    }

    @Property
    void noZeroesAndNegatives(@ForAll @IntRange(max = 0) int i) {
        assertThatThrownBy(() -> FizzBuzz.fizzbuzz(i))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Provide
    Arbitrary<Integer> divisibleBy3ButNot5() {
        return Arbitraries.integers()
                .between(1, 10000)
                .map(n -> n * 3)
                .filter(i -> i % 5 != 0);
    }

    @Provide
    Arbitrary<Integer> divisibleBy5ButNot3() {
        return Arbitraries.integers()
                .between(1, 10000)
                .map(n -> n * 5)
                .filter(i -> i % 3 != 0);
    }

    @Provide
    Arbitrary<Integer> divisibleBy3and5() {
        return Arbitraries.integers()
                .between(1, 10000)
                .map(n -> n * 15);
    }

    @Provide
    Arbitrary<Integer> notDivisibleBy3or5() {
        return Arbitraries.integers()
                .between(1, 10000)
                .map(n -> (n * 15) - 1);
    }
}