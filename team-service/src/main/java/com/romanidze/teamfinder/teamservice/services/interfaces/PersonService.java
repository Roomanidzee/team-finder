package com.romanidze.teamfinder.teamservice.services.interfaces;

import com.romanidze.teamfinder.teamservice.domain.Person;

/**
 * 04.02.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public interface PersonService {

    Person getPersonByID(String personID);

}
