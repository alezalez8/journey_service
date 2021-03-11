package org.hillel.config;


import org.hillel.service.InDBJourneyServiceImpl;
import org.hillel.service.JourneyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {

    @Bean
    public JourneyService InDBJourneyServiceImpl() {
        return new InDBJourneyServiceImpl();
    }
}
