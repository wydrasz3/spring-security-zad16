package pl.sda.zad16.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin","/admin/**").hasRole("ADMIN")
                .antMatchers("/user","/user/**").hasRole("USER")
                .antMatchers("/").authenticated()
                .antMatchers("/login").permitAll()
                .antMatchers("/register*").permitAll();

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/appLogin")
                .usernameParameter("inputEmail")
                .passwordParameter("pass")
                .defaultSuccessUrl("/", true);

        http.csrf().disable()
                .headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user@wp.pl")
//                .password("{noop}user123")
//                .roles("USER")
//                .and()
//                .withUser("admin@wp.pl")
//                .password("{noop}admin123")
//                .roles("USER", "ADMIN");

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(getUserQuery())
                .authoritiesByUsernameQuery(getAuthoritiesQuery());
    }

    private String getUserQuery() {
        return "select username, password, enabled from user where username=?";
    }

    private String getAuthoritiesQuery() {
        return "select u.username, r.name from user u " +
                " join user_role ur on u.id = ur.user_id " +
                " join role r on r.id = ur.role_id where u.username=?";
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**");
    }

}
