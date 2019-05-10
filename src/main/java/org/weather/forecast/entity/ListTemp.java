/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.weather.forecast.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author aja001
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListTemp {

    @JsonProperty("list")
    private List<TempByDate> listTempByDates;

    @JsonProperty("city")
    private City city;

    public List<TempByDate> getListTempByDates() {
        return listTempByDates;
    }

    public City getCity() {
        return city;
    }

    public void setListTempByDates(List<TempByDate> listTempByDates) {
        this.listTempByDates = listTempByDates;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
