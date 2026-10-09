package com.vev.projeto;

import org.junit.jupiter.api.Test;

import com.vev.exemplo.CountWords;

import static org.assertj.core.api.Assertions.assertThat;

class CountWordsTest {
    @Test
    void t0() {
        int words = new CountWords().count("dogs");
        assertThat(words).isEqualTo(1);
    }
/* 
    @Test
    void t1() {
        int words = new CountWords().count("dogs cats");
        assertThat(words).isEqualTo(2);
    }
    
    @Test
    void t2() {
        int words = new CountWords().count("dog cat");
        assertThat(words).isZero();
    }
*/    
    /*
    @Test
    void t3() {
        int words = new CountWords().count("car bar");
        assertThat(words).isEqualTo(2);
    }
    */
}
