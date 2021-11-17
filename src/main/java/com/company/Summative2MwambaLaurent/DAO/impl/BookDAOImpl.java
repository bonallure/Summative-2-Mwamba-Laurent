package com.company.Summative2MwambaLaurent.DAO.impl;

import com.company.Summative2MwambaLaurent.DAO.BookDAO;
import com.company.Summative2MwambaLaurent.model.Book;
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
public class BookDAOImpl implements BookDAO {

    // Prepare statements
    private static final String CREATE_BOOK =
            "INSERT INTO book (isbn, publish_date, author_id, title, publisher_id, price) VALUES (?, ?, ?, ?, ?, ?)";

    public static final String READ_BOOK =
            "SELECT * FROM book WHERE book_id = ?";

    public static final String READ_ALL_BOOKS =
            "SELECT * FROM book";

    private static final String UPDATE_BOOK =
            "UPDATE book SET isbn = ?, publish_date = ?, author_id = ?, title = ?," +
                    " publisher_id = ?, price = ? WHERE book_id = ?";

    private static final String DELETE_BOOK =
            "delete from book where book_id = ?";

    // jdbctemplate
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAOImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    // CRUD methods
    @Override
    public Book create(Book book) {

        jdbcTemplate.update(CREATE_BOOK,
                book.getIsbn(),
                book.getPublishDate(),
                book.getAuthorId(),
                book.getTitle(),
                book.getPublisherId(),
                book.getPrice());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        book.setId(id);

        return book;
    }

    @Override
    public Book read(int id) {
        try {
            return jdbcTemplate.queryForObject(READ_BOOK, this::mapToRowBook, id);
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public List<Book> readAll() {

        return jdbcTemplate.query(READ_ALL_BOOKS, this::mapToRowBook);
    }

    @Override
    public void update(Book book) {
        jdbcTemplate.update(UPDATE_BOOK,
                book.getIsbn(),
                book.getPublishDate(),
                book.getAuthorId(),
                book.getTitle(),
                book.getPublisherId(),
                book.getPrice(),
                book.getId());
    }

    @Override
    public void delete(int id) {

        jdbcTemplate.update(DELETE_BOOK, id);
    }

    // mapToRowBook
    private Book mapToRowBook(ResultSet rs, int rowNum) throws SQLException {

        Book book = new Book();
        book.setId(rs.getInt("book_id"));
        book.setIsbn(rs.getString("isbn"));
        book.setPublishDate(rs.getDate("publish_date"));
        book.setAuthorId(rs.getInt("author_id"));
        book.setTitle(rs.getString("title"));
        book.setPublisherId(rs.getInt("publisher_id"));
        book.setPrice(rs.getDouble("price"));

        return book;
    }
}
