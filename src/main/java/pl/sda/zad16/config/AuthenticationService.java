package pl.sda.zad16.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.zad16.model.Role;
import pl.sda.zad16.model.User;
import pl.sda.zad16.repository.UserRepository;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class AuthenticationService implements UserDetailsService {
    private UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        String rolesStr = user.getRoles().stream().map(Role::getName).collect(Collectors.joining());
        GrantedAuthority roles = new SimpleGrantedAuthority(rolesStr);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), Arrays.asList(roles));
        return userDetails;
    }
}