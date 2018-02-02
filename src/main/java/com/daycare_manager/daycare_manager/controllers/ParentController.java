package com.daycare_manager.daycare_manager.controllers;


import com.daycare_manager.daycare_manager.daos.ChildrenRepository;
import com.daycare_manager.daycare_manager.daos.ReportCardRepository;
import com.daycare_manager.daycare_manager.daos.UsersRepository;
import com.daycare_manager.daycare_manager.model.Child;
import com.daycare_manager.daycare_manager.model.User;
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
public class ParentController {

    private ChildrenRepository childrenRepository;

    private final UserService userService;

    private ReportCardRepository reportCardRepository;

    private final ReportCardService reportCardService;


    public ParentController(ChildrenRepository childrenRepository,
                            UserService userService,
                            ReportCardRepository reportCardRepository,
                            ReportCardService reportCardService) {
        this.childrenRepository = childrenRepository;
        this.userService = userService;
        this.reportCardRepository = reportCardRepository;
        this.reportCardService = reportCardService;
    }


    @GetMapping("/parent/{id}/edit")
    public String showEditForm(@PathVariable long id, Model viewModel){
        User user = userService.findOne(id);
        viewModel.addAttribute("user", user);
        return "/users/edit_parent_profile";
    }


    @PostMapping("/parent/edit")
    public String updateUser(@ModelAttribute User user){
        userService.update(user);
        return "redirect:/login";
    }



    @GetMapping("parent/{id}/delete")
    public String deleteParentProfile(@PathVariable long id){
        userService.delete(id);
        return "users/home";
    }


    @GetMapping("/parent/enroll")
    public String showEnrollForm(Model viewModel) {
        // new Child to catch the form to enroll one kid
        viewModel.addAttribute("child", new Child());
        return "users/enroll";
    }


    // Pending to assign teacher to each kid.  ==================================================
    @PostMapping("/parent/enroll")
    public String enrollAChild(@ModelAttribute Child child) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        child.setParent(userService.findOne(user.getId()));
        childrenRepository.save(child);
        return "redirect:/user/parent";
    }


    @GetMapping("/parent/children")
    public String kidsByParent(Model viewModel) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        viewModel.addAttribute("children", childrenRepository.findByParent(user));
        return "/users/kids_by_parent";
    }



    @GetMapping("/parent/kid/{id}/edit")
    public String showEditFormForKid(@PathVariable long id, Model viewModel){
        User user = userService.findOne(id);
        Child child = childrenRepository.findOne(id);
        viewModel.addAttribute("user", user);
        viewModel.addAttribute("child", child);
        return "/users/edit_kid";
    }


    @PostMapping("/parent/kid/edit/{parentId}")
    public String updateKid(@ModelAttribute Child child, @PathVariable long parentId){
        User user = userService.findOne(parentId);
        child.setParent(user);
        childrenRepository.save(child);
        return "redirect:/login";
    }

    @GetMapping("/parent/kid//{childId}/reportcard_by_kid/{parentId}")
    public String reportCardByKid(Model viewModel, @PathVariable long childId, @PathVariable long parentId) {
        User parent = userService.findOne(parentId);
        Child child = (Child) childrenRepository.findByParent(parent);
        System.out.println(child);
        viewModel.addAttribute("child", child);
        viewModel.addAttribute("parent", parent);
        viewModel.addAttribute("reportcard", reportCardRepository.findByChild(child));
        return "/users/reportcard_by_kid";
    }


    @GetMapping("parent/kid/{id}/delete")
    public String deleteChildRecord(@PathVariable long id){
        childrenRepository.delete(id);
        return "users/home";
    }


    // Method to show all the kids from all the parents (not being used):
//    @GetMapping("/parent/children")
//    public String allTheKids(Model viewModel) {
//        viewModel.addAttribute("children", childrenRepository.findAll());
//        return "/users/kids_by_parent";
//
//    }

}
