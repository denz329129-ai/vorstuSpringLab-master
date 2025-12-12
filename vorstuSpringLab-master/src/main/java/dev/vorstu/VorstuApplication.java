package dev.vorstu;

import dev.vorstu.repositories.Initializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class VorstuApplication {



	private static Initializer initiator;

	@Autowired
	public VorstuApplication(Initializer initiator) {
		VorstuApplication.initiator = initiator;
	}

	public static void main(String[] args) {

		SpringApplication.run(VorstuApplication.class, args);
		initiator.initial();

	}
}