package hello.repository;


import hello.model.Author;
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
public class AuthorRepositoryImpl implements AuthorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    @Transactional(readOnly=true)
    public List<Author> findByName(String name) {
        Object[] parameters=new Object[]{name};
        return jdbcTemplate.query("select * from author where name= ? ", parameters,new AuthorRowMapper());
    }

    @Override
    @Transactional
    public long createAuthor(String name){
        Map<String,Object> insertParameters = new HashMap<String, Object>();
        insertParameters.put("name", name);
        SimpleJdbcInsert insert=new SimpleJdbcInsert(jdbcTemplate).withTableName("author").usingGeneratedKeyColumns("id");;
        return insert.executeAndReturnKey(insertParameters).longValue();
    }
}

class AuthorRowMapper implements RowMapper<Author>{

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id=rs.getInt("id");
        String name=rs.getString("name");
        return new Author(id,name);
    }
}