package com.example.restaurantcollection.service;

import com.example.restaurantcollection.dto.RestaurantDTO;
import com.example.restaurantcollection.entity.Restaurant;
import com.example.restaurantcollection.mapper.RestaurantMapper;
import com.example.restaurantcollection.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceImplTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private RestaurantMapper restaurantMapper;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    @Test
    public void testCreateRestaurant() {

        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setCity("City");
        restaurantDTO.setName("TestRestaurant");
        restaurantDTO.setEstimatedCost(1000);
        restaurantDTO.setAverageRating("5");
        restaurantDTO.setVotes(10);

        Restaurant restaurant = new Restaurant();
        restaurant.setCity(restaurantDTO.getCity());
        restaurant.setName(restaurantDTO.getName());
        restaurant.setEstimatedCost(restaurantDTO.getEstimatedCost());
        restaurant.setAverageRating(restaurantDTO.getAverageRating());
        restaurant.setVotes(restaurantDTO.getVotes());


        when(restaurantMapper.toRestaurant(any(RestaurantDTO.class))).thenReturn(restaurant);
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);
        when(restaurantMapper.toDTO(any(Restaurant.class))).thenReturn(restaurantDTO);

        RestaurantDTO createdRestaurantDTO = restaurantService.createRestaurant(restaurantDTO);

        assertEquals(restaurantDTO, createdRestaurantDTO);
    }




}