package com.example.restaurantcollection.mapper;

import com.example.restaurantcollection.dto.RestaurantDTO;
import com.example.restaurantcollection.entity.Restaurant;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(source = "city", target = "city")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "estimatedCost", target = "estimatedCost")
    @Mapping(source = "averageRating", target = "averageRating")
    @Mapping(source = "votes", target = "votes")
    RestaurantDTO toDTO(Restaurant restaurant);

    @InheritInverseConfiguration
    Restaurant toRestaurant(RestaurantDTO restaurantDto);
}