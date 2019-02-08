package com.romanidze.teamfinder.teamservice.services.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.romanidze.teamfinder.teamservice.dto.CaptainDeclineDTO;
import com.romanidze.teamfinder.teamservice.dto.TeamDTO;

import java.time.LocalDateTime;

/**
 * 04.02.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public interface CaptainService {

    void prepareCaptain(TeamDTO teamDTO) throws JsonProcessingException;
    boolean checkCapByTime(LocalDateTime creationDate);
    void declineCap(CaptainDeclineDTO captainDeclineDTO) throws JsonProcessingException;

}
