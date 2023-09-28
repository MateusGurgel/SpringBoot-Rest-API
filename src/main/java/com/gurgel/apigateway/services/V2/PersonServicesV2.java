package com.gurgel.apigateway.services.V2;

import com.gurgel.apigateway.data.vo.v2.PersonVOV2;
import com.gurgel.apigateway.exceptions.ResourceNotFoundException;
import com.gurgel.apigateway.mapper.DozerMapper;
import com.gurgel.apigateway.models.v2.PersonV2;
import com.gurgel.apigateway.repositories.v1.PersonRepository;
import com.gurgel.apigateway.repositories.v2.PersonRepositoryV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServicesV2 {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServicesV2.class.getName());

    @Autowired
    PersonRepositoryV2 repository;

    public PersonVOV2 findById(Long id){
        logger.info("Finding one person");

        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id"));

        return DozerMapper.parseObject(entity, PersonVOV2.class);
    }

    public List<PersonVOV2> findAll(){
        logger.info("Getting all persons");

         return DozerMapper.parseListObjects(repository.findAll(), PersonVOV2.class);
    }

    public PersonVOV2 update(PersonVOV2 person){
        logger.info("Updating one person");

        PersonV2 entity  = repository.findById(person.getId()).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id")
        );

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return DozerMapper.parseObject(repository.save(entity), PersonVOV2.class);
    }

    public void delete(Long personId){
        logger.info("Deleting one person");

        PersonV2 entity  = repository.findById(personId).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id")
        );

        repository.delete(entity);

    }

    public PersonVOV2 create(PersonVOV2 person){
        logger.info("Creating one person");

        var entity = DozerMapper.parseObject(person, PersonV2.class);
        var voEntity = DozerMapper.parseObject(repository.save(entity), PersonVOV2.class);
        return voEntity;
    }
}
