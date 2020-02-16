package pl.sda.zad16.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    public static final String PAGE = "index";

    @GetMapping
    public String main() {
        return PAGE;
    }
}
