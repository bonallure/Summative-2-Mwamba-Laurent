package com.company.Summative2MwambaLaurent.DAO.impl;

import com.company.Summative2MwambaLaurent.DAO.AuthorDAO;
import com.company.Summative2MwambaLaurent.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by bonallure on 11/13/21
 */
@Repository
public class AuthorDAOImpl implements AuthorDAO {

    // Prepare statements
    private static final String CREATE_AUTHOR =
            "INSERT INTO author (first_name, last_name, street, city, state, postal_code, phone, email)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String READ_AUTHOR =
            "SELECT * FROM author WHERE author_id = ?";

    public static final String READ_ALL_AUTHORS =
            "SELECT * FROM author";

    private static final String UPDATE_AUTHOR =
            "UPDATE author SET first_name = ?, last_name = ?, street = ?, city = ?," +
                    " state = ?, postal_code = ?, phone = ?, email = ? WHERE author_id = ?";

    private static final String DELETE_AUTHOR =
            "delete from author where author_id = ?";

    // jdbctemplate
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorDAOImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author create(Author author) {

        jdbcTemplate.update(CREATE_AUTHOR,
                author.getFirstName(),
                author.getLastName(),
                author.getStreet(),
                author.getCity(),
                author.getState(),
                author.getPostalCode(),
                author.getPhone(),
                author.getEmail());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        author.setId(id);

        return author;
    }

    @Override
    public Author read(int id) {
        try {
            return jdbcTemplate.queryForObject(READ_AUTHOR, this::mapToRowAuthor, id);
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public List<Author> readAll() {
        return jdbcTemplate.query(READ_ALL_AUTHORS, this::mapToRowAuthor);
    }

    @Override
    public void update(Author author) {
        jdbcTemplate.update(UPDATE_AUTHOR,
                author.getFirstName(),
                author.getLastName(),
                author.getStreet(),
                author.getCity(),
                author.getState(),
                author.getPostalCode(),
                author.getPhone(),
                author.getEmail(),
                author.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_AUTHOR, id);
    }

    // mapToRowAuthor
    private Author mapToRowAuthor(ResultSet rs, int rowNum) throws SQLException {

        Author author = new Author();
        author.setId(rs.getInt("author_id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));
        author.setStreet(rs.getString("street"));
        author.setCity(rs.getString("city"));
        author.setState(rs.getString("state"));
        author.setPostalCode(rs.getString("postal_code"));
        author.setPhone(rs.getString("phone"));
        author.setEmail(rs.getString("email"));

        return author;
    }

}
