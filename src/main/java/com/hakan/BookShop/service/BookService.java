package com.hakan.BookShop.service;

import com.hakan.BookShop.model.Book;
import com.hakan.BookShop.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

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

  public Book findByGivenId(Long id) throws NotFoundException {
    Optional<Book> book = bookRepository.findById(id);
    if (book.isPresent()) {
      return book.get();
    } else {
      throw new NotFoundException("Element with given id is not found");
    }
  }

  public Book update(Book book) {
    return bookRepository.save(book);
  }

  public void delete(Long id) {
    bookRepository.deleteById(id);
  }
}
