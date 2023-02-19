package com.bootcamp.spring.Controller;

import com.bootcamp.spring.DomainObject.Tenants;
import com.bootcamp.spring.Repository.TenantRepository;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TenantController {
    @Autowired
    TenantRepository tenantRepository;

    @GetMapping("/tenants")
    public ResponseEntity<String> getAllTenants() {
        try {
//            List<Tenants> tenants = new ArrayList<>();
//            tenantRepository.findAll().forEach(tenants::add);
            Iterable<Tenants> allTenants = tenantRepository.findAll();

            String jsonAllPeople = JSONArray.toJSONString((List<? extends Object>) allTenants);

            if (!allTenants.iterator().hasNext()) {
                return new ResponseEntity<>("{\"message\":\"There are no tenants entities in the database\"}",
                        HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(jsonAllPeople, HttpStatus.OK);
        }
        catch (HttpServerErrorException e) {
            return new ResponseEntity<>("{\"message\":\"There was an error parsing \"}",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tenants/{id}")
    public ResponseEntity<String> getTenantById(@PathVariable("id") long id) {

        Optional<Tenants> tenantData = tenantRepository.findById(id);

        if (tenantData.isPresent()) {
            return new ResponseEntity<>(tenantData.get().toString(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("{\"message\" : \"There was an error parsing your request to the server\"}",
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/tenant")
    public ResponseEntity<String> createTenant(@RequestBody Tenants incomingTenant) {

        try {
            Tenants tenant = tenantRepository.save(incomingTenant);


            return new ResponseEntity<>(tenant.toString(), HttpStatus.CREATED);

        }
        catch (HttpServerErrorException e) {
            return new ResponseEntity<>("{\"message\" : \"There was an error parsing your request to the server\"}",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tenant/{id}")
    public ResponseEntity<String> updateTenant(@PathVariable("id") long id, @RequestBody Tenants tenantUpdate) {
        Optional<Tenants> tenantData = tenantRepository.findById(id);

        if (tenantData.isPresent()) {

            Tenants tenant = tenantData.get();
//            tenant.setFirstname(tenantUpdate.getFirstName());
//            tenant.setLastname(tenantUpdate.getLastName());
//            tenant.setIDnumber(tenantUpdate.getIDnumber());

            return new ResponseEntity<>(tenantRepository.save(tenant).toString(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("{\"message\" : \"There was an error parsing your request to the server\"}",
                    HttpStatus.NOT_FOUND);
        }
    }


}
