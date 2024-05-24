package com.example.restaurantcollection.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Restaurant Api",
                description = "Restaurant", version = "1.0.0",
                contact = @Contact(
                        name = "...",
                        email = "...",
                        url = "..."
                )
        )
)
public class OpenApiConfig {

}
