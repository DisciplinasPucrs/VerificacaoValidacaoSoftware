package com.vev.exemplo.mock;

public interface BookRepository {
    Book findByISBN(String ISBN);
}