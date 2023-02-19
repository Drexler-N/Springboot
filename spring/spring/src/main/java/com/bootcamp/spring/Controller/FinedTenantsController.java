package com.bootcamp.spring.Controller;

import com.bootcamp.spring.DomainObject.FinedTenants;
import com.bootcamp.spring.Repository.FinedTenantsRepository;
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
public class FinedTenantsController {

    @Autowired
    FinedTenantsRepository finedTenantsRepository;

    @GetMapping("/finedtenants")
    public ResponseEntity<String> getAllFinedTenants() {
        try {
            List<FinedTenants> finedTenants = new ArrayList<>();
//            Iterable<FinedTenants> allFinedTenants = finedTenantsRepository.findAll();

            finedTenantsRepository.findAll().forEach(finedTenants::add);
            String jsonFinedTenants = JSONArray.toJSONString(finedTenants);



            if (finedTenants.isEmpty()) {
                return new ResponseEntity<>("{\"message\" : \"There are no fined tenants in the database\"}",
                        HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(jsonFinedTenants, HttpStatus.OK);

        }
        catch (HttpServerErrorException e) {
            return new ResponseEntity<>("{\"message\" : \"There was an error parsing your request to the server\"}",
                    HttpStatus.INTERNAL_SERVER_ERROR );
        }

    }

    @GetMapping("/finedtenants/{id}")
    public ResponseEntity<String> getFinedTenantById(@PathVariable("id") long id) {
        Optional<FinedTenants> finedTenantData = finedTenantsRepository.findById(id);
        String jsonFinedTenant = JSONArray.toJSONString(Collections.singletonList(finedTenantData));

        if(jsonFinedTenant.isEmpty()){
            return new ResponseEntity<>("{\"message\" : \"There are no fined tenants in the database\"}",
                    HttpStatus.NO_CONTENT);
        }

        return finedTenantData.map(finedTenants -> new ResponseEntity<>(jsonFinedTenant, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>("{\"message\" : \"There are no fined tenants in the database with " +
                    "that id\"}", HttpStatus.NOT_FOUND));
    }



}
