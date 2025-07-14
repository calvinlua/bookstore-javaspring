package com.calvin.bookstore.bookstore_javaspring.repository;

import com.calvin.bookstore.bookstore_javaspring.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // This interface will automatically provide Database CRUD operations like CRUDRepository for the Book entity
    // Spring Data JPA automatically provides methods like save(), findById(), findAll(), deleteById()
    // No additional methods are needed unless you want to define custom queries
}
