package com.project.exercise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Quiz Show Project API")
						.description("Specialist Team [ANSMOON / KTH]")
						.version("1.16.1 립버전")
						.license(new License().name("GitHub Main Page").url("https://github.com/ANSmoon/Final_project")));
	}
}