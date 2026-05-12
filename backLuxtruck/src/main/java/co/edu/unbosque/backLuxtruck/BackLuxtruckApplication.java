package co.edu.unbosque.backLuxtruck;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackLuxtruckApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackLuxtruckApplication.class, args);
	}

    @Bean
    ModelMapper getModelMapper() {
		return new ModelMapper();
	}	
}
