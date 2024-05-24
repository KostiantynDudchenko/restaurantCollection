package com.example.restaurantcollection.repository;

import com.example.restaurantcollection.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Page<Restaurant> findAll(Pageable pageable);

    Page<Restaurant> findByCity(String city, Pageable pageable);

    Optional<Restaurant> findById(Long id);

    Page<Restaurant> findAllByOrderByAverageRatingDesc(Pageable pageable);


}
