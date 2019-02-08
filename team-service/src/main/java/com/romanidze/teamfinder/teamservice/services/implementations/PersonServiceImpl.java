package com.romanidze.teamfinder.teamservice.services.implementations;

import com.romanidze.teamfinder.teamservice.domain.Person;
import com.romanidze.teamfinder.teamservice.repositories.PersonRepository;
import com.romanidze.teamfinder.teamservice.services.interfaces.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 04.02.2019
 *
 * Сервис с бизнес - логикой по людям
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     *
     * Получение человека по его идентификатору
     * @param personID - идентификатор человека
     *
     */
    @Override
    public Person getPersonByID(String personID) {

        Optional<Person> existedPerson = this.personRepository.findById(personID);

        if(!existedPerson.isPresent()){
            throw new NullPointerException("Нет такого человека в БД!");
        }

        return existedPerson.get();
    }
}
