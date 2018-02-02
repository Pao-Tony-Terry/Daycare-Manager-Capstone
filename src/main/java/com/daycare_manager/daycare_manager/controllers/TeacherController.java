package com.daycare_manager.daycare_manager.controllers;

import com.daycare_manager.daycare_manager.daos.ChildrenRepository;
import com.daycare_manager.daycare_manager.daos.ReportCardRepository;
import com.daycare_manager.daycare_manager.daos.UsersRepository;
import com.daycare_manager.daycare_manager.model.ReportCard;
import com.daycare_manager.daycare_manager.model.User;
import com.daycare_manager.daycare_manager.services.ReportCardService;
import com.daycare_manager.daycare_manager.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeacherController {

    private final ReportCardService reportCardService;
    private final ChildrenRepository childrenRepository;
    private final ReportCardRepository reportCardRepository;

    public TeacherController(ReportCardService reportCardService, ChildrenRepository childrenRepository,ReportCardRepository reportCardRepository) {
        this.reportCardService = reportCardService;
        this.childrenRepository = childrenRepository;
        this.reportCardRepository = reportCardRepository;
    }


    @GetMapping("/teacher/reportcard")
    public String showReportCardForm(Model viewModel) {
        viewModel.addAttribute("reportCard", new ReportCard());
        return "users/reportcard";
    }


    @PostMapping("/teacher/reportcard")
    public String saveReportCard (@ModelAttribute ReportCard reportCard){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        reportCard.setChild(childrenRepository.findOne(user.getId()));
        reportCardService.save(reportCard);
        return "redirect:/user/teacher";
    }

    @GetMapping("/teacher/children")
    public String kidsByTeacher(Model viewModel) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        viewModel.addAttribute("children", childrenRepository.findByTeacher(user));
        return "/users/kids_by_teacher";
    }
}
