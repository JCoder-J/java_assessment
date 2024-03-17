package com.java.assessment.controller;

import com.java.assessment.model.Person;
import com.java.assessment.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getPerson(){
        return personService.getPerson();
    }

    @PostMapping
    public void addPerson(@RequestBody Person person){
        personService.addPerson(person);
    }

    @PutMapping(path = "{personId}")
    public void updatePerson(@PathVariable("personId") Long id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email,
                              @RequestParam(required = false) String dob){
        personService.updatePerson(id, name, email, dob);
    }

    @DeleteMapping(path = "{personId}")
    public void deletePerson(@PathVariable("personId") Long id){
        personService.deletePerson(id);
    }
}
