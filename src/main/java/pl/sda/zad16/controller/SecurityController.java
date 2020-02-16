package pl.sda.zad16.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    public static final String LOGIN_PAGE = "login";

    @GetMapping("/login")
    public String login() {
        return LOGIN_PAGE;
    }
}
