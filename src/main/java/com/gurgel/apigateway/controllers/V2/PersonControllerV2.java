package com.gurgel.apigateway.controllers.V2;

import com.gurgel.apigateway.data.vo.v2.PersonVOV2;
import com.gurgel.apigateway.services.V2.PersonServicesV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person/V2")
public class PersonControllerV2 {
    @Autowired
    private PersonServicesV2 service;
    //private PersonService = new PersonServices()

    @GetMapping(value= "/{id}",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVOV2 findById(@PathVariable(value = "id") Long id){

        return service.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonVOV2> findAll(){
        return service.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVOV2 create(@RequestBody PersonVOV2 person) {
        return service.create(person);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVOV2 update(@RequestBody PersonVOV2 person) {
        return service.update(person);
    }

    @DeleteMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
