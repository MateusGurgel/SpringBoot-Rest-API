package com.gurgel.apigateway.services;

import com.gurgel.apigateway.exceptions.ResourceNotFoundException;
import com.gurgel.apigateway.models.Person;
import com.gurgel.apigateway.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public Person findById(Long id){
        logger.info("Finding one person");

        //Person person = mockPerson(Integer.parseInt(id));Res
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id")) ;
    }

    public List<Person> findAll(){
        logger.info("Getting all persons");

         return repository.findAll();
    }

    public Person update(Person person){
        logger.info("Updating one person");

        Person entity  = repository.findById(person.getId()).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id")
        );

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete(Long personId){
        logger.info("Deleting one person");

        Person entity  = repository.findById(personId).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id")
        );

        repository.delete(entity);

    }

    public Person create(Person person){
        logger.info("Creating one person");
        return repository.save(person );
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Mateus " + i);
        person.setLastName("Gurgel");
        person.setGender("Male");
        person.setAddress("Road braba do sul, 198");
        return person;
    }
}
