/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.weather.forecast.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.weather.forecast.entity.ListTemp;

/**
 *
 * @author aja001
 */
@Service
public class ForecastService {

    private static final String appId = "dd0d953463b34d635770db052b724c41";
    private static final String URL = "http://api.openweathermap.org/data/2.5/forecast";

    RestTemplate restTemplate;

    public ListTemp getForecastByLocation(String cityId, String units) {
        restTemplate = new RestTemplate();
        String uri = URL + "?id=" + cityId + "&units=" + units + "&appid=" + appId;
        System.out.println(uri);
        return restTemplate.getForObject(uri, ListTemp.class);
    }
}
