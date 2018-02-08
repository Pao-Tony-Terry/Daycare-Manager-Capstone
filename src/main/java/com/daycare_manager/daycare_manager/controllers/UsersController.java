package com.daycare_manager.daycare_manager.controllers;

import com.daycare_manager.daycare_manager.daos.UsersRepository;
import com.daycare_manager.daycare_manager.model.User;
import com.daycare_manager.daycare_manager.services.PhoneService;
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

    private final UsersRepository usersRepository;

    private PasswordEncoder encoder;

    private final PhoneService phoneService;


    public UsersController(UserService userService, PasswordEncoder encoder, UsersRepository usersRepository, PhoneService phoneService) {
        this.userService = userService;
        this.encoder = encoder;
        this.usersRepository = usersRepository;
        this.phoneService = phoneService;
    }


    // Sign-up an user:
    @GetMapping("/user/sign-up")
    public String showSignUpForm(Model viewModel) {
        // new User to catch the form
        viewModel.addAttribute("user", new User());
        return "users/sign-up";
    }


    // Sign-up an user:
//    @PostMapping("/user/sign-up")
//    public String singUpNewUser(@Valid User user, Errors validation, Model viewModel,  @RequestParam(name = "is_employee", defaultValue = "false") boolean isEmployee) {
//
//        if (validation.hasErrors()){
//            viewModel.addAttribute("errors", validation);
//            viewModel.addAttribute("user", user);
//            return "users/sign-up";
//        }
//
//        user.setEmployee(isEmployee);
//        String hash = encoder.encode(user.getPassword());
//        user.setPassword(hash);
//        userService.save(user);
//        return "redirect:/login";
//
//    }


    // New method with validations for duplicated emails and usernames:
    @PostMapping("/user/sign-up")
    public String singUpNewUser2(@Valid User user, Errors validation, Model viewModel,  @RequestParam(name = "is_employee", defaultValue = "false") boolean isEmployee)  {

        String username = user.getUsername();
        User existingUsername = usersRepository.findByUsername(username);
        User existingEmail = usersRepository.findByEmail(user.getEmail());



        if (existingUsername != null) {

            validation.rejectValue("username", "user.username", "Duplicated username " + username);

        }

        if (existingEmail != null) {

            validation.rejectValue("email", "user.email", "Duplicated email " + user.getEmail());

        }




        // right format for phone number:
        boolean validatedPhone = phoneService.validatePhoneNumber(user.getPhone());
        if (!validatedPhone) {
            validation.rejectValue("phone", "user.phone", "Invalid format: (xxx) xxx-xxxx");
        }

        // duplicated phone number:
        User existingPhone = usersRepository.findByPhone(user.getPhone());
        if (existingPhone != null) {
            validation.rejectValue("phone", "user.phone", "Phone number already exists " + user.getPhone());
        }


        if (validation.hasErrors()) {
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



    // Show user profile is it is employee or not:
    @GetMapping("/user/profile")
    public String showProfile() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.isEmployee()) {
            return "redirect:/user/parent"; // Suppose we already have an action for this one
        }
        return "redirect:/user/teacher"; // And another for this one
    }


    // Show parent profile:
    @GetMapping("/user/parent")
    public String showParentProfile(Model viewModel) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        viewModel.addAttribute("user", user);
        return "/users/parent_profile";
    }


    // Show teacher profile:
    @GetMapping("/user/teacher")
    public String showTeacherProfile() {
        return "/users/teacher_profile";
    }

}

