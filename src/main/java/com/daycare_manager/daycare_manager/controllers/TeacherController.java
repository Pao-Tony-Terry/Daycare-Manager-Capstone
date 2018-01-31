package com.daycare_manager.daycare_manager.controllers;

import com.daycare_manager.daycare_manager.daos.UsersRepository;
import com.daycare_manager.daycare_manager.model.ReportCard;
import com.daycare_manager.daycare_manager.model.User;
import com.daycare_manager.daycare_manager.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherController {
    private UsersRepository usersRepository;

    private final UserService userService;

    public TeacherController(UsersRepository usersRepository, UserService userService) {
        this.usersRepository = usersRepository;
        this.userService = userService;
    }

    @GetMapping("/teacher/{id}/grade")
    public String showReportCardForm(Model viewModel) {
        viewModel.addAttribute("reportCard", new ReportCard());
        return "users/reportcard";
    }
}
