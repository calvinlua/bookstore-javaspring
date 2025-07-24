package com.calvin.bookstore.bookstore_javaspring.controller;


import com.calvin.bookstore.bookstore_javaspring.dto.BookRequest;
import com.calvin.bookstore.bookstore_javaspring.dto.BookResponse;
import com.calvin.bookstore.bookstore_javaspring.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse createBook(@RequestBody BookRequest bookRequest ){
        return bookService.createBook(bookRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponse> getAllBooks(){
        return bookService.getAllBooks();
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id){
        return bookService.getBookById(id)
                .map(bookResponse -> new ResponseEntity<>(bookResponse,HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id , @RequestBody BookRequest bookRequest){
        try{
            BookResponse updatedBook = bookService.updateBook(id,bookRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }

}
