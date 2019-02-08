package com.romanidze.teamfinder.teamservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 04.02.2019
 *
 * DTO - объект, необходимый для предоставления информации о команде
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamDTO {

    @JsonProperty("team_id")
    private String id;

    @JsonProperty("participant_id")
    private String captainID;

    @JsonProperty("participant_identifier")
    private String captainIdentifier;

    private LocalDateTime requestCreationTime;

}
