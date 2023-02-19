package com.bootcamp.spring.Controller;


import com.bootcamp.spring.DomainObject.Types;
import com.bootcamp.spring.DomainObject.Visitors;
import com.bootcamp.spring.Repository.TypesRepository;
import com.bootcamp.spring.Repository.VisitorRepository;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TypesController {

    @Autowired
    TypesRepository typesRepository;

    @GetMapping("/types")
    public ResponseEntity<String> getAllTypes() {
        try {

            List<Types> types = new ArrayList<>();
            typesRepository.findAll().forEach(types::add);
            String jsonTypes = JSONArray.toJSONString(types);


            if (types.isEmpty()) {
                    return new ResponseEntity<>("{\"message\":\"There are no types entities in the database\"}",
                            HttpStatus.NO_CONTENT);
                }
            return new ResponseEntity<>(jsonTypes, HttpStatus.OK);
        }
        catch (HttpServerErrorException e) {
            return new ResponseEntity<>("{\"message\" : \"There was an error parsing your request to the server\"}",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/typess/{id}")
    public ResponseEntity<String> getTypeById(@PathVariable("id") long id) {

        try {
            Optional<Types> visitorData = typesRepository.findById(id);
            String jsonTypeData = JSONArray.toJSONString(Collections.singletonList(visitorData));

            if(visitorData.isEmpty()){
                return new ResponseEntity<>("{\"message\":\"There is no type entities in the database with thar" +
                        "id\"}", HttpStatus.NO_CONTENT);
            }
            else{
                return new ResponseEntity<>(jsonTypeData, HttpStatus.OK);
            }

        }
        catch (HttpServerErrorException e){
            return new ResponseEntity<>("{\"message\":\"There are no types entities in the database\"}",
                    HttpStatus.NOT_FOUND);
        }



    }

}
