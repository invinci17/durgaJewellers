package durgaJewellers.durgaJewellers.configuration;

import durgaJewellers.durgaJewellers.configuration.environment.DB;
import durgaJewellers.durgaJewellers.configuration.environment.DevDB;
import durgaJewellers.durgaJewellers.configuration.environment.ProdDB;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    @ConditionalOnProperty(name = "project.mode", havingValue = "production")
    public DB getProdDB(){
        return new ProdDB();
    }

    @Bean
    @ConditionalOnProperty(name = "project.mode", havingValue = "development")
    public DB getDevDB(){
        return new DevDB();
    }
}
