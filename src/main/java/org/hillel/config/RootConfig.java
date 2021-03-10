package org.hillel.config;


import org.hillel.service.InMemoryJourneyServiceImpl;
import org.hillel.service.JourneyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RootConfig {

    @Bean
    public JourneyService InMemoryJourneyServiceImpl() {
        return new InMemoryJourneyServiceImpl("1");
    }
}
