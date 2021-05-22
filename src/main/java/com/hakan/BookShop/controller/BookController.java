package com.hakan.BookShop.controller;
import com.hakan.BookShop.model.Book;
import com.hakan.BookShop.service.BookService;
import java.util.List;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/")
  public String books(Model model) {
    List<Book> books = bookService.allBooks();
    model.addAttribute("books", books);
    model.addAttribute("book", new Book());
    model.addAttribute("title", "Books");
    model.addAttribute("isAdd", true);
    return "book";
  }

  @PostMapping("/save")
  public String save(@ModelAttribute Book book) {
    bookService.save(book);
    return "redirect:/";
  }

  @GetMapping("/getBook/{id}")
  public String getBook(@PathVariable Long id, Model model) throws NotFoundException {
    Book book = bookService.findByGivenId(id);
    List<Book> books = bookService.allBooks();
    model.addAttribute("books", books);
    model.addAttribute("book", book);
    model.addAttribute("title", "Edit Books");
    model.addAttribute("isAdd", false);
    return "book";

  }

  @PostMapping("/update")
  public String update(@ModelAttribute Book book) {
    bookService.update(book);
    return "redirect:/";
  }

  @GetMapping(value = "/deleteBook/{id}")
  public String delete(@PathVariable Long id) {
    bookService.delete(id);
    return "redirect:/";
  }
}
