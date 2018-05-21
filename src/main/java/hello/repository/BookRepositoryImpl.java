package hello.repository;

import hello.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    @Transactional(readOnly=true)
    public Book findById(long id) {
        Object[] parameters=new Object[]{id};
        return jdbcTemplate.queryForObject("select * from book where id=?", parameters,new BookRowMapper());
    }

    @Override
    @Transactional(readOnly=true)
    public List<Book> findByAuthor(String author) {
        Object[] parameters=new Object[]{author.toLowerCase()};
        return jdbcTemplate.query("select * from book where author_id in (select id from author where lower(name)=?)", parameters,new BookRowMapper());
    }

    @Override
    @Transactional(readOnly=true)
    public List<Book> findByTitle(String title) {
        Object[] parameters=new Object[]{'%'+title.toLowerCase()+'%'};
        return jdbcTemplate.query("select * from book where lower(title) like ?",  parameters,new BookRowMapper());
    }

    @Override
    @Transactional
    public long createBook(String title, long authorId){
        Map<String,Object> insertParameters = new HashMap<String, Object>();
        insertParameters.put("title", title);
        insertParameters.put("author_id", authorId);
        SimpleJdbcInsert insert=new SimpleJdbcInsert(jdbcTemplate).withTableName("book").usingGeneratedKeyColumns("id");;
        return insert.executeAndReturnKey(insertParameters).longValue();
    }
}

class BookRowMapper implements RowMapper<Book>{

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id=rs.getInt("id");
        String title=rs.getString("title");
        long authorId=rs.getInt("author_id");
        return new Book(id,title,authorId);
    }
}