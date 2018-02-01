
package com.daycare_manager.daycare_manager.controllers;

import com.daycare_manager.daycare_manager.model.Notification;
import com.daycare_manager.daycare_manager.services.TwilioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TwilioController {

    private final TwilioService twilioService;



    // Constructor:
    public TwilioController(TwilioService twilioService) {
        this.twilioService = twilioService;
    }

//    @GetMapping ("/user/sendSMS")
//    @ResponseBody
//    public String sendSMS () {
//
//        return twilioService.sendSMS(new PhoneNumber("+17203930339"), new PhoneNumber("+12104053232"), "https://cdn.pixabay.com/photo/2013/12/12/03/08/kitten-227009_960_720.jpg");
//
//    }


    @GetMapping("/user/sendSMS")
    public String showSMSform(Model viewModel) {
        // new User to catch the form
        viewModel.addAttribute("notification", new Notification());
        return "users/sign-up";
    }

//    @PostMapping("/user/sign-up")
//    public String singUpNewUser(@ModelAttribute User user, @RequestParam(name = "is_employee", defaultValue = "false") boolean isEmployee) {
//        // we need to hash passwords (using security configuration), after changing in the configuration class, create
//        // the passwordEncoder in this controller.
//        user.setEmployee(isEmployee);
//        String hash = encoder.encode(user.getPassword());
//        user.setPassword(hash);
//        userService.save(user);
//        return "redirect:/login";
//
//    }


    // 1st test --> it works,  without using the service: //

    // Find your Account Sid and Token at twilio.com/user/account
//    @Value("${twilio.account.id}")
//    private String accountId;
//
//    @Value("${twilio.token.id}")
//    private String tokenId;
//
//    @Value("${twilio.number}")
//    private String twilioNumber;
//
//    @GetMapping("/testTwilio")
//    @ResponseBody
//    public String testingTwilio(){
//        Twilio.init(accountId, tokenId);
//
//        Message message = Message.creator(
//                new PhoneNumber("+12107082724"),  // this is the user's number (to)
//                new PhoneNumber("+12564748407"),    // this is my twilio number (from)
//                "We are glad to inform you that RJ is doing great").create();
//        return  message.getSid();
//    }

    // 1st test ends here.




}

