package com.company.Summative2MwambaLaurent.DAO;

import com.company.Summative2MwambaLaurent.model.Publisher;

import java.util.List;

/**
 * Created by bonallure on 11/13/21
 */
public interface PublisherDAO {

    Publisher create(Publisher publisher);

    Publisher read(int id);

    List<Publisher> readAll();

    void update(Publisher publisher);

    void delete(int id);
}
