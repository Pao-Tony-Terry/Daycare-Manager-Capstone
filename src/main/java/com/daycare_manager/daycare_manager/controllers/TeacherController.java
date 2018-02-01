package com.daycare_manager.daycare_manager.controllers;

import com.daycare_manager.daycare_manager.daos.UsersRepository;
import com.daycare_manager.daycare_manager.model.ReportCard;
import com.daycare_manager.daycare_manager.model.User;
import com.daycare_manager.daycare_manager.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeacherController {
//    private UsersRepository usersRepository;

//    private final ReportCardService reportCardService;

//    public TeacherController(UsersRepository usersRepository, ReportCardService reportCardService) {
//        this.usersRepository = usersRepository;
//        this.reportCardService = reportCardService;
//    }

    @GetMapping("/teacher/reportcard")
    public String showReportCardForm(Model viewModel) {
        viewModel.addAttribute("reportCard", new ReportCard());
        return "users/reportcard";
    }

//    @PostMapping("/teacher/reportcard")
//    public String updateReportCard(@ModelAttribute ReportCard reportcard) {
//        reportCardService.update(reportcard);
//        return "redirect:/teacher_profile";
//        }
//    }
}
