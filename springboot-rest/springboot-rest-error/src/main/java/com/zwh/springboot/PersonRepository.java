package com.zwh.springboot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;


@RepositoryRestResource(path = "people")
public interface PersonRepository extends JpaRepository<Person,Long> {

    Collection<Person> finByEmail(@Param("email") String email);
}
