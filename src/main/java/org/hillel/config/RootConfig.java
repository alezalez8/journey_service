package org.hillel.config;


import org.hillel.Journey;
import org.hillel.service.InDBJourneyServiceImpl;
import org.hillel.service.JourneyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;
import java.util.Collection;

@Configuration
public class RootConfig {

    @Bean
    @Scope("prototype")
    public JourneyService InDBJourneyServiceImpl() {
        return new InDBJourneyServiceImpl();
    }


}
