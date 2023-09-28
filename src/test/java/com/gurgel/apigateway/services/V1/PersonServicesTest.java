package com.gurgel.apigateway.services.V1;

import com.gurgel.apigateway.data.vo.v1.PersonVO;
import com.gurgel.apigateway.exceptions.RequiredObjectIsNullException;
import com.gurgel.apigateway.mocks.MockPerson;
import com.gurgel.apigateway.models.v1.Person;
import com.gurgel.apigateway.repositories.v1.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    private PersonServices service;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {

        Person entity = input.mockEntity(1);

        Person persisted = entity;
        persisted.setId(1);

        PersonVO vo = input.mockVO(1);

        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());

    }

    @Test
    void findById() {
        Person person = input.mockEntity(1);
        person.setId(1l);

        when(repository.findById(1l)).thenReturn(Optional.of(person));

        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("inks: [</person/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());

    }

    @Test
    void findAll() {
        List<Person> mockedList = input.mockEntityList();
        when(repository.findAll()).thenReturn(mockedList);

        var people = service.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        people.stream().forEach(person -> {

            assertTrue(person.toString().contains(
                    "inks: [</person/" + person.getKey() + ">;rel=\"self\"]"
            ));

            assertEquals("Addres Test" + person.getKey(), person.getAddress());
            assertEquals("First Name Test" + person.getKey(), person.getFirstName());
            assertEquals("Last Name Test" + person.getKey(), person.getLastName());
            assertEquals(((person.getKey() % 2)==0) ? "Male" : "Female", person.getGender());
        });
    }

    @Test
    void createPersonWithNullObject(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });

        String actualMessage = exception.getMessage();
        String expectedMessage = "It is not allowed to persist null objects";

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update() {
        Person entity = input.mockEntity(1);

        Person person = entity;
        person.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.findById(1l)).thenReturn(Optional.of(person));
        when(repository.save(entity)).thenReturn(person);

        var result = service.update(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("inks: [</person/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void delete() {
    }
}