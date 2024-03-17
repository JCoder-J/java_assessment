package com.java.assessment.service;

import com.java.assessment.model.Person;
import com.java.assessment.repository.PersonRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@NoArgsConstructor
public class PersonServiceImpl implements PersonService{

    private PersonRepository personRepository;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void addPerson(Person person) {
        Optional<Person> personOptional = personRepository.findPersonByEmail(person.getEmail());
        if (personOptional.isPresent()){
            throw new IllegalStateException("Email address is taken");
        }
        personRepository.save(person);
    }

    @Override
    public List<Person> getPerson() {
        return personRepository.findAll();
    }

    @Transactional
    @Override
    public void updatePerson(Long id, String name, String email, String dob) {
        if(!personRepository.findById(id).isPresent()){
            throw new IllegalStateException("Person ID " + id + " does not exist.");
        }
        Person person = personRepository.findById(id)
                .orElseThrow(() ->new IllegalStateException("Person ID " + id + " does not exist."));
        if (isNotNullOrEmpty(name)){
            person.setName(name);
        }
        if (isNotNullOrEmpty(email)) {
            person.setEmail(email);
        }
        if (isNotNullOrEmpty(dob)) {
            person.setDob(LocalDate.parse(dob));
        }

        personRepository.save(person);
    }

    @Override
    public void deletePerson(Long id) {
        if (!personRepository.existsById(id)){
            throw new IllegalStateException("Person ID " + id + " does not exist.");
        }
        personRepository.delete(personRepository.retrievePersonById(id));
    }

    private Boolean isNotNullOrEmpty(String checkString){
        return checkString == null || !checkString.isEmpty();
    }
}
