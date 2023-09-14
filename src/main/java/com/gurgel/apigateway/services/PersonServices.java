package com.gurgel.apigateway.services;

import com.gurgel.apigateway.models.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id){
        logger.info("Finding one person");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Mateus");
        person.setLastName("Gurgel");
        person.setGender("Male");
        person.setAddress("Road braba do sul, 198");
        return person;
    }
}
