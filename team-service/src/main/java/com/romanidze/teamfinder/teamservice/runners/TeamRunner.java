package com.romanidze.teamfinder.teamservice.runners;

import com.romanidze.teamfinder.teamservice.domain.Person;
import com.romanidze.teamfinder.teamservice.domain.Team;
import com.romanidze.teamfinder.teamservice.repositories.PersonRepository;
import com.romanidze.teamfinder.teamservice.repositories.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 05.02.2019
 *
 * Инициализация данных для MongoDB
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
public class TeamRunner implements CommandLineRunner {

    private final PersonRepository personRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public TeamRunner(PersonRepository personRepository, TeamRepository teamRepository) {
        this.personRepository = personRepository;
        this.teamRepository = teamRepository;
    }

    /**
     *  Метод со вставкой данных в базу
     *  @param args - аргументы, которые могут появиться пр запуске приложения
     */
    @Override
    public void run(String... args) throws Exception {

        Person person1 = Person.builder()
                               .identifier("Иванов Иван")
                               .build();

        Person person2 = Person.builder()
                               .identifier("Смирнов Семён")
                               .build();

        Person person3 = Person.builder()
                               .identifier("Сидоров Пётр")
                               .build();

        this.personRepository.save(person1);
        this.personRepository.save(person2);
        this.personRepository.save(person3);

        List<Person> persons = Arrays.asList(person1, person2, person3);

        Team team = Team.builder()
                        .persons(persons)
                        .build();

        this.teamRepository.save(team);

    }
}
