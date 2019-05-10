package org.weather.forecast.daily;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan({"org.weather.forecast.service", "org.weather.forecast.daily","org.weather.forecast.controller","org.weather.forecast.entity"})
@EntityScan({"org.weather.forecast.service", "org.weather.forecast.daily","org.weather.forecast.controller","org.weather.forecast.entity"})
public class ForecastApplicationTests {

	@Test
	public void contextLoads() {
	}

}
