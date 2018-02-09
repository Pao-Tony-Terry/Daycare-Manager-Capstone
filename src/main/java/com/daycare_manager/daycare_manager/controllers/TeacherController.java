package com.daycare_manager.daycare_manager.controllers;

import com.daycare_manager.daycare_manager.daos.ChildrenRepository;
import com.daycare_manager.daycare_manager.daos.ReportCardRepository;
import com.daycare_manager.daycare_manager.daos.UsersRepository;
import com.daycare_manager.daycare_manager.model.Child;
import com.daycare_manager.daycare_manager.model.ReportCard;
import com.daycare_manager.daycare_manager.model.User;
import com.daycare_manager.daycare_manager.services.ChildService;
import com.daycare_manager.daycare_manager.services.ReportCardService;
import com.daycare_manager.daycare_manager.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeacherController {

    private final ReportCardService reportCardService;
    private final UserService userService;
    private final ChildService childService;

    public TeacherController(ReportCardService reportCardService, UserService userService, ChildService childService) {
        this.reportCardService = reportCardService;
        this.userService = userService;
        this.childService = childService;
    }


    @GetMapping("/teacher/kid/{childId}/reportcard")
    public String showReportCardForm(@PathVariable Long childId, Model viewModel) {
        Child child = childService.findOne(childId);
        viewModel.addAttribute("child", child);
        viewModel.addAttribute("report_card", new ReportCard());
        return "users/reportcard";
    }


    @PostMapping("/teacher/kid/{childId}/reportcard")
    public String saveReportCard (@ModelAttribute ReportCard reportCard, @PathVariable Long childId){
        Child child = childService.findOne(childId);
        reportCard.setChild(child);
        reportCardService.save(reportCard);
        return "redirect:/user/teacher";
    }


    @GetMapping("/teacher/children")
    public String kidsByTeacher(Model viewModel) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        viewModel.addAttribute("children", childService.findByTeacher(user));
        return "/users/kids_by_teacher";
    }

    // Missing edit and delete teacher profile

    // Edit parent profile (show the form):
    @GetMapping("/teacher/{id}/edit")
    public String showEditForm(@PathVariable long id, Model viewModel){
        User user = userService.findOne(id);
        viewModel.addAttribute("user", user);
        return "/users/edit_teacher_profile";
    }

    // Edit parent profile (populate the form):
    @PostMapping("/teacher/edit")
    public String updateUser(@ModelAttribute User user){
        userService.update(user);
        return "redirect:/login";
    }
}
