/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.weather.forecast.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author aja001
 */
public class IndexControllerUnitTest {

    private MockMvc mockMvc;
    
    @Autowired
    private IndexController indexController;

    /**
     * Programmatically constructed a MockMvc instance after registering a new IndexController instance.
     * Start with standaloneSetup() to test this IndexController.
     */
    @Before
    public void setUp() {
        indexController = new IndexController();
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/src/main/resources/");
        viewResolver.setSuffix(".html");
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).setViewResolvers(viewResolver).build();
    }

    /**
     * The returned HTTP status code is 200 
     * The returned view name is index
     */
    @Test
    public void testIndex() throws Exception {
        System.out.println("testIndex");    
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andDo(print());
               
    }

}
