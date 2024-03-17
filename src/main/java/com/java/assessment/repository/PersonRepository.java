package com.java.assessment.repository;

import com.java.assessment.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT s FROM Person s WHERE s.email = ?1")
    Optional<Person> findPersonByEmail(String email);

    @Query("SELECT s FROM Person s WHERE s.id = ?1")
    public Person retrievePersonById(Long id);
}
