package com.project.etaskify.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        final String securitySchemeName = "BearerAuth";
        final String apiTitle = "AllInOne Api";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )
                .info(new Info().title(apiTitle).version("1.0.0"));

    }

//    @Bean
//    public OperationCustomizer customize() {
//        return (operation, handlerMethod) -> operation
//                .addParametersItem(new Parameter().in("header").required(false).description("Msisdn is required").name(X_TARGET))
//                .addParametersItem(new Parameter().in("header").required(false).description("Language is required").name(HttpHeaders.ACCEPT_LANGUAGE));
//    }

}
