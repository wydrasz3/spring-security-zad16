package pl.sda.zad16.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan("pl.sda.zad16")
@Import(SecurityConfig.class)
public class Zad16Application {

	public static void main(String[] args) {
		SpringApplication.run(Zad16Application.class, args);
	}

}
