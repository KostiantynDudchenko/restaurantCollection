package com.example.restaurantcollection.dto;

import com.example.restaurantcollection.dto.transfer.New;
import com.example.restaurantcollection.dto.transfer.Update;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class RestaurantDTO {

    @NotBlank(message = "city is mandatory", groups = {New.class})
    @Size(min = 3, max = 100, message = "city must be between 3 and 100 characters", groups = {New.class})
    private String city;

    @NotBlank(message = "Name is mandatory", groups = {New.class})
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters", groups = {New.class})
    private String name;

    @NotNull(message = "Estimated cost is mandatory", groups = {New.class})
    @Min(value = 0, message = "Estimated cost must be greater than or equal to 0", groups = {New.class})
    private Integer estimatedCost;

    @NotNull(message = "Average rating is mandatory", groups = {New.class, Update.class})
    @DecimalMin(value = "0.0", inclusive = false, message = "Average rating must be greater than 0", groups = {New.class, Update.class})
    @DecimalMax(value = "5.0", message = "Average rating must be less than or equal to 5", groups = {New.class, Update.class})
    private String averageRating;

    @NotNull(message = "Votes is mandatory", groups = {New.class, Update.class})
    @Min(value = 0, message = "Votes must be greater than or equal to 0", groups = {New.class, Update.class})
    private Integer votes;
}