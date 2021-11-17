package com.company.Summative2MwambaLaurent.service;

import com.company.Summative2MwambaLaurent.DAO.AuthorDAO;
import com.company.Summative2MwambaLaurent.DAO.BookDAO;
import com.company.Summative2MwambaLaurent.DAO.PublisherDAO;
import com.company.Summative2MwambaLaurent.model.Author;
import com.company.Summative2MwambaLaurent.model.Book;
import com.company.Summative2MwambaLaurent.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bonallure on 11/13/21
 */
@Service
public class ServiceLayerImpl implements ServiceLayer {

    private BookDAO bookDAO;
    private AuthorDAO authorDAO;
    private PublisherDAO publisherDAO;

    @Autowired
    public ServiceLayerImpl(BookDAO bookDAO, AuthorDAO authorDAO, PublisherDAO publisherDAO){
        this.authorDAO = authorDAO;
        this.bookDAO = bookDAO;
        this.publisherDAO = publisherDAO;
    }

    @Override
    public Book addBook(Book book) {
        return bookDAO.create(book);
    }

    @Override
    public Book findBook(int id) {
        return bookDAO.read(id);
    }

    @Override
    public List<Book> findBooksByAuthor(int authorId) {
        List<Book> allBooks = bookDAO.readAll();
        List<Book> booksByAuthor = new ArrayList<>();
        for(Book book: allBooks){
            if (book.getAuthorId() == authorId){
                booksByAuthor.add(book);
            }
        }
        return booksByAuthor;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookDAO.readAll();
    }

    @Override
    public void updateBook(Book book) {
        bookDAO.update(book);
    }

    @Override
    public void deleteBook(int id) {
        bookDAO.delete(id);
    }

    @Override
    public Author addAuthor(Author author) {
        return authorDAO.create(author);
    }

    @Override
    public Author findAuthor(int id) {
        return authorDAO.read(id);
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorDAO.readAll();
    }

    @Override
    public void updateAuthor(Author author) {
        authorDAO.update(author);
    }

    @Override
    public void deleteAuthor(int id) {
        authorDAO.delete(id);
    }

    @Override
    public Publisher addPublisher(Publisher publisher) {
        return publisherDAO.create(publisher);
    }

    @Override
    public Publisher findPublisher(int id) {
        return publisherDAO.read(id);
    }

    @Override
    public List<Publisher> findAllPublishers() {
        return publisherDAO.readAll();
    }

    @Override
    public void updatePublisher(Publisher publisher) {
        publisherDAO.update(publisher);
    }

    @Override
    public void deletePublisher(int id) {
        publisherDAO.delete(id);
    }
}
