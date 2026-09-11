package org.order_service.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTempletConfi {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
