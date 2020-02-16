package pl.sda.zad16.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("pl.sda.zad16")
@Import(SecurityConfig.class)
@EnableJpaRepositories("pl.sda.zad16.repository")
@EntityScan("pl.sda.zad16.model")
public class Zad16Application {

	public static void main(String[] args) {
		SpringApplication.run(Zad16Application.class, args);
	}

}
