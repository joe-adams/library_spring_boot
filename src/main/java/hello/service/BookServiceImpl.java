package hello.service;

import hello.model.Author;
import hello.model.Book;
import hello.repository.AuthorRepository;
import hello.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public BookServiceImpl() {
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public Book createBook(String title, String author){
        long authorId=getAuthorId(author);
        long id=bookRepository.createBook(title,authorId);
        return bookRepository.findById(id);
    }

    private long getAuthorId(String name){
        List<Author> authors=authorRepository.findByName(name);
        if(authors.isEmpty()){
            return authorRepository.createAuthor(name);
        } else{
            return authors.get(0).getId();
        }
    }

}
