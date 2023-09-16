package com.gurgel.apigateway.repositories.v2;

import com.gurgel.apigateway.models.v1.Person;
import com.gurgel.apigateway.models.v2.PersonV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepositoryV2 extends JpaRepository<PersonV2, Long> {

}