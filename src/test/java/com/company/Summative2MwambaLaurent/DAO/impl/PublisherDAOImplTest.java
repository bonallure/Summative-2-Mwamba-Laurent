package com.company.Summative2MwambaLaurent.DAO.impl;

import com.company.Summative2MwambaLaurent.DAO.PublisherDAO;
import com.company.Summative2MwambaLaurent.model.Publisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bonallure on 11/14/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublisherDAOImplTest {

    @Autowired
    private PublisherDAO dao;

    // clearing the publisher table
    @org.junit.Before
    public void setUp() throws Exception {

        List<Publisher> publisherList = dao.readAll();

        for (Publisher publisher: publisherList) {
            dao.delete(publisher.getId());
        }
    }

    // add, get, update, and delete test
    @Test
    public void addGeUpdateDelete(){

        // prepare
        Publisher publisher1 = new Publisher();
        publisher1.setName("Les Auberges Jaunes");
        publisher1.setCity("Austin");
        publisher1.setEmail("lesaubergesjaunes@gmail.com");
        publisher1.setPostalCode("78704");
        publisher1.setStreet("512 1st Street");
        publisher1.setState("TX");
        publisher1.setPhone("512-456-8309");

        // create act
        publisher1 = dao.create(publisher1);
        Publisher publisher2 = dao.read(publisher1.getId());

        // assert
        assertEquals(publisher1, publisher2);

        // prepare
        publisher2.setPhone("512-876-3022");

        // update act
        dao.update(publisher2);
        publisher2 = dao.read(publisher2.getId());

        // assert
        assertNotEquals(publisher1, publisher2);

        // delete act
        dao.delete(publisher1.getId());
        publisher1 = dao.read(publisher1.getId());

        // assert
        assertNull(publisher1);

    }
}