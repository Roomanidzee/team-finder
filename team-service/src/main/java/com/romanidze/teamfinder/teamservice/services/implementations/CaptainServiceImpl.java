package com.romanidze.teamfinder.teamservice.services.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.romanidze.teamfinder.teamservice.config.rabbitmq.RabbitMQProperties;
import com.romanidze.teamfinder.teamservice.domain.Captain;
import com.romanidze.teamfinder.teamservice.domain.Team;
import com.romanidze.teamfinder.teamservice.dto.CaptainDeclineDTO;
import com.romanidze.teamfinder.teamservice.dto.StatusDTO;
import com.romanidze.teamfinder.teamservice.dto.TeamDTO;
import com.romanidze.teamfinder.teamservice.repositories.CaptainRepository;
import com.romanidze.teamfinder.teamservice.repositories.TeamRepository;
import com.romanidze.teamfinder.teamservice.services.interfaces.CaptainService;
import com.romanidze.teamfinder.teamservice.services.interfaces.TeamService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 04.02.2019
 *
 * Сервис с бизнес-логикой по капитанам
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Service
public class CaptainServiceImpl implements CaptainService {

    private final CaptainRepository captainRepository;
    private final TeamRepository teamRepository;

    private final TeamService teamService;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final RabbitMQProperties rabbitMQProperties;

    @Autowired
    public CaptainServiceImpl(CaptainRepository captainRepository, TeamRepository teamRepository,
                              TeamService teamService, RabbitTemplate rabbitTemplate,
                              ObjectMapper objectMapper, RabbitMQProperties rabbitMQProperties) {
        this.captainRepository = captainRepository;
        this.teamRepository = teamRepository;
        this.teamService = teamService;
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
        this.rabbitMQProperties = rabbitMQProperties;
    }

    /**
     *
     * Проверка существующего капитана у команды
     * @param teamDTO - DTO - модель для обработки заявки
     *
     */
    @Override
    public void prepareCaptain(TeamDTO teamDTO) throws JsonProcessingException {

        TeamDTO existedTeam = this.teamService.getTeamByID(teamDTO.getId());

        if(existedTeam.getCaptainID() != null){

            StatusDTO statusDTO = StatusDTO.builder()
                                           .status(Boolean.FALSE)
                                           .build();

            String jsonString = this.objectMapper.writeValueAsString(statusDTO);
            this.rabbitTemplate.convertAndSend(this.rabbitMQProperties.getExchange(),
                                               this.rabbitMQProperties.getAcceptCap().getBinding(), jsonString);

        }

        Team team = this.teamRepository.findById(teamDTO.getId()).get();

        Captain captain = Captain.builder()
                                 .team(team)
                                 .participantID(teamDTO.getCaptainID())
                                 .participantIdentifier(teamDTO.getCaptainIdentifier())
                                 .build();

        this.captainRepository.save(captain);

        StatusDTO statusDTO = StatusDTO.builder()
                                       .status(Boolean.TRUE)
                                       .build();

        String jsonString = this.objectMapper.writeValueAsString(statusDTO);
        this.rabbitTemplate.convertAndSend(this.rabbitMQProperties.getExchange(),
                                           this.rabbitMQProperties.getAcceptCap().getBinding(), jsonString);

    }

    /**
     *
     * Проверка на тот случай, если две заявки будут отправлены одновременно
     * @param creationDate - дата создания сообщения с заявкой
     *
     */
    @Override
    public boolean checkCapByTime(LocalDateTime creationDate) {

        Captain existedCaptain = this.captainRepository.findByCreationDate(creationDate);

        return existedCaptain == null;

    }

    /**
     *
     * Метод, обрабатывающий ситуацию при отказе от капитанства
     * @param captainDeclineDTO - DTO - модель для обработки отказа
     *
     */
    @Override
    public void declineCap(CaptainDeclineDTO captainDeclineDTO) throws JsonProcessingException {

        Optional<Captain> existedCaptain = this.captainRepository.findById(captainDeclineDTO.getCaptainID());

        if(!existedCaptain.isPresent()){
            throw new NullPointerException("Нет такого капитана!");
        }

        Captain captain = existedCaptain.get();
        TeamDTO teamDTO = this.teamService.getTeamByID(captainDeclineDTO.getTeamID());

        if(!teamDTO.getCaptainID().equals(captain.getId())){
            throw new IllegalArgumentException("Несовпадение идентификаторов капитанов");
        }

        captain.setTeam(null);
        this.captainRepository.save(captain);

        StatusDTO statusDTO = StatusDTO.builder()
                                       .status(Boolean.FALSE)
                                       .build();

        String jsonString = this.objectMapper.writeValueAsString(statusDTO);

        this.rabbitTemplate.convertAndSend(this.rabbitMQProperties.getExchange(),
                                           this.rabbitMQProperties.getDeclineCap().getBinding(), jsonString);

    }
}
