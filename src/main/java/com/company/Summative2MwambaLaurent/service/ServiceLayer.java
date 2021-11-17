package com.company.Summative2MwambaLaurent.service;

import com.company.Summative2MwambaLaurent.model.Author;
import com.company.Summative2MwambaLaurent.model.Book;
import com.company.Summative2MwambaLaurent.model.Publisher;

import java.util.List;

/**
 * Created by bonallure on 11/13/21
 */
public interface ServiceLayer {

    // book crud methods
    Book addBook(Book book);

    Book findBook(int id);

    List<Book> findBooksByAuthor(int authorId);

    List<Book> findAllBooks();

    void updateBook(Book book);

    void deleteBook(int id);

    // author crud methods
    Author addAuthor(Author author);

    Author findAuthor(int id);

    List<Author> findAllAuthors();

    void updateAuthor(Author author);

    void deleteAuthor(int id);

    // publisher crud methods
    Publisher addPublisher(Publisher publisher);

    Publisher findPublisher(int id);

    List<Publisher> findAllPublishers();

    void updatePublisher(Publisher publisher);

    void deletePublisher(int id);
}
