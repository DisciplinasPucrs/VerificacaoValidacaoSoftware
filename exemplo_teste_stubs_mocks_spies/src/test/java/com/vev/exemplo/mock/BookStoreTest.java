package com.vev.exemplo.mock;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Mockito.*;

class BookStoreTest {

    @Test
    void emptyOrder() {
        BookRepository bookRepo = mock(BookRepository.class);
        BuyBookProcess process = mock(BuyBookProcess.class);
        BookStore bookStore = new BookStore(bookRepo, process);

        Map<String, Integer> orderMap = new HashMap<>();
        Overview overview = bookStore.getPriceForCart(orderMap);

        assertThat(overview.getTotalPrice()).isZero();
        assertThat(overview.getUnavailable()).isEmpty();
    }

    @Test
    void nullOrder() {
        BookRepository bookRepo = mock(BookRepository.class);
        BuyBookProcess process = mock(BuyBookProcess.class);
        BookStore bookStore = new BookStore(bookRepo, process);

        Overview overview = bookStore.getPriceForCart(null);

        assertThat(overview).isNull();
    }

    @Test
    void moreComplexOrder() {
        BookRepository bookRepo = mock(BookRepository.class);
        BuyBookProcess process = mock(BuyBookProcess.class);

        Map<String, Integer> orderMap = new HashMap<>();

        /**
         * Criando 3 livros:
         * - um onde a quantidade é suficiente para o pedido
         * - um onde a quantidade é precisamente a que foi solicitada no pedido
         * - um onde a quantidade é insuficiente para o pedido
         */
        orderMap.put("PRODUCT-ENOUGH-QTY", 5);
        orderMap.put("PRODUCT-PRECISE-QTY", 10);
        orderMap.put("PRODUCT-NOT-ENOUGH", 22);

        Book book1 = new Book("PRODUCT-ENOUGH-QTY", 20, 11); // 11 is more than 5
        when(bookRepo.findByISBN("PRODUCT-ENOUGH-QTY")).thenReturn(book1);
        Book book2 = new Book("PRODUCT-PRECISE-QTY", 25, 10); // 10 == 10
        when(bookRepo.findByISBN("PRODUCT-PRECISE-QTY")).thenReturn(book2);
        Book book3 = new Book("PRODUCT-NOT-ENOUGH", 37, 21);  // 21 < 22
        when(bookRepo.findByISBN("PRODUCT-NOT-ENOUGH")).thenReturn(book3);

        BookStore bookStore = new BookStore(bookRepo, process);
        Overview overview = bookStore.getPriceForCart(orderMap);

        // Garantir que o preço total é correto
        int expectedPrice =
                5*20 + // from the first product
                10*25 + // from the second product
                21*37; // from the third product

        assertThat(overview.getTotalPrice()).isEqualTo(expectedPrice);

        // Garantir que o processo de compra foi executado
        verify(process).buyBook(book1, 5);
        verify(process).buyBook(book2, 10);
        verify(process).buyBook(book3, 21);

        // Garantir que a lista de livros indisponíveis está correta
        assertThat(overview.getUnavailable())
                .containsExactly(entry(book3, 1));
    }

}
