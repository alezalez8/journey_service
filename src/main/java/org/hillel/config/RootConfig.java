package org.hillel.config;


import org.hillel.service.TicketClient;
import org.springframework.context.annotation.*;


@Configuration
@ComponentScan("org.hillel")
@PropertySource({"classpath:application.properties"})
public class RootConfig {


    @Bean
    public TicketClient ticketClient() {
        return new TicketClient();
    }

}
