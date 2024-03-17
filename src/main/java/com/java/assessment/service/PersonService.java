package com.java.assessment.service;

import com.java.assessment.model.Person;

import java.util.List;

public interface PersonService {

    void addPerson(Person person);
    List<Person> getPerson();
    void updatePerson(Long id, String name, String email, String dob);
    void deletePerson(Long id);

}