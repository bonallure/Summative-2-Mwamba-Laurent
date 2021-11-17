package com.company.Summative2MwambaLaurent.controller;

import com.company.Summative2MwambaLaurent.model.Book;
import com.company.Summative2MwambaLaurent.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by bonallure on 11/14/21
 */
@RestController
public class BookAPI {

    private ServiceLayer serviceLayer;

    @Autowired
    public BookAPI(ServiceLayer serviceLayer){
        this.serviceLayer = serviceLayer;
    }

    // CRUD METHODS
    // create
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Book addBook(@RequestBody Book book){

        return serviceLayer.addBook(book);
    }

    // read
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Book findBook(@PathVariable int id){

        return serviceLayer.findBook(id);
    }

    // read by
    @RequestMapping(value = "/books/artist/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Book> findBookByAuthor(@PathVariable int authorId){

        return serviceLayer.findBooksByAuthor(authorId);
    }

    // update
    @RequestMapping(value = "/books", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateBook(@RequestBody Book book){

        serviceLayer.updateBook(book);
    }

    // delete
    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteBook(@PathVariable int id){

        serviceLayer.deleteBook(id);
    }
}
