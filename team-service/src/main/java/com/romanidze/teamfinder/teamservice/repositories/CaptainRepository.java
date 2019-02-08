package com.romanidze.teamfinder.teamservice.repositories;

import com.romanidze.teamfinder.teamservice.domain.Captain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

/**
 * 04.02.2019
 *
 * Интерфейс для получения данных по сущности Captain
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public interface CaptainRepository extends MongoRepository<Captain, String> {

    Captain findByCreationDate(LocalDateTime creationDate);

}
