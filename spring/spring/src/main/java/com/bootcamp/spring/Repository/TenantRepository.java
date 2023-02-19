package com.bootcamp.spring.Repository;

import com.bootcamp.spring.DomainObject.Tenants;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "tenant", path = "tenants")
public interface TenantRepository extends PagingAndSortingRepository<Tenants, Long>, CrudRepository<Tenants,Long> {

//    List<Tenants> findByFirstName(String firstname);



}
