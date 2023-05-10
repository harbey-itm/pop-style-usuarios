package co.com.popstyle.usuarios;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PopstyleMicroservicioUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PopstyleMicroservicioUsuariosApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
