package org.order_service.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class Reclient {
    @Bean
    public RestClient restClient(){
        return RestClient.create();
    }
}

