package com.bootcamp.spring.Repository;


import com.bootcamp.spring.DomainObject.People;
import com.bootcamp.spring.DomainObject.Visitors;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PeopleRepository extends PagingAndSortingRepository<People, Long>, CrudRepository<People,Long> {

}
