package hello.repository;

import hello.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Transactional(readOnly=true)
    public List<Book> findByAuthor(String author) {
        return jdbcTemplate.query("select * from book where author_id in (select id from author where name=?)",  new Object[]{author},new BookRowMapper());
    }

    @Transactional(readOnly=true)
    public List<Book> findByTitle(String title) {
        String likeTitle='%'+title+'%';
        return jdbcTemplate.query("select * from book where title like ?",  new Object[]{likeTitle},new BookRowMapper());
    }
}

class BookRowMapper implements RowMapper<Book>{

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id=rs.getInt("id");
        String title=rs.getString("title");
        int authorId=rs.getInt("author_id");
        return new Book(id,title,authorId);
    }
}