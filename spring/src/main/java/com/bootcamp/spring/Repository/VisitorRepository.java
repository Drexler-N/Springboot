package com.bootcamp.spring.Repository;

import com.bootcamp.spring.Model.Visitors;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "visitor", path = "visitors")
public interface VisitorRepository extends PagingAndSortingRepository<Visitors, Long>, CrudRepository<Visitors,Long> {

//    List<Visitors> findByTitleContaining(String title);
    @Procedure
    void uspEnteringVisitor(String firstname, String lastname, String idnumber, int tenandid);
}
