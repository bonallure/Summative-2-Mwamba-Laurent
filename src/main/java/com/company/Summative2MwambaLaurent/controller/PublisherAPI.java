package com.company.Summative2MwambaLaurent.controller;

import com.company.Summative2MwambaLaurent.model.Publisher;
import com.company.Summative2MwambaLaurent.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bonallure on 11/14/21
 */
@RestController
public class PublisherAPI {

    private ServiceLayer serviceLayer;

    @Autowired
    public PublisherAPI(ServiceLayer serviceLayer){
        this.serviceLayer = serviceLayer;
    }

    // CRUD METHODS
    // create
    @RequestMapping(value = "/publisher", method = RequestMethod.POST)
    public Publisher addPublisher(@RequestBody Publisher publisher){

        return serviceLayer.addPublisher(publisher);
    }

    // read
    @RequestMapping(value = "/publishers/{id}", method = RequestMethod.GET)
    public Publisher findPublisher(@PathVariable int id){

        return serviceLayer.findPublisher(id);
    }

    // update
    @RequestMapping(value = "/publishers", method = RequestMethod.PUT)
    public void updatePublisher(@RequestBody Publisher publisher){

        serviceLayer.updatePublisher(publisher);
    }

    // delete
    @RequestMapping(value = "/publisher/{id}", method = RequestMethod.DELETE)
    public void deletePublisher(@PathVariable int id){

        serviceLayer.deletePublisher(id);
    }
}
