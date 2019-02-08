package com.romanidze.teamfinder.messagingservice.services.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.romanidze.teamfinder.messagingservice.dto.TeamDTO;

/**
 * 05.02.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public interface CaptainService {

    void processCaptain(TeamDTO teamDTO) throws JsonProcessingException;

}
