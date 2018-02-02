package com.daycare_manager.daycare_manager.controllers;

import com.daycare_manager.daycare_manager.daos.UsersRepository;
import com.daycare_manager.daycare_manager.model.User;
import com.daycare_manager.daycare_manager.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UsersController {

//    private UsersRepository usersRepository;

    private final UserService userService;

    private PasswordEncoder encoder;


    public UsersController(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }


    @GetMapping("/user/sign-up")
    public String showSignUpForm(Model viewModel) {
        // new User to catch the form
        viewModel.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/user/sign-up")
    public String singUpNewUser(@Valid User user, Errors validation, Model viewModel,  @RequestParam(name = "is_employee", defaultValue = "false") boolean isEmployee) {

        if (validation.hasErrors()){
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("user", user);
            return "users/sign-up";
        }

        user.setEmployee(isEmployee);
        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);
        userService.save(user);
        return "redirect:/login";

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



    @GetMapping("/user/profile")
    public String showProfile() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.isEmployee()) {
            return "redirect:/user/parent"; // Suppose we already have an action for this one
        }
        return "redirect:/user/teacher"; // And another for this one
    }

    @GetMapping("/user/parent")
    public String showParentProfile(Model viewModel) {
//        User user = userService.findOne(id);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        viewModel.addAttribute("user", user);
        return "/users/parent_profile";
    }


    @GetMapping("/user/teacher")
    public String showTeacherProfile() {
        return "/users/teacher_profile";
    }

}

