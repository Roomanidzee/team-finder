package com.romanidze.teamfinder.messagingservice.services.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.romanidze.teamfinder.messagingservice.config.rabbitmq.RabbitMQProperties;
import com.romanidze.teamfinder.messagingservice.dto.TeamDTO;
import com.romanidze.teamfinder.messagingservice.services.interfaces.CaptainService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 05.02.2019
 *
 * Сервис с бизнес-логикой по капитанам
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Service
public class CaptainServiceImpl implements CaptainService {

    private final ObjectMapper objectMapper;
    private final RabbitMQProperties rabbitMQProperties;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public CaptainServiceImpl(ObjectMapper objectMapper, RabbitMQProperties rabbitMQProperties,
                              RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.rabbitMQProperties = rabbitMQProperties;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void processCaptain(TeamDTO teamDTO) throws JsonProcessingException {

        String jsonString = this.objectMapper.writeValueAsString(teamDTO);

        this.rabbitTemplate.convertAndSend(this.rabbitMQProperties.getExchange(),
                                           this.rabbitMQProperties.getCheckCapRequest().getBinding(), jsonString);
    }
}
