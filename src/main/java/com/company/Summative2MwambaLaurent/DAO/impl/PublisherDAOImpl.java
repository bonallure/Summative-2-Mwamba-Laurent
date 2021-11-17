package com.company.Summative2MwambaLaurent.DAO.impl;

import com.company.Summative2MwambaLaurent.DAO.PublisherDAO;
import com.company.Summative2MwambaLaurent.model.Publisher;
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
public class PublisherDAOImpl implements PublisherDAO {

    // Prepare statements
    private static final String CREATE_PUBLISHER=
            "INSERT INTO publisher (name, street, city, state, postal_code, phone, email)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String READ_PUBLISHER =
            "SELECT * FROM publisher WHERE publisher_id = ?";

    public static final String READ_ALL_PUBLISHERS =
            "SELECT * FROM publisher";

    private static final String UPDATE_PUBLISHER =
            "UPDATE publisher SET name = ?, street = ?, city = ?," +
                    " state = ?, postal_code = ?, phone = ?, email = ? WHERE publisher_id = ?";

    private static final String DELETE_PUBLISHER =
            "delete from publisher where publisher_id = ?";

    // jdbctemplate
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PublisherDAOImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Publisher create(Publisher publisher) {
        jdbcTemplate.update(CREATE_PUBLISHER,
                publisher.getName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getPostalCode(),
                publisher.getPhone(),
                publisher.getEmail());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        publisher.setId(id);

        return publisher;
    }

    @Override
    public Publisher read(int id) {
        try{
            return jdbcTemplate.queryForObject(READ_PUBLISHER, this::mapToRowPublisher, id);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Publisher> readAll() {

         return jdbcTemplate.query(READ_ALL_PUBLISHERS, this::mapToRowPublisher);
    }

    @Override
    public void update(Publisher publisher) {

        jdbcTemplate.update(UPDATE_PUBLISHER,
                publisher.getName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getPostalCode(),
                publisher.getPhone(),
                publisher.getEmail(),
                publisher.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_PUBLISHER, id);
    }

    // mapToRowPublisher
    private Publisher mapToRowPublisher(ResultSet rs, int rowNum) throws SQLException {

        Publisher publisher = new Publisher();
        publisher.setId(rs.getInt("publisher_id"));
        publisher.setName(rs.getString("name"));
        publisher.setStreet(rs.getString("street"));
        publisher.setCity(rs.getString("city"));
        publisher.setState(rs.getString("state"));
        publisher.setPostalCode(rs.getString("postal_code"));
        publisher.setPhone(rs.getString("phone"));
        publisher.setEmail(rs.getString("email"));

        return publisher;
    }
}
