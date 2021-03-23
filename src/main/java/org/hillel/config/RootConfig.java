package org.hillel.config;


//import org.hillel.service.InMemoryJourneyServiceImpl;
import org.hillel.service.JourneyService;
import org.hillel.service.TicketClient;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@Configuration
@ComponentScan("org.hillel")
@PropertySource({"classpath:application.properties"})
public class RootConfig {

    /*@Bean
    public DataSource getDataSource() throws Exception {
        return new DataSource();
    }*/

    @Bean
    public TicketClient ticketClient() {
        return new TicketClient();
    }

}
