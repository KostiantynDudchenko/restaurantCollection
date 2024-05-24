package com.example.restaurantcollection.service;

import com.example.restaurantcollection.dto.RestaurantDTO;
import com.example.restaurantcollection.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RestaurantService {

    RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO);
    RestaurantDTO updateAverageRatingAndVotes(Long id, RestaurantDTO restaurantNew);
    Page<RestaurantDTO> getAllRestaurants(Pageable pageable);
    Page<RestaurantDTO> getRestaurantsByCity(String city, Pageable pageable);
    RestaurantDTO getRestaurantById(Long id);
    void deleteRestaurant(Long id);
    Page<RestaurantDTO> getRestaurantsSortedByRating(Pageable pageable);

}
