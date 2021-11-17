package com.company.Summative2MwambaLaurent.DAO;

import com.company.Summative2MwambaLaurent.model.Book;

import java.util.List;

/**
 * Created by bonallure on 11/13/21
 */
public interface BookDAO {

    Book create(Book book);

    Book read(int id);

    List<Book> readAll();

    void update(Book book);

    void delete(int id);
}
