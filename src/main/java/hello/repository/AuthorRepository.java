package hello.repository;

import hello.model.Author;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AuthorRepository {
    @Transactional(readOnly=true)
    List<Author> findByName(String name);

    @Transactional
    long createAuthor(String name);
}
