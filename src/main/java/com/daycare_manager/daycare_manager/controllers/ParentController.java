package com.daycare_manager.daycare_manager.controllers;


import com.daycare_manager.daycare_manager.daos.ChildrenRepository;
import com.daycare_manager.daycare_manager.daos.ReportCardRepository;
import com.daycare_manager.daycare_manager.daos.UsersRepository;
import com.daycare_manager.daycare_manager.model.Child;
import com.daycare_manager.daycare_manager.model.User;
import com.daycare_manager.daycare_manager.services.ChildService;
import com.daycare_manager.daycare_manager.services.ReportCardService;
import com.daycare_manager.daycare_manager.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ParentController {

    private ChildService childService;

    private final UserService userService;

    private final ReportCardService reportCardService;


    public ParentController(ChildService childService, UserService userService, ReportCardService reportCardService) {
        this.childService = childService;
        this.userService = userService;
        this.reportCardService = reportCardService;
    }

    // Edit parent profile (show the form):
    @GetMapping("/parent/{id}/edit")
    public String showEditForm(@PathVariable long id, Model viewModel){
        User user = userService.findOne(id);
        viewModel.addAttribute("user", user);
        return "/users/edit_parent_profile";
    }

    // Edit parent profile (populate the form):
    @PostMapping("/parent/edit")
    public String updateUser(@ModelAttribute User user){
        userService.update(user);
        return "redirect:/login";
    }


    // Delete parent profile (including children related to parent):
    @GetMapping("parent/{id}/delete")
    public String deleteParentProfile(@PathVariable long id){
        userService.delete(id);
        return "users/home";
    }

    // Enroll a kid (show the form):
    @GetMapping("/parent/enroll")
    public String showEnrollForm(Model viewModel) {
        // new Child to catch the form to enroll one kid
        viewModel.addAttribute("child", new Child());
        return "users/enroll";
    }



    // Enroll a kid (populate the form):
    @PostMapping("/parent/enroll")
    public String enrollAChild(@Valid Child child, Errors validation, Model viewModel) {  // Pending to assing teacher to each kid
        if (validation.hasErrors()){
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("child", child);
            return "users/enroll";
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        child.setParent(userService.findOne(user.getId()));
        childService.save(child);
        return "redirect:/user/parent";
    }


    // Show all the kids by parent:
    @GetMapping("/parent/children")
    public String kidsByParent(Model viewModel) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        viewModel.addAttribute("children", childService.findByParent(user));  // fix this line ... RJ woke up  14:02
        return "/users/kids_by_parent";
    }


    // Show all the kids from all the parents (not being used):
//    @GetMapping("/parent/children")
//    public String allTheKids(Model viewModel) {
//        viewModel.addAttribute("children", childrenRepository.findAll());
//        return "/users/kids_by_parent";
//
//    }


    // Edit a kid (show the form):
    @GetMapping("/parent/kid/{id}/edit")
    public String showEditFormForKid(@PathVariable long id, Model viewModel){
        User user = userService.findOne(id);
        Child child = childService.findOne(id);
        viewModel.addAttribute("user", user);
        viewModel.addAttribute("child", child);
        return "/users/edit_kid";
    }


    // Edit a kid (populate the form):
    @PostMapping("/parent/kid/edit/{parentId}")
    public String updateKid(@ModelAttribute Child child, @PathVariable long parentId){
        User user = userService.findOne(parentId);
        child.setParent(user);
        childService.save(child);
        return "redirect:/user/parent";
    }

    // Delete a kid record:
    @GetMapping("parent/kid/{id}/delete")
    public String deleteChildRecord(@PathVariable long id){
        childService.delete(id);
        return "redirect:/user/parent";
    }



    //  Terry is working on this:
    @GetMapping("/parent/kid//{childId}/reportcard_by_kid")
    public String reportCardByKid(Model viewModel, @PathVariable long childId) {
//        User parent = userService.findOne(parentId);
        Child child = childService.findOne(childId);
        System.out.println(child);
        viewModel.addAttribute("child", child);
//        viewModel.addAttribute("parent", parent);
        viewModel.addAttribute("report_card", reportCardService.findByChild(child));
        return "/users/reportcard_by_kid";
    }





}
