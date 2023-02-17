package com.bootcamp.spring.Controller;

import com.bootcamp.spring.Model.FinedTenants;
import com.bootcamp.spring.Repository.FinedTenantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class FinedTenantsController {
    @Autowired
    FinedTenantsRepository finedTenantsRepository;

    @GetMapping("/finedtenants")
    public ResponseEntity<List<FinedTenants>> getAllFinedTenants() {
        try {
            List<FinedTenants> finedTenants = new ArrayList<>();
            finedTenantsRepository.findAll().forEach(finedTenants::add);

            if (finedTenants.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(finedTenants, HttpStatus.OK);

        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/finedtenants/{id}")
    public ResponseEntity<FinedTenants> getFinedTenantById(@PathVariable("id") long id) {
        Optional<FinedTenants> finedTenantData = finedTenantsRepository.findById(id);

        return finedTenantData.map(finedTenants -> new ResponseEntity<>(finedTenants, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



}
