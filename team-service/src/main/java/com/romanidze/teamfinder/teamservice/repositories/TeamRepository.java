package com.romanidze.teamfinder.teamservice.repositories;

import com.romanidze.teamfinder.teamservice.domain.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 04.02.2019
 *
 * Интерфейс для получения данных по сущности Team
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public interface TeamRepository extends MongoRepository<Team, String> {
}
