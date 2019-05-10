/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.weather.forecast.controller;

import org.weather.forecast.entity.City;
import org.weather.forecast.entity.ListTemp;
import org.weather.forecast.entity.TempByDate;
import org.weather.forecast.entity.Temp;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.Matchers.containsString;
import org.junit.Before;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.weather.forecast.service.ForecastService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.weather.forecast.daily.ForecastApplication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 *
 * @author aja001
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ForecastApplication.class)
@AutoConfigureMockMvc
public class ForecastControllerUnitTest {

    private final String cityId = "4013720";
    private final String units = "imperial";
    private final String days = "3";
    private ListTemp mockListTemp;

    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext wac;
    @Autowired
    private ForecastController forecastController;
    @MockBean
    private ForecastService forecastService;


    /**
     * Programmatically constructed new entities to mock REST response. Start
     * with standaloneSetup() to test this IndexController.
     */
    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        InternalResourceViewResolver vrs = new InternalResourceViewResolver();
        vrs.setPrefix("/src/main/resources/");
        vrs.setSuffix(".html");
        mockMvc = MockMvcBuilders.standaloneSetup(wac).setViewResolvers(vrs).build();     
        //forecastController = new ForecastController();
        //mockMvc = standaloneSetup(forecastController).build();
        //mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        mockListTemp = new ListTemp();
        City mockCity = new City();
        mockCity.setName("Ciudad Acuna");
        mockCity.setCountry("MX");
        TempByDate tempByDateStub = new TempByDate();
        tempByDateStub.setDate("2019-05-08");
        Temp tempStub = new Temp();
        tempStub.setTemp(23.0);
        tempByDateStub.setMain(tempStub);
        List<TempByDate> listTempByDates = new ArrayList<TempByDate>();
        listTempByDates.add(tempByDateStub);
        mockListTemp.setCity(mockCity);
        mockListTemp.setListTempByDates(listTempByDates);
    }

    /**
     * The ForecastService mock is not null 
     * The HTTP status code 200 is returned
     * The returned content type is text/html;charset=UTF-8 
     * The name of the returned view is forecast 
     * The view contains the "Selected location" string
     */
    @Test
    public void testPostForecast() throws Exception {
        System.out.println("testPostForecast");
        forecastService = new ForecastService();
        assertThat(forecastService).isNotNull();
        //when(forecastService.getForecastByLocation(cityId,units)).thenReturn(mockListTemp);
        mockMvc.perform(post("/forecast").contentType(MediaType.MULTIPART_FORM_DATA).param("cityId", cityId).param("units", units).param("days", days)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Selected location")))
                .andExpect(view().name("forecast")).andDo(print());
    }

    /**
     * The HTTP status code 200 is returned 
     * The returned content type is text/html;charset=UTF-8 
     * The name of the returned view is index 
     * The view contains the "Search a location" string
     */
    @Test
    public void testGetForecast() throws Exception {
        System.out.println("testGetForecast");
        mockMvc.perform(get("/forecast").accept(MediaType.ALL)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Search a location")))
                .andExpect(view().name("index")).andDo(print());
    }

    /**
     * The ForecastService mock is not null 
     * The HTTP status code 200 is returned
     * The returned content type is text/html;charset=UTF-8 
     * The name of the returned view is index 
     * The view contains the "Search a location" string
     */
    @Test
    public void testGetForecastMock() throws Exception {
        System.out.println("testGetForecastMock");
        forecastService = new ForecastService();
        mockMvc.perform(MockMvcRequestBuilders.get("/forecast"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("index"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(content().string(Matchers.containsString("Search a location")))
                .andDo(print());
    }

    /**
     * The ForecastService mock is not null 
     * The HTTP status code 200 is returned
     * The returned content type is text/html;charset=UTF-8 
     * The name of the returned view is forecast 
     * The view contains the "Search a location" string
     */
    @Test
    public void testPostForecastMock() throws Exception {
        System.out.println("testGetForecastMock");
        forecastService = new ForecastService();
        assertThat(forecastService).isNotNull();
        mockMvc.perform(MockMvcRequestBuilders.post("/forecast").param("cityId", cityId).param("units", units).param("days", days).contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("forecast"))
                .andExpect(MockMvcResultMatchers.view().name("forecast"))
                .andExpect(content().string(Matchers.containsString("Selected location")))
                .andDo(print());
    }

}
