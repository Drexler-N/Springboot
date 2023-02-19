package com.bootcamp.spring.Controller;


import com.bootcamp.spring.DomainObject.People;
import com.bootcamp.spring.DomainObject.Visitors;
import com.bootcamp.spring.Repository.PeopleRepository;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class PeopleController {

    @Autowired
    PeopleRepository peopleRepository;

    @GetMapping("/people")
    public ResponseEntity<String> getAllPeople() {
        try {
            List<People> people = new ArrayList<>();
            peopleRepository.findAll().forEach(people::add);
//            Iterable<People> allPeople = peopleRepository.findAll();
            String jsonAllPeople = JSONArray.toJSONString(people);


            if (people.isEmpty()) {
                return new ResponseEntity<>("{\"message\":\"There are no people entities in the database\"}",
                        HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(jsonAllPeople, HttpStatus.OK);
        }
        catch (HttpServerErrorException e) {
            return new ResponseEntity<>("{\"message\" : \"There was an error parsing your request to the server\"}",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<String> getPeopleById(@PathVariable("id") long id) {
        Optional<People> peopleData = peopleRepository.findById(id);
        String jsonPerson = JSONArray.toJSONString(Collections.singletonList(peopleData));

        return peopleData.map(people -> new ResponseEntity<>(jsonPerson, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>("{\"message\":\"The people entity with that id could not" +
                        "be found\"}", HttpStatus.NOT_FOUND));
    }

}
