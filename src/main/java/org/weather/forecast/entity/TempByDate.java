/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.weather.forecast.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author aja001
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TempByDate {
    
    @JsonProperty("dt_txt")
    private String date;
    @JsonProperty("main")
    private Temp main;

    public Temp getMain() {
        return main;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMain(Temp main) {
        this.main = main;
    }
    
}
