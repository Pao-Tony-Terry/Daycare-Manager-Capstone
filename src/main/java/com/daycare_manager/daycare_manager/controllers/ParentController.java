package com.daycare_manager.daycare_manager.controllers;


import com.daycare_manager.daycare_manager.daos.ChildrenRepository;
import com.daycare_manager.daycare_manager.daos.UsersRepository;
import com.daycare_manager.daycare_manager.model.Child;
import com.daycare_manager.daycare_manager.model.User;
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

    private UsersRepository usersRepository;

    private final UserService userService;


    public ParentController(ChildrenRepository childrenRepository, UsersRepository usersRepository, UserService userService) {
        this.childrenRepository = childrenRepository;
        this.usersRepository = usersRepository;
        this.userService = userService;
    }

    @PostMapping("parent/edit")
    public String updateUser(@ModelAttribute User user){
        userService.update(user);
        return "redirect:/posts";
    }

    @GetMapping("/parent/enroll")
    public String showEnrollForm(Model viewModel) {
        // new Child to catch the form to enroll one kid
        viewModel.addAttribute("child", new Child());
        return "users/enroll";
    }


    // Pending to assing teacher to each kid.
    @PostMapping("/parent/enroll")
    public String enrollAChild(@ModelAttribute Child child) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        child.setParent(usersRepository.findOne(user.getId()));
        childrenRepository.save(child);
        return "redirect:/user/parent";
    }


    @GetMapping("/parent/children")
    public String allTheKids(Model viewModel) {
        viewModel.addAttribute("children", childrenRepository.findAll());
        return "/users/kids_by_parent";

    }


//    @GetMapping("posts/{id}/edit")
//    public String showEditForm(@PathVariable long id, Model viewModel){
//        Post post = postService.findOne(id);
//        viewModel.addAttribute("post", post);
//        return "/blog_template/edit";
//    }
//
//    @GetMapping("posts/{id}/delete")
//    public String deletePost(@PathVariable long id, Model viewModel){
//        postService.delete(id);
//        return "redirect:/posts";
//    }
//
//
//    @PostMapping("posts/edit")
//    public String updatePost(@ModelAttribute Post post){
//        postService.update(post);
//        return "redirect:/posts";
//    }



}
