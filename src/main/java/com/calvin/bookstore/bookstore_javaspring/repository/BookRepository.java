package com.calvin.bookstore.bookstore_javaspring.repository;

import com.calvin.bookstore.bookstore_javaspring.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

//First param specified Model/Entity and second params as type of primary key
public interface BookRepository extends JpaRepository<Book,Long> {
    // Spring Data JPA automatically provides methods like save(), findById(), findAll(), deleteById() for database operations
}
