package com.hakan.BookShop.repository;

import com.hakan.BookShop.model.Book;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BookRepository  extends JpaRepository<Book,Long> {

  Optional<Book> findById(Long id);

}
