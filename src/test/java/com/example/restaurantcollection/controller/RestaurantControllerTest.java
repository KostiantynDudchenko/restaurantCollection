package com.example.restaurantcollection.controller;

import com.example.restaurantcollection.dto.RestaurantDTO;
import com.example.restaurantcollection.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RestaurantService restaurantService;

    @Test
    public void testCreateRestaurantWithCorrectFields() throws Exception {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setCity("City");
        restaurantDTO.setName("TestRestaurant");
        restaurantDTO.setEstimatedCost(1000);
        restaurantDTO.setAverageRating("5");
        restaurantDTO.setVotes(10);

        when(restaurantService.createRestaurant(any(RestaurantDTO.class))).thenReturn(restaurantDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurantDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateRestaurantNotCorrectFields() throws Exception {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setCity("C");
        restaurantDTO.setName("TestRestaurant");
        restaurantDTO.setEstimatedCost(1000);
        restaurantDTO.setAverageRating("5");
        restaurantDTO.setVotes(10);

        when(restaurantService.createRestaurant(any(RestaurantDTO.class))).thenReturn(restaurantDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurantDTO)))
                .andExpect(status().is4xxClientError());
    }
}