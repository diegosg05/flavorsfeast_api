package com.flavors.pe.flavorsfeast.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Flavors Feast API",
                description = "Peruvian restaurant chain API that offers food purchases, reservations at different locations, and a list of products",
                contact = @Contact(
                        name = "Diego Alberto Sanchez Garcia",
                        url = "https://www.linkedin.com/in/diegoalsaga2706/",
                        email = "diegosg2706@gmail.com"
                ),
                version = "2.1",
                license = @License(
                        name = "Standard Software Use License for Diego Alberto Sanchez Garcia"
                )
        ),
        servers = {
                @Server(
                        description = "DEV SERVER",
                        url = "http://localhost:8051/flavorsfeastpe/api/v2.1"
                )
        },
        security = @SecurityRequirement(
                name = "Jwt Cookie"
        )
)
@SecurityScheme(
        name = "Jwt Cookie",
        description = "Access Token from Cookies for my API",
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.COOKIE,
        paramName = "jwtToken"
)
public class SwaggerConfig {
}
