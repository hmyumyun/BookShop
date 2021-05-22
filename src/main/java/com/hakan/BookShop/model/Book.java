package com.hakan.BookShop.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Book {

  @Id
  @GeneratedValue
  private long id;
  @NotNull
  private String title;
  @NotNull
  private String author;
  private String genre;
  private float price;

  public long getId() {
    return id;
  }

  public Book setId(long id) {
    this.id = id;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public Book setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getAuthor() {
    return author;
  }

  public Book setAuthor(String author) {
    this.author = author;
    return this;
  }

  public String getGenre() {
    return genre;
  }

  public Book setGenre(String genre) {
    this.genre = genre;
    return this;
  }

  public float getPrice() {
    return price;
  }

  public Book setPrice(float price) {
    this.price = price;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Book book = (Book) o;
    return id == book.id && Float.compare(book.price, price) == 0 && Objects
        .equals(title, book.title) && Objects.equals(author, book.author)
        && Objects.equals(genre, book.genre);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, author, genre, price);
  }

  @Override
  public String toString() {
    return "Book{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", author='" + author + '\'' +
        ", genre='" + genre + '\'' +
        ", price=" + price +
        '}';
  }
}
