package com.romanidze.teamfinder.teamservice.repositories;

import com.romanidze.teamfinder.teamservice.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 04.02.2019
 *
 * Интерфейс для получения данных по сущности Person
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public interface PersonRepository extends MongoRepository<Person, String> {
}
