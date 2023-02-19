package com.bootcamp.spring.Repository;


import com.bootcamp.spring.DomainObject.Types;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "types", path = "types")
public interface TypesRepository extends PagingAndSortingRepository<Types, Long>, CrudRepository<Types,Long> {
}
