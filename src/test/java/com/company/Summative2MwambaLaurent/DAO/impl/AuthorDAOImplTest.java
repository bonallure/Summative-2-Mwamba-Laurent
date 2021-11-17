package com.company.Summative2MwambaLaurent.DAO.impl;

import com.company.Summative2MwambaLaurent.DAO.AuthorDAO;
import com.company.Summative2MwambaLaurent.model.Author;
import com.company.Summative2MwambaLaurent.model.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bonallure on 11/16/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthorDAOImplTest {

    @Autowired
    private AuthorDAO dao;

    @Before
    public void setUp() throws Exception {

        List<Author> authorList = dao.readAll();

        for (Author author: authorList) {
            dao.delete(author.getId());
        }
    }

    // add, get, update, and delete test
    @Test
    public void addGeUpdateDelete(){

        // prepare
        Author author1 = new Author();
        author1.setFirstName("Tiffany");
        author1.setLastName("Angove");
        author1.setStreet("5433 Carriage Dr.");
        author1.setCity("Austin");
        author1.setState("TX");
        author1.setPostalCode("78704");
        author1.setPhone("817-309-8076");
        author1.setEmail("tiffany_angove@gmail.com");

        // create act
        author1 = dao.create(author1);
        Author author2 = dao.read(author1.getId());

        // assert
        assertEquals(author1, author2);

        // prepare
        author2.setPhone("512-876-3022");

        // update act
        dao.update(author2);
        author2 = dao.read(author2.getId());

        // assert
        assertNotEquals(author1, author2);

        // delete act
        dao.delete(author1.getId());
        author1 = dao.read(author1.getId());

        // assert
        assertNull(author1);
    }
}