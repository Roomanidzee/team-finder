package com.romanidze.teamfinder.teamservice.tests.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.romanidze.teamfinder.teamservice.application.Application;
import com.romanidze.teamfinder.teamservice.domain.Captain;
import com.romanidze.teamfinder.teamservice.domain.Person;
import com.romanidze.teamfinder.teamservice.domain.Team;
import com.romanidze.teamfinder.teamservice.dto.TeamDTO;
import com.romanidze.teamfinder.teamservice.repositories.CaptainRepository;
import com.romanidze.teamfinder.teamservice.repositories.PersonRepository;
import com.romanidze.teamfinder.teamservice.repositories.TeamRepository;

import org.junit.Assert;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 06.02.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {Application.class})
public class MongoDBIntegrationTest {

    private MockMvc mockMVC;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CaptainRepository captainRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public MongoDBIntegrationTest(){}

    @Test
    public void testTeamInfo() throws Exception {

        Person person1 = Person.builder()
                               .identifier("Иванов Иван")
                               .build();

        Person person2 = Person.builder()
                               .identifier("Смирнов Семён")
                               .build();

        this.personRepository.save(person1);
        this.personRepository.save(person2);

        List<Person> persons = Arrays.asList(person1, person2);

        Team team = Team.builder()
                        .persons(persons)
                        .build();

        this.teamRepository.save(team);

        Captain captain = Captain.builder()
                                 .participantID(person1.getId())
                                 .participantIdentifier(person1.getIdentifier())
                                 .creationDate(LocalDateTime.now())
                                 .build();

        this.captainRepository.save(captain);

        team.setCaptain(captain);
        this.teamRepository.save(team);

        MvcResult result = this.mockMVC.perform(MockMvcRequestBuilders.get("/" + team.getId()))
                                       .andExpect(MockMvcResultMatchers.status().isOk())
                                       .andReturn();

        String response = result.getResponse().getContentAsString();

        TeamDTO teamDTO = this.objectMapper.readValue(response, TeamDTO.class);

        Assert.assertEquals(team.getId(), teamDTO.getId());
        Assert.assertEquals(team.getCaptain().getParticipantID(), teamDTO.getCaptainID());
        Assert.assertEquals(team.getCaptain().getParticipantIdentifier(), teamDTO.getCaptainIdentifier());

    }

}
