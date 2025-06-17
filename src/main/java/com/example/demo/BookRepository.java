package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

  private List<Book> books = new ArrayList<>();

  public void addBooks(Book book) {
    this.books.add(book);
  }

  public List<Book> findAll() {
    return this.books;
  }
}
