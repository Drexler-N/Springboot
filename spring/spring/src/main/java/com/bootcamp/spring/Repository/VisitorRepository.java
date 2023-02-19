package com.bootcamp.spring.Repository;

import com.bootcamp.spring.DomainObject.Visitors;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "visitors", path = "visitors")
public interface VisitorRepository extends PagingAndSortingRepository<Visitors, Long>, CrudRepository<Visitors,Long> {

//    @Procedure
//    void uspEnteringVisitor(String firstname, String lastname, String idnumber, int tenandid);
}
