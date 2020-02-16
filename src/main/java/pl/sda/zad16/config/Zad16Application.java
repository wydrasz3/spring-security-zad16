package pl.sda.zad16.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.sda.zad16.model.Role;
import pl.sda.zad16.model.User;
import pl.sda.zad16.repository.RoleRepository;
import pl.sda.zad16.repository.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@SpringBootApplication
@ComponentScan("pl.sda.zad16")
@Import(SecurityConfig.class)
@EnableJpaRepositories("pl.sda.zad16.repository")
@EntityScan("pl.sda.zad16.model")
public class Zad16Application implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(Zad16Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if(userRepository.count() == 0) {
            User user = new User("user@wp.pl",
                    passwordEncoder.encode("user123"), true, new HashSet<>(Collections.singletonList(new Role("USER"))));
            userRepository.save(user);

            User admin = new User("admin@wp.pl",
                    passwordEncoder.encode("admin123"), true, new HashSet<>(Collections.singletonList(new Role("USER"))));
            userRepository.save(admin);
        }
    }
}
