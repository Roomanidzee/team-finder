package com.romanidze.teamfinder.messagingservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.romanidze.teamfinder.messagingservice.dto.TeamDTO;
import com.romanidze.teamfinder.messagingservice.services.interfaces.CaptainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 05.02.2019
 *
 * Контроллер, который принимает заявки по капитанам
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@RestController
public class CaptainController {

    private final CaptainService captainService;

    @Autowired
    public CaptainController(CaptainService captainService) {
        this.captainService = captainService;
    }

    @PostMapping(value = "/accept_cap", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> acceptCaptain(@RequestBody TeamDTO teamDTO) throws JsonProcessingException {

        this.captainService.processCaptain(teamDTO);
        return ResponseEntity.ok("{\"message\": \"Заявка отправлена\"}");

    }

}
