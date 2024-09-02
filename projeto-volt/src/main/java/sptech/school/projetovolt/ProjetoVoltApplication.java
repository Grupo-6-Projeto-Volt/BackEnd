package sptech.school.projetovolt;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Projeto Volt - Back End",
				version = "3.18.9",
				description = "Documentação dos EndPoints referentes a API Back End",
				contact = @Contact(name = "Empresa Impact", url = "https://github.com/Grupo-6-Projeto-Volt")
		)
)
public class ProjetoVoltApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoVoltApplication.class, args);
	}

}
