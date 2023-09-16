package com.gurgel.apigateway.repositories.v1;

import com.gurgel.apigateway.models.v1.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}