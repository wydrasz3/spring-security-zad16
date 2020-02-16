package pl.sda.zad16.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    public static final String PAGE = "index";
    public static final String ADMIN_PAGE = "admin";
    public static final String USER_PAGE = "user";

    @GetMapping("/")
    public String main() {
        return PAGE;
    }

    @GetMapping("/admin")
    public String admin() {
        return ADMIN_PAGE;
    }

    @GetMapping("/user")
    public String user() {
        return USER_PAGE;
    }
}
