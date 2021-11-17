package com.company.Summative2MwambaLaurent.DAO;

import com.company.Summative2MwambaLaurent.model.Author;

import java.util.List;

/**
 * Created by bonallure on 11/13/21
 */
public interface AuthorDAO {

    Author create(Author author);

    Author read(int id);

    List<Author> readAll();

    void update(Author author);

    void delete(int id);
}
