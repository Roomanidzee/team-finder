package com.romanidze.teamfinder.teamservice.mappers;

import com.romanidze.teamfinder.teamservice.config.mapstruct.MapStructConfig;
import com.romanidze.teamfinder.teamservice.domain.Team;
import com.romanidze.teamfinder.teamservice.dto.TeamDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import org.springframework.stereotype.Component;

/**
 * 04.02.2019
 *
 * Интерфейс, необходимый для передачи данных из доменной сущности в DTO
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
@Mapper(config = MapStructConfig.class)
public interface TeamMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "team.captain.participantID", target = "captainID"),
            @Mapping(source = "team.captain.participantIdentifier", target = "captainIdentifier")
    })
    TeamDTO domainToDTO(final Team team);

}
