package org.hillel.config;


import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("org.hillel")
@PropertySource({"classpath:database.properties"})
public class RootConfig {

   /* @Bean
    @Scope("prototype")
    public JourneyService InDBJourneyServiceImpl() {
        return new InDBJourneyServiceImpl();
    }


    @Bean
    public TicketClient ticketClient() {
        return new TicketClient();
    }
*/

}
