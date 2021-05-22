package com.hakan.BookShop.service;

import com.hakan.BookShop.model.Book;
import com.hakan.BookShop.repository.BookRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookService {

  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public List<Book> allBooks() {
    return bookRepository.findAll();
  }

  public Book save(Book book) {
    return bookRepository.save(book);
  }

  public Book findByGivenId(Long id) throws ResponseStatusException {
    return bookRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public Book update(Book book) {
    return bookRepository.save(book);
  }

  public void delete(Long id) {
    bookRepository.deleteById(id);
  }
}
