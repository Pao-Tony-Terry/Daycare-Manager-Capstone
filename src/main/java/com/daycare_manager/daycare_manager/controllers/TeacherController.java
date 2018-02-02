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
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeacherController {

    private final ReportCardService reportCardService;
    private final ReportCardRepository reportCardRepository;
    private final ChildService childService;

    public TeacherController(ReportCardService reportCardService, ReportCardRepository reportCardRepository, ChildService childService) {
        this.reportCardService = reportCardService;
        this.reportCardRepository = reportCardRepository;
        this.childService = childService;
    }

    @GetMapping("/teacher/reportcard")
    public String showReportCardForm(Model viewModel) {
        viewModel.addAttribute("reportCard", new ReportCard());
        return "users/reportcard";
    }


    @PostMapping("/teacher/reportcard")
    public String saveReportCard (@ModelAttribute ReportCard reportCard, Child child){
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        reportCard.setChild(childService.findOne(child.getId()));
        reportCardService.save(reportCard);
        return "redirect:/user/teacher";
    }


    //    @PostMapping("/ads/create")
//    public String publishAd(
//            @Valid Ad ad,
//            Errors validation,
//            Model model
//    ) {
//        if (validation.hasErrors()) {
//            model.addAttribute("errors", validation);
//            model.addAttribute("ad", ad);
//            return "ads/create";
//        }
//        // Redirect to an appropriate page (show/edit ad or show all ads)
//        return "redirect:/";

    @GetMapping("/teacher/children")
    public String kidsByTeacher(Model viewModel) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        viewModel.addAttribute("children", childService.findOne(user.getId()));
        return "/users/kids_by_teacher";
    }
}
