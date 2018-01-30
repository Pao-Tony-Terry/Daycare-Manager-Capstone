package com.daycare_manager.daycare_manager.controllers;


import com.daycare_manager.daycare_manager.daos.ChildrenRepository;
import com.daycare_manager.daycare_manager.model.Child;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ParentController {

    private ChildrenRepository childrenRepository;

    public ParentController(ChildrenRepository childrenRepository) {
        this.childrenRepository = childrenRepository;
    }


    @GetMapping("/parent/enroll")
    public String showEnrollForm(Model viewModel) {
        // new Child to catch the form to enroll one kid
        viewModel.addAttribute("child", new Child());
        return "users/enroll";
    }

    @PostMapping("/parent/enroll")
    public String enrollAChild(@ModelAttribute Child child) {
        childrenRepository.save(child);
        return "redirect: /user/parent";
    }
}
