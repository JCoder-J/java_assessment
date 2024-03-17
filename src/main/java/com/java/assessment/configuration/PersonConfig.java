package com.java.assessment.configuration;

import com.java.assessment.model.Person;
import com.java.assessment.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Configuration
@AllArgsConstructor
public class PersonConfig {

    @Bean
    CommandLineRunner commandLineRunner (PersonRepository repository){
        return args -> {
            List<Person> personList = new ArrayList<>();
            personList.add(new Person(
                    "First Name",
                    LocalDate.of(1999, Month.JANUARY, 1),
                    "first@name.com"
            ));

            personList.add(new Person(
                    "Second Name",
                    LocalDate.of(1999, Month.FEBRUARY, 2),
                    "second@name.com"
            ));

            repository.saveAll(personList);
        };
    }
}
