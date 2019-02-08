package com.romanidze.teamfinder.teamservice.receivers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.romanidze.teamfinder.teamservice.config.rabbitmq.RabbitMQProperties;
import com.romanidze.teamfinder.teamservice.dto.StatusDTO;
import com.romanidze.teamfinder.teamservice.dto.TeamDTO;
import com.romanidze.teamfinder.teamservice.services.interfaces.CaptainService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 05.02.2019
 *
 * Консьюмер RabbitMQ, который обрабатывает поступающие заявки на капитанство
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
public class TeamCapReceiver {

    private static final Logger logger = LogManager.getLogger(TeamCapReceiver.class);

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQProperties rabbitMQProperties;
    private final CaptainService captainService;

    @Autowired
    public TeamCapReceiver(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate,
                           RabbitMQProperties rabbitMQProperties, CaptainService captainService) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMQProperties = rabbitMQProperties;
        this.captainService = captainService;
    }

    /**
     *
     * Обработка поступающих сообщений с заявками
     * @param message - сообщение с заявкой
     * @param timestampHeader - время того, когда сообщение первый раз попало в RabbitMQ
     *
     */
    @RabbitListener(queues = "${messaging.check-cap-request.queue}")
    public void process(Message<TeamDTO> message, @Header("timestamp_in_ms") String timestampHeader)
                                                                                       throws JsonProcessingException {

        LocalDateTime messageCreationTime = Timestamp.valueOf(timestampHeader).toLocalDateTime();
        logger.info("Получена заявка: {}", message.getPayload());

        boolean capCheck = this.captainService.checkCapByTime(messageCreationTime);

        if(capCheck){

            StatusDTO statusDTO = StatusDTO.builder()
                                           .status(Boolean.FALSE)
                                           .build();

            String jsonResponse = this.objectMapper.writeValueAsString(statusDTO);

            this.rabbitTemplate.convertAndSend(this.rabbitMQProperties.getExchange(),
                                               this.rabbitMQProperties.getCheckCapResult().getBinding(), jsonResponse);


        }else{

            TeamDTO teamDTO = message.getPayload();
            teamDTO.setRequestCreationTime(messageCreationTime);
            this.captainService.prepareCaptain(teamDTO);

        }

    }

}
