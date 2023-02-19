package com.bootcamp.spring.Controller;


import com.bootcamp.spring.DomainObject.Tenants;
import com.bootcamp.spring.DomainObject.Visitors;
import com.bootcamp.spring.Repository.VisitorRepository;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.repository.query.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class VisitorController {

    @Autowired
    VisitorRepository visitorRepository;

    @GetMapping("/visitors")
    public ResponseEntity<String> getAllVisitors() {
        try {
            List<Visitors> visitors = new ArrayList<>();
            visitorRepository.findAll().forEach(visitors::add);
            String jsonVisitors = JSONArray.toJSONString(visitors);

//            Iterable<Visitors> visitors = visitorRepository.findAll();

            if (jsonVisitors.isEmpty()) {
                return new ResponseEntity<>("{\"message\" : \"There are no visitors in the database\"}",
                        HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(jsonVisitors, HttpStatus.OK);
        }
        catch (HttpServerErrorException e) {
            return new ResponseEntity<>("{\"message\" : \"There was an error parsing your request to the server\"}",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/visitors/{id}")
    public ResponseEntity<String> getVisitorById(@PathVariable("id") long id) {
        Optional<Visitors> visitorData = visitorRepository.findById(id);

        String jsonVisitor = JSONArray.toJSONString(Collections.singletonList(visitorData));

        return visitorData.map(visitors -> new ResponseEntity<>(jsonVisitor, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(
                    "{\"message\" : \"There is no visitor in the database with that id\"}",
                        HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/visitor")
    public ResponseEntity<String> createVisitor(@RequestBody Visitors visitor) {

        try {
            Visitors visitors = visitorRepository.save(visitor);


            return new ResponseEntity<>(visitor.toString(), HttpStatus.CREATED);

        }
        catch (HttpServerErrorException e) {
            return new ResponseEntity<>("{\"message\" : \"There was an error parsing your request to the server\"}",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
