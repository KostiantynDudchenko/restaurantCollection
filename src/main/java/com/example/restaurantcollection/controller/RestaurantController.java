package com.example.restaurantcollection.controller;

import com.example.restaurantcollection.dto.RestaurantDTO;
import com.example.restaurantcollection.dto.transfer.New;
import com.example.restaurantcollection.dto.transfer.Update;
import com.example.restaurantcollection.exception.ArgumentNotValidException;
import com.example.restaurantcollection.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "RestaurantController", description = "Create, Update, Get Restaurants")
@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Operation(
            summary = "Создание ресторана",
            description = "Позволяет добавить ресторан"
    )
    @PostMapping
    public ResponseEntity<RestaurantDTO> create(@Validated(New.class) @RequestBody RestaurantDTO restaurantDTO,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ArgumentNotValidException(bindingResult);
        }

        return new ResponseEntity<>(restaurantService.createRestaurant(restaurantDTO), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Обновление рейтинга и глосования ресторана",
            description = "Позволяет установить рейтинг и колличество голосов"
    )
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO> updateAverageRatingAndVotes(
            @PathVariable Long id, @Validated(Update.class) @RequestBody RestaurantDTO restaurant,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ArgumentNotValidException(bindingResult);
        }

        return new ResponseEntity<>(restaurantService.updateAverageRatingAndVotes(id, restaurant), HttpStatus.OK);
    }

    @Operation(
            summary = "Получение всех ресторанов",
            description = "Позволяет получить все ресторанов"
    )
    @GetMapping
    public ResponseEntity<Page<RestaurantDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(restaurantService.getAllRestaurants(pageable));
    }

    @Operation(
            summary = "Получение ресторанов в указанном городе",
            description = "Позволяет получит рестораны в указанном городе"
    )
    @GetMapping("/query")
    public ResponseEntity<Page<RestaurantDTO>> getByCity(
            @RequestParam @NotBlank @Size(min = 2, max = 100) String city,
            Pageable pageable) {

        return ResponseEntity.ok(restaurantService.getRestaurantsByCity(city, pageable));
    }

    @Operation(
            summary = "Получение ресторана по id",
            description = "Позволяет получить ресторан по id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(restaurantService.getRestaurantById(id), HttpStatus.OK);
    }


    @Operation(
            summary = "Удаление ресторана",
            description = "Позволяет удалить ресторан"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable @Parameter(description = "Id ресторана") Long id) {
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Получение ресторанов по рейтингу",
            description = "Позволяет получить ресторан по рейтингу начитная с наиболее высокого"
    )
    @GetMapping("/sort")
    public ResponseEntity<Page<RestaurantDTO>> getSortedByRating(Pageable pageable) {
        return ResponseEntity.ok(restaurantService.getRestaurantsSortedByRating(pageable));
    }
}
