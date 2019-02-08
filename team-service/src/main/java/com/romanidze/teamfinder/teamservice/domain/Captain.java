package com.romanidze.teamfinder.teamservice.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 * 04.02.2019
 *
 * Доменная модель для сущности "Капитан"
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
@Document(collection = "captains")
@TypeAlias("captain")
public class Captain {

    @Id
    private String id;
    private Team team;

    @Field("participant_id")
    private String participantID;

    @Field("participant_identifier")
    private String participantIdentifier;

    @Field("creation_date")
    private LocalDateTime creationDate;

}
