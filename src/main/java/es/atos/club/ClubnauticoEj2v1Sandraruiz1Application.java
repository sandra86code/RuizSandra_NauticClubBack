package es.atos.club;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClubnauticoEj2v1Sandraruiz1Application {

	public static void main(String[] args) {
		SpringApplication.run(ClubnauticoEj2v1Sandraruiz1Application.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
