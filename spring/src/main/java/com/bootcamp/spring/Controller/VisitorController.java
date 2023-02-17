package com.bootcamp.spring.Controller;


import com.bootcamp.spring.Model.Visitors;
import com.bootcamp.spring.Repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class VisitorController {

    @Autowired
    VisitorRepository visitorRepository;

    @GetMapping("/visitors")
    public ResponseEntity<List<Visitors>> getAllVisitors() {
        try {
            List<Visitors> visitors = new ArrayList<>();
            visitorRepository.findAll().forEach(visitors::add);

            if (visitors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(visitors, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/visitors/{id}")
    public ResponseEntity<Visitors> getVisitorById(@PathVariable("id") long id) {
        Optional<Visitors> visitorData = visitorRepository.findById(id);

        return visitorData.map(visitors -> new ResponseEntity<>(visitors, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
