package com.bootcamp.spring.Repository;

import com.bootcamp.spring.Model.FinedTenants;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "finedtenants", path = "finedtenants")
public interface FinedTenantsRepository  extends PagingAndSortingRepository<FinedTenants, Long>, CrudRepository<FinedTenants,Long>{

}

