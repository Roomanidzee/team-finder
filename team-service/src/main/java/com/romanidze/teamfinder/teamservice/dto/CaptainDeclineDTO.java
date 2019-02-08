package com.romanidze.teamfinder.teamservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;

/**
 * 04.02.2019
 *
 * DTO - объект для назначения капитана
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
public class CaptainDeclineDTO {

    @JsonProperty("team_id")
    private String teamID;

    @JsonProperty("cap_id")
    private String captainID;

}
