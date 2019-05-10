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
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.weather.forecast.entity.ListTemp;
import org.weather.forecast.entity.City;
import org.weather.forecast.entity.TempByDate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author aja001
 */
public class ForecastServiceUnitTest {

    private final String cityId = "4013728";  // Ciudad Acuna, MX
    private final String units = "imperial";
    private final String days = "3";
    private Date tomorrow;
    private SimpleDateFormat formatter;
    private ListTemp mockList;

    @MockBean
    private RestTemplate forecastMock;

    /**
     * Programmatically constructed new entities to mock REST response.
     * Start with standaloneSetup() to test this IndexController.
     */
    public void setup() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1); //Could be 0 or 1. It depends on requested time
        mockList = new ListTemp();
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
     * Assert that the HTTP status code 200 is returned
     * Passing mocked values to mocked REST service and comparing results
     */
    @Test
    public void testGetForecastByLocation() throws ParseException {
        System.out.println("testGetForecastByLocation");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("cityId", cityId);
        map.add("units", units);
        map.add("days", days);
        forecastMock = new RestTemplate();
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = forecastMock.postForEntity("http://localhost:8080/forecast", request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
