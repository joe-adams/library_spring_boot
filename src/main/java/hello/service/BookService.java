package hello.service;

import hello.model.Author;
import hello.model.Book;

import java.util.List;

public interface BookService {
    List<Book> findByAuthor(String author);

    List<Book> findByTitle(String title);

    Book createBook(String title, String author);

}
