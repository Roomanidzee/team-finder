package com.romanidze.teamfinder.teamservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.romanidze.teamfinder.teamservice.dto.CaptainDeclineDTO;
import com.romanidze.teamfinder.teamservice.services.interfaces.CaptainService;

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

    @PostMapping(value = "/decline_cap", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> declineCaptain(@RequestBody CaptainDeclineDTO captainDeclineDTO)
                                                                                      throws JsonProcessingException {

        this.captainService.declineCap(captainDeclineDTO);
        return ResponseEntity.ok("{\"message\": \"Заявка отправлена\"}");

    }

}
