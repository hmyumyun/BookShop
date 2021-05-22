package com.hakan.BookShop.controller;

import com.hakan.BookShop.model.Book;
import com.hakan.BookShop.service.BookService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1/books")
public class BooksRestContoller {

  private final BookService bookService;

  public BooksRestContoller(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public List<Book> listBooks() {
    return bookService.allBooks();
  }

  @PostMapping
  public Book createBook(@Valid @RequestBody Book book) {
    return bookService.save(book);
  }

  @GetMapping("/{id}")
  public Book getBook(@PathVariable Long id) {
    return bookService.findByGivenId(id);
  }

  @PostMapping("/{id}")
  public Book update(@PathVariable Long id, @Valid @RequestBody Book book) {
    book.setId(id);
    return bookService.update(book);

  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    bookService.delete(id);
  }

}
