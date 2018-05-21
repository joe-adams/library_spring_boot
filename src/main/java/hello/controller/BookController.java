package hello.controller;

import hello.model.Book;
import hello.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books/title/{title}")
    public List<Book> findByTitle(@PathVariable String title) {
        return bookService.findByTitle(title);
    }

    @GetMapping("/books/author/{author}")
    public List<Book> findByAuthor(@PathVariable String author) {
        return bookService.findByAuthor(author);
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Map<String, Object> json){
        return bookService.createBook((String)json.get("title"),(String)json.get("author"));
    }
}
