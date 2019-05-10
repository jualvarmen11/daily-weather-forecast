package org.weather.forecast.daily;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.weather.forecast.service", "org.weather.forecast.daily","org.weather.forecast.controller","org.weather.forecast.entity"})
@EntityScan({"org.weather.forecast.service", "org.weather.forecast.daily","org.weather.forecast.controller","org.weather.forecast.entity"})
public class ForecastApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForecastApplication.class, args);
    }
}
