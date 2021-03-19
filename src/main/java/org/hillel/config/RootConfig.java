package org.hillel.config;


import org.hillel.service.InMemoryJourneyServiceImpl;
import org.hillel.service.JourneyService;
import org.hillel.service.TicketClient;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("org.hillel")
@PropertySource({"classpath:database.properties"})
public class RootConfig {



    @Bean
      public JourneyService InDBJourneyServiceImpl() {
        return new InDBJourneyServiceImpl();
    }

    @Bean(name = "inMemoryJourneyService")
    public JourneyService InMemoryJourneyServiceImpl() {
        return new InMemoryJourneyServiceImpl("in memory");
    }


    @Bean
    public TicketClient ticketClient() {
        return new TicketClient();
    }

}
