package com.gurgel.apigateway.controllers;

import com.gurgel.apigateway.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import com.gurgel.apigateway.services.PersonServices;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonServices service;
    //private PersonService = new PersonServices()

    @RequestMapping(value= "/{id}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable(value = "id") String id){

        return service.findById(id);
    }

}
