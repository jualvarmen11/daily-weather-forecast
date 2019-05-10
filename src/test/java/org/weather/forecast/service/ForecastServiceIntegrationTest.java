/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.weather.forecast.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.weather.forecast.entity.ListTemp;
import org.weather.forecast.entity.City;
import org.weather.forecast.entity.TempByDate;

/**
 *
 * @author aja001
 */
public class ForecastServiceIntegrationTest {

    private final String cityId = "4013728";  // Ciudad Acuna, MX
    private final String units = "imperial";
    private Date tomorrow;
    private SimpleDateFormat formatter;

    @MockBean
    private ForecastService instance;

    /**
     * Programmatically constructed new entities to assert REST response.
     *
     */
    @Before
    public void setup() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);  //Could be 0 or 1. It depends on requested time
        ListTemp mockList = new ListTemp();
        City mockCity = new City();
        mockCity.setName("Ciudad Acuna");
        mockCity.setName("MX");
        mockList.setCity(mockCity);
        TempByDate mockTempByDate = new TempByDate();
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        tomorrow = calendar.getTime();
        mockTempByDate.setDate(formatter.format(tomorrow));
    }

    /**
     * The ForecastService object is not null 
     * The ListTemp result  is not null
     * The returned city name is equal to "Ciudad Acuna" string
     * The returned country is equal to "Ciudad Acuna" string
     * The returned date is equal to current date
     * The ListTemp result  is not empty
     */
    @Test
    public void testGetForecastByLocation() throws ParseException {
        System.out.println("testGetForecastByLocation");
        instance = new ForecastService();
        assertThat(instance).isNotNull();
        ListTemp result = instance.getForecastByLocation(cityId, units);
        assertThat(result).isNotNull();
        assertThat(result.getCity().getName()).isEqualTo("Ciudad Acuna");
        assertThat(result.getCity().getCountry()).isEqualTo("MX");
        assertThat(formatter.parse(result.getListTempByDates().get(0).getDate())).isEqualTo(formatter.format(tomorrow));
        assertFalse(result.getListTempByDates().isEmpty());
        assertTrue(result.getListTempByDates().size() > 0);

    }
}
