package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public void failingMethod() {
    throw new RuntimeException("Oh la boulette !");
  }

  public void notFailingMethod() {
  }

  public void addBook(Book book) {
    this.bookRepository.addBooks(book);
  }

  public List<Book> getBooks() {
    return this.bookRepository.findAll();
  }
}
