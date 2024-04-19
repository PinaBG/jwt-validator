package br.com.pinabg.jwtvalidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class JwtValidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtValidatorApplication.class, args);
	}

}
