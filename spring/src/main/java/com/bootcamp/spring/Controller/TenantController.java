package com.bootcamp.spring.Controller;

import com.bootcamp.spring.Model.Tenants;
import com.bootcamp.spring.Repository.TenantRepository;
import net.minidev.json.JSONObject;
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
public class TenantController {
    @Autowired
    TenantRepository tenantRepository;

    @GetMapping("/tenants")
    public ResponseEntity<List<Tenants>> getAllTenants() {
        try {
            List<Tenants> tenants = new ArrayList<>();
            tenantRepository.findAll().forEach(tenants::add);

            if (tenants.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tenants, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tenants/{id}")
    public ResponseEntity<Tenants> getTenantById(@PathVariable("id") long id) {

        Optional<Tenants> tenantData = tenantRepository.findById(id);

        if (tenantData.isPresent()) {
            return new ResponseEntity<>(tenantData.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/tenant")
    public ResponseEntity<Tenants> createTenant(@RequestBody JSONObject object) {

        Tenants tenants = new Tenants(object.getAsString("firstname"),
                object.getAsString("lastname"),
                object.getAsString("idnumber"),
                object.getAsString("roomnumber"));
        try {
            Tenants tenant = tenantRepository.save(tenants);

            return new ResponseEntity<>(tenant, HttpStatus.CREATED);

        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tenant/{id}")
    public ResponseEntity<Tenants> updateTutorial(@PathVariable("id") long id, @RequestBody Tenants tenantUpdate) {
        Optional<Tenants> tenantData = tenantRepository.findById(id);

        if (tenantData.isPresent()) {

            Tenants tenant = tenantData.get();
            tenant.setFirstname(tenantUpdate.getFirstName());
            tenant.setLastname(tenantUpdate.getLastName());
            tenant.setIDnumber(tenantUpdate.getIDnumber());

            return new ResponseEntity<>(tenantRepository.save(tenant), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
