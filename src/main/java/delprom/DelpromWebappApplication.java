package delprom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot DELPROM WebApp REST APIs",
				description = "Spring Boot DELPROM WebApp REST APIs Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Selena Delčev",
						email = "selenadelcev411@gmail.com"
						)
				)
		)
public class DelpromWebappApplication{

	public static void main(String[] args) {
		SpringApplication.run(DelpromWebappApplication.class, args);
	}	

}
