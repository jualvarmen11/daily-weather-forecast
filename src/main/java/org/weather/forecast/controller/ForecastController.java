/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.weather.forecast.controller;

import org.weather.forecast.entity.TempByDate;
import org.weather.forecast.entity.ListTemp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.weather.forecast.entity.City;
import org.weather.forecast.service.ForecastService;

/**
 *
 * @author aja001
 */

@Controller
public class ForecastController {

    @Autowired
    private ForecastService forecastService;

    @RequestMapping(value = "/forecast", method = RequestMethod.POST)
    public String getForecast(
            @RequestParam String units,
            @RequestParam String cityId,
            @RequestParam Integer days,
            Map<String, Object> model) throws Exception {
        //Getting a list of temperatures based on selected city
        ListTemp listTemp = forecastService.getForecastByLocation(cityId, units);
        //Creating a LinkedHashMap to keep insertion order of all registered temperatures by date as key. 
        LinkedHashMap<Date, ArrayList<Double>> dailyAvg = new LinkedHashMap<Date, ArrayList<Double>>();
        //Using a SimpleDateFormat object to keep a common input date format for keys
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //e.g. 2019-05-06 06:00:00 
        //Iterating over a returned list of temperatures
        for (TempByDate mainTemp : listTemp.getListTempByDates()) {
            //Check if the returned date is on the LinkedHashMap
            //If there is a date as key, a new temperature is added to the temperature list. 
            //Otherwise, a new ArrayList is created and save into LinkedHashMap
            if (dailyAvg.containsKey(formatter.parse(mainTemp.getDate()))) {
                dailyAvg.get(formatter.parse(mainTemp.getDate())).add(mainTemp.getMain().getTemp());
            } else {
                ArrayList<Double> list = new ArrayList<Double>();
                list.add(mainTemp.getMain().getTemp());
                dailyAvg.put(formatter.parse(mainTemp.getDate()), list);
            }
        }
        //Create a LinkedHashMap to save the temp average for each date
        LinkedHashMap<String, Integer> tempAvg = new LinkedHashMap<String, Integer>();
        for (Map.Entry<Date, ArrayList<Double>> entry : dailyAvg.entrySet()) {
            //Break foor loop when days to show are reached
            if (tempAvg.size() == days) {
                break;
            }
            Date key = entry.getKey();
            ArrayList<Double> value = entry.getValue();
            //Saving the temperature average by date into LinkedHashMap
            tempAvg.put(formatter.format(key), (int) value.stream().mapToDouble(val -> val).average().orElse(0.0));
            System.out.println(formatter.format(key) + " -> " + value.stream().mapToDouble(val -> val).average().orElse(0.0));
        }
        //Returning values to forecast view.
        //Those values will be shown in forecast view
        City city = listTemp.getCity();
        model.put("ListTemp", tempAvg);
        model.put("cityName", city.getName());
        model.put("country", city.getCountry());
        model.put("days", days);
        return "forecast";
    }

    //When a get request is reached to forecast form, a redirect is performed to index page
    @RequestMapping(value = "/forecast", method = RequestMethod.GET)
    public String getForecast() {
        return "redirect:/";
    }

}
