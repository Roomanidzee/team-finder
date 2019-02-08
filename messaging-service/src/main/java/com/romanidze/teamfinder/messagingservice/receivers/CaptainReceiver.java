package com.romanidze.teamfinder.messagingservice.receivers;

import com.romanidze.teamfinder.messagingservice.dto.StatusDTO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * 05.02.2019
 *
 * Консьюмер RabbitMQ, который обрабатывает поступающие заявки по капитанам
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
public class CaptainReceiver {

    private static final Logger logger = LogManager.getLogger(CaptainReceiver.class);

    @RabbitListener(queues = "${messaging.check-cap-result.queue}")
    public void processCapCheck(Message<StatusDTO> message){
        logger.info("Полученный результат проверки капитана: {}", message.getPayload());
    }

    @RabbitListener(queues = "${messaging.decline-cap.queue}")
    public void processCapDecline(Message<StatusDTO> message){
        logger.info("Полученный результат сдачи полномочий капитаном: {}", message.getPayload());
    }

    @RabbitListener(queues = "${messaging.accept-cap.queue}")
    public void processCapAccept(Message<StatusDTO> message){
        logger.info("Полученный результат при назначении капитана: {}", message.getPayload());
    }

}
