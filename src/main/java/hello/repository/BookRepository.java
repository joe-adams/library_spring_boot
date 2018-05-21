package hello.repository;

import hello.model.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository {
    @Transactional(readOnly=true)
    Book findById(long id);

    @Transactional(readOnly=true)
    List<Book> findByAuthor(String author);

    @Transactional(readOnly=true)
    List<Book> findByTitle(String title);

    @Transactional
    long createBook(String title, long authorId);
}
