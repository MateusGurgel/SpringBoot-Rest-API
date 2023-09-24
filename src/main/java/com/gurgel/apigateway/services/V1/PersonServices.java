package com.gurgel.apigateway.services.V1;

import com.gurgel.apigateway.controllers.V1.PersonController;
import com.gurgel.apigateway.data.vo.v1.PersonVO;
import com.gurgel.apigateway.exceptions.ResourceNotFoundException;
import com.gurgel.apigateway.mapper.DozerMapper;
import com.gurgel.apigateway.models.v1.Person;
import com.gurgel.apigateway.repositories.v1.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



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

        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return vo;
    }

    public List<PersonVO> findAll(){
         logger.info("Getting all persons");

         var people = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
         people.stream().forEach(vo -> vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel()));

         return people;
    }

    public PersonVO update(PersonVO person){
        logger.info("Updating one person");

        Person entity  = repository.findById(person.getKey()).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id")
        );

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

        return vo;
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

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }
}
