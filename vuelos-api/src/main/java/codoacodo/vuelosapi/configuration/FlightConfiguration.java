package codoacodo.vuelosapi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import codoacodo.vuelosapi.model.Dolar;

@Configuration
public class FlightConfiguration {
    
    @Value("${dollar.url}")
    private String DOLLAR_URL; 

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public Dolar fetchDolar() {
        RestTemplate restTemplate = restTemplate();
        return restTemplate.getForObject(DOLLAR_URL, Dolar.class);
    }

    public Dolar[] fetchAllDolars() {
        RestTemplate restTemplate = restTemplate();
        String apiUrl = "https://dolarapi.com/v1/dolares";
        return restTemplate.getForEntity(apiUrl, Dolar[].class).getBody();
    }
}
