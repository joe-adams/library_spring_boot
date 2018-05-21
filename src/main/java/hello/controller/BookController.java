package hello.controller;

import hello.model.Book;
import hello.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/book/title/{title}")
    public List<Book> findByTitle(@PathVariable String title) {
        return bookRepository.findByTitle(title);
    }

    @GetMapping("/book/author/{author}")
    public List<Book> findByAuthor(@PathVariable String author) {
        return bookRepository.findByAuthor(author);
    }
}
