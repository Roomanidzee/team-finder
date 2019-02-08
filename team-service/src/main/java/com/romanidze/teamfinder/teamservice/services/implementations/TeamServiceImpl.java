package com.romanidze.teamfinder.teamservice.services.implementations;

import com.romanidze.teamfinder.teamservice.domain.Team;
import com.romanidze.teamfinder.teamservice.dto.TeamDTO;
import com.romanidze.teamfinder.teamservice.mappers.TeamMapper;
import com.romanidze.teamfinder.teamservice.repositories.TeamRepository;
import com.romanidze.teamfinder.teamservice.services.interfaces.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 04.02.2019
 *
 * Сервис с бизнес - логикой по командам
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    /**
     *
     * Получение команды с её капитаном по идентификатору команды
     * @param teamID - идентификатор команды
     *
     */
    @Override
    public TeamDTO getTeamByID(String teamID) {

        Optional<Team> existedTeam = this.teamRepository.findById(teamID);

        if(!existedTeam.isPresent()){
            throw new NullPointerException("Нет такой команды!");
        }

        Team team = existedTeam.get();

        if(team.getCaptain() == null){
            throw new NullPointerException("У команды нет капитана!");
        }

        return this.teamMapper.domainToDTO(team);
    }
}
