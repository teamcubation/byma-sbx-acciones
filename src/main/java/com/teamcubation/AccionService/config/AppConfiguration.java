package com.teamcubation.AccionService.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Getter
@Configuration
public class AppConfiguration {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.port}")
    private Integer applicationPort;

    @Value("${logging.level.root}")
    private String rootLogLevel;


}