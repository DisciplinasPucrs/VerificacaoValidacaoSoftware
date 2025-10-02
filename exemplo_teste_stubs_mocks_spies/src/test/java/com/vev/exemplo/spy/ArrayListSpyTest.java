package com.vev.exemplo.spy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat; 

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ArrayListSpyTest {
    @Spy
    private List<String> spyList = new ArrayList<>();

    @Test
    void addSpyOnArrayListTest() {
        doReturn("algo").when(spyList).get(0);
        assertEquals("algo", spyList.get(0));
        var resultado = spyList.add("um");
        assertTrue(resultado);
        assertThat(spyList).hasSize(1);
        verify(spyList).add("um");
        assertThat(spyList.get(0)).isEqualTo("um");
    }
}
