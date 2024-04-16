package com.playground.spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class WebApplicationStartupConfig implements ApplicationListener<ApplicationReadyEvent> {
    private final static Logger log = LoggerFactory.getLogger(WebApplicationStartupConfig.class);
    @Autowired
    private Environment environment;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.debug("System.getenv: {}", System.getenv("SPRING_PROFILES_ACTIVE"));
        log.debug("System.property: {}", System.getProperty("spring.profiles.active"));
        log.debug("Environment: {}", environment.getProperty("spring.profiles.active"));
        log.debug("Environment: {}", String.join(",", environment.getActiveProfiles()));
    }
}
