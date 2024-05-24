package com.example.restaurantcollection.service;

import com.example.restaurantcollection.dto.RestaurantDTO;
import com.example.restaurantcollection.entity.Restaurant;
import com.example.restaurantcollection.exception.EntityNotFoundException;
import com.example.restaurantcollection.mapper.RestaurantMapper;
import com.example.restaurantcollection.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper mapper;

    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = mapper.toRestaurant(restaurantDTO);
        return mapper.toDTO(restaurantRepository.save(restaurant));
    }

    @Transactional()
    public RestaurantDTO updateAverageRatingAndVotes(Long id, RestaurantDTO restaurantNew) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Restaurant with id " + id);
                });

        restaurant.setAverageRating(restaurantNew.getAverageRating());
        restaurant.setVotes(restaurantNew.getVotes());

        return mapper.toDTO(restaurant);
    }

    public Page<RestaurantDTO> getAllRestaurants(Pageable pageable) {
        return restaurantRepository.findAll(pageable).map(mapper::toDTO);
    }

    public Page<RestaurantDTO> getRestaurantsByCity(String city, Pageable pageable) {
        return restaurantRepository.findByCity(city, pageable).map(mapper::toDTO);
    }

    public RestaurantDTO getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException(Restaurant.class.getSimpleName(), id));
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    public Page<RestaurantDTO> getRestaurantsSortedByRating(Pageable pageable) {
        return restaurantRepository.findAllByOrderByAverageRatingDesc(pageable).map(mapper::toDTO);
    }
}
