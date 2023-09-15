package com.gurgel.apigateway.services;

import com.gurgel.apigateway.data.vo.v1.PersonVO;
import com.gurgel.apigateway.exceptions.ResourceNotFoundException;
import com.gurgel.apigateway.mapper.DozerMapper;
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

    public PersonVO findById(Long id){
        logger.info("Finding one person");

        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id"));

        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll(){
        logger.info("Getting all persons");

         return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO update(PersonVO person){
        logger.info("Updating one person");

        Person entity  = repository.findById(person.getId()).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id")
        );

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
    }

    public void delete(Long personId){
        logger.info("Deleting one person");

        Person entity  = repository.findById(personId).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id")
        );

        repository.delete(entity);

    }

    public PersonVO create(PersonVO person){
        logger.info("Creating one person");

        var entity = DozerMapper.parseObject(person, Person.class);
        var voEntity = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return voEntity;
    }
}
