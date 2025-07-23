package com.calvin.bookstore.bookstore_javaspring.service;


import com.calvin.bookstore.bookstore_javaspring.dto.BookRequest;
import com.calvin.bookstore.bookstore_javaspring.dto.BookResponse;
import com.calvin.bookstore.bookstore_javaspring.model.Book;
import com.calvin.bookstore.bookstore_javaspring.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Generates a constructor with required arguments (final fields)
public class BookService {

    private final BookRepository bookRepository; // Injects the BookRepository dependency

    public BookResponse createBook(BookRequest bookRequest) {
        // Convert BookRequest to Book entity
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setIsbn(bookRequest.getIsbn());
        book.setPrice(bookRequest.getPrice());

        // Save the book entity to the database
        Book savedBook = bookRepository.save(book);

        // Convert saved Book entity to BookResponse DTO
        return new BookResponse(savedBook.getId(), savedBook.getTitle(), savedBook.getAuthor(), savedBook.getIsbn(), savedBook.getPrice());
    }

    public BookResponse getBookById(Long id) {
        // Find the book by ID
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        // Convert Book entity to BookResponse DTO
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPrice());
    }

    public List<BookResponse> getAllBooks() {
        // Retrieve all books from the repository
        List<Book> books = bookRepository.findAll();

        // Convert List<Book> to List<BookResponse>
        return books.stream()
                .map(book -> new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPrice()))
                .collect(Collectors.toList());
    }

    public BookResponse updateBook(Long id, BookRequest bookRequest) {
        // Find the book by ID
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        // Update the book entity with new values
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setIsbn(bookRequest.getIsbn());
        book.setPrice(bookRequest.getPrice());

        // Save the updated book entity to the database
        Book updatedBook = bookRepository.save(book);

        // Convert updated Book entity to BookResponse DTO
        return new BookResponse(updatedBook.getId(), updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getIsbn(), updatedBook.getPrice());

//        return mapToBookResponse(savedBook);

    }

    public void deleteBook(Long id) {
        // Check if the book exists
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }

        // Delete the book by ID
        bookRepository.deleteById(id);
    }

    public void deleteAllBooks() {
        // Delete all books from the repository
        bookRepository.deleteAll();
    }

    public boolean bookExists(Long id) {
        // Check if the book exists by ID
        return bookRepository.existsById(id);
    }

    public long countBooks() {
        // Count the total number of books in the repository
        return bookRepository.count();
    }


    private BookResponse mapToBookResponse(Book book) {
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPrice());
    }

}
