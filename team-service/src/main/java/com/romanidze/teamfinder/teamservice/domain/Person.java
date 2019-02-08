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

/**
 * 04.02.2019
 *
 * Доменная модель для сущности "Участник"
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
@Document(collection = "persons")
@TypeAlias("person")
public class Person {

    @Id
    private String id;
    private String identifier;

}
