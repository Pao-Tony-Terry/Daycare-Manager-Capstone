package com.daycare_manager.daycare_manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    @GetMapping("/")
    public String welcome() {
        return "users/home";
    }



    @GetMapping("/aboutus")
    public String aboutUs() {
        return "dcare_views/about_us";
    }

    @GetMapping("/gallery")
    public String gallery() {
        return "dcare_views/gallery";
    }

    @GetMapping("/staff")
    public String staff() {
        return "dcare_views/staff";
    }

    @GetMapping("/programs")
    public String programsAndFees() {
        return "dcare_views/programs_tuition_fees";
    }

    @GetMapping("/contact")
    public String contactUs() {
        return "dcare_views/contact";
    }

    @GetMapping("/calendar")
    public String calendar() {
        return "dcare_views/calendar";
    }
}
