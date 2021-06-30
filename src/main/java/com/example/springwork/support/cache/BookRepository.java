package com.example.springwork.support.cache;

public interface BookRepository {
    Book getByIsbn(String isbn);
  }