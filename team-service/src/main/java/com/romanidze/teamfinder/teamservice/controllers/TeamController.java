package com.romanidze.teamfinder.teamservice.controllers;

import com.romanidze.teamfinder.teamservice.dto.TeamDTO;
import com.romanidze.teamfinder.teamservice.services.interfaces.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 04.02.2019
 *
 * Контроллер, который возвращает информацию о команде
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@RestController
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService){
        this.teamService = teamService;
    }

    @GetMapping("/{teamID}")
    public ResponseEntity<TeamDTO> getTeamInformation(@PathVariable("teamID") String teamID){
        return ResponseEntity.ok(this.teamService.getTeamByID(teamID));
    }

}
