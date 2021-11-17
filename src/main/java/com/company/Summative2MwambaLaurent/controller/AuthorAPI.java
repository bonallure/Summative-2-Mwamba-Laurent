package com.company.Summative2MwambaLaurent.controller;

import com.company.Summative2MwambaLaurent.model.Author;
import com.company.Summative2MwambaLaurent.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bonallure on 11/14/21
 */
@RestController
public class AuthorAPI {

    private ServiceLayer serviceLayer;

    @Autowired
    public AuthorAPI(ServiceLayer serviceLayer){
        this.serviceLayer = serviceLayer;
    }

    // CRUD METHODS
    // create
    @RequestMapping(value = "/author", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Author addAuthor(@RequestBody Author author){
        // return author;
        return serviceLayer.addAuthor(author);
    }

    // read
    @RequestMapping(value = "/authors/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Author findAuthor(@PathVariable int id){

        return serviceLayer.findAuthor(id);
    }

    // update
    @RequestMapping(value = "/authors", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateAuthor(@RequestBody Author author){

        serviceLayer.updateAuthor(author);
    }

    // delete
    @RequestMapping(value = "/author/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteAuthor(@PathVariable int id){

        serviceLayer.deleteAuthor(id);
    }
}
