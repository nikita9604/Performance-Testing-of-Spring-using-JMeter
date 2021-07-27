package com.nikita.springwebexample9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class SpringWebExample9Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebExample9Application.class, args);
	}

}
