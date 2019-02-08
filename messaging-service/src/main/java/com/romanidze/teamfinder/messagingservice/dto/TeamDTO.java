package com.romanidze.teamfinder.messagingservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;

/**
 * 05.02.2019
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
public class TeamDTO {

    @JsonProperty("team_id")
    private String id;

    @JsonProperty("participant_id")
    private String captainID;

    @JsonProperty("participant_identifier")
    private String captainIdentifier;

}
