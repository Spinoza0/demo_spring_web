package com.evolentacourses.demospringweb.data.repository;

import com.evolentacourses.demospringweb.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
