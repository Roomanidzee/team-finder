package com.romanidze.teamfinder.teamservice.config.mapstruct;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

/**
 * 04.02.2019
 *
 * Класс конфигурации для MapStruct
 * Необходим для обозначения MapStruct в экосистеме Spring
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MapStructConfig {
}
