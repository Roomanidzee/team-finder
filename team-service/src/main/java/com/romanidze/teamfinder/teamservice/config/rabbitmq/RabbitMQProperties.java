package com.romanidze.teamfinder.teamservice.config.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 05.02.2019
 *
 * Своя конфигурация для RabbitMQ. Необходима для обозначения exchange, binding и queue
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Component
@ConfigurationProperties(prefix = "messaging")
public class RabbitMQProperties {

    private String exchange;
    private RabbitMQParameters acceptCap;
    private RabbitMQParameters declineCap;
    private RabbitMQParameters checkCapRequest;
    private RabbitMQParameters checkCapResult;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @EqualsAndHashCode
    @ToString
    public static class RabbitMQParameters {
        private String binding;
        private String queue;
    }

}
