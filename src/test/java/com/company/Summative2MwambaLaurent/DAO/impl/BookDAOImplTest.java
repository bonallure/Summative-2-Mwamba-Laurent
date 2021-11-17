package com.company.Summative2MwambaLaurent.DAO.impl;

import com.company.Summative2MwambaLaurent.DAO.AuthorDAO;
import com.company.Summative2MwambaLaurent.DAO.BookDAO;
import com.company.Summative2MwambaLaurent.DAO.PublisherDAO;
import com.company.Summative2MwambaLaurent.model.Author;
import com.company.Summative2MwambaLaurent.model.Book;
import com.company.Summative2MwambaLaurent.model.Publisher;
import com.company.Summative2MwambaLaurent.service.ServiceLayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by bonallure on 11/16/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDAOImplTest {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private ServiceLayer serviceLayer;

    @Autowired
    private PublisherDAO publisherDAO;

    @Autowired
    private AuthorDAO authorDAO;

    @Before
    public void setUp() throws Exception {
        List<Book> bookList = bookDAO.readAll();

        for (Book book: bookList){
            bookDAO.delete(book.getId());
        }

        List<Publisher> publisherList = publisherDAO.readAll();

        for (Publisher publisher: publisherList) {
            publisherDAO.delete(publisher.getId());
        }

        List<Author> authorList = authorDAO.readAll();

        for (Author author: authorList) {
            authorDAO.delete(author.getId());
        }
    }

    @Test
    public void addGetUpdateDelete(){

        // prepare author
        Author author1 = new Author();
        author1.setFirstName("Tiffany");
        author1.setLastName("Angove");
        author1.setStreet("5433 Carriage Dr.");
        author1.setCity("Austin");
        author1.setState("TX");
        author1.setPostalCode("78704");
        author1.setPhone("817-309-8076");
        author1.setEmail("tiffany_angove@gmail.com");

        // prepare publisher
        Publisher publisher1 = new Publisher();
        publisher1.setName("Les Auberges Jaunes");
        publisher1.setCity("Austin");
        publisher1.setEmail("lesaubergesjaunes@gmail.com");
        publisher1.setPostalCode("78704");
        publisher1.setStreet("512 1st Street");
        publisher1.setState("TX");
        publisher1.setPhone("512-456-8309");

        // act
        publisher1 = publisherDAO.create(publisher1);
        author1 = authorDAO.create(author1);

        // prepare book
        Book book1 = new Book();
        book1.setIsbn("thisIsUsuallyA#");
        book1.setPublishDate(Date.valueOf("2021-11-30"));
        book1.setAuthorId(author1.getId());
        book1.setTitle("Los camarones verde");
        book1.setPublisherId(publisher1.getId());
        book1.setPrice(39.99);

        // act
        book1 = bookDAO.create(book1);
        Book book2 = bookDAO.read(book1.getId());

        // act: service layer method find books by author
        List<Book> author1Books = serviceLayer.findBooksByAuthor(author1.getId());

        // assert
        assertEquals(book2, book1);
        assertEquals(1, author1Books.size());
        assertTrue(author1Books.contains(book1));

        // arrange for book update
        book2.setPrice(18.99);

        // act
        bookDAO.update(book2);
        book2 = bookDAO.read(book1.getId());

        // assert
        assertNotEquals(book1, book2);

        // delete act
        bookDAO.delete(book1.getId());
        book1 = bookDAO.read(book1.getId());

        // assert
        assertNull(book1);
    }
}