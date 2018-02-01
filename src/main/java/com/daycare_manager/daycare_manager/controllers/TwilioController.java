
package com.daycare_manager.daycare_manager.controllers;

import com.daycare_manager.daycare_manager.model.Notification;
import com.daycare_manager.daycare_manager.model.User;
import com.daycare_manager.daycare_manager.services.TwilioService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
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



    @GetMapping("/user/sendSMS")
    public String showSMSform(Model viewModel) {
        // new User to catch the form
        viewModel.addAttribute("notification", new Notification());
        return "users/notification_form";
    }

//    @PostMapping("/user/sendSMS")
//    public String sendNotification(@ModelAttribute Notification notification) {
//
//
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        child.setParent(userService.findOne(user.getId()));
//        childrenRepository.save(child);
//        return "redirect:/user/parent";
//
//    }

    @Value("${twilio.account.id}")
    private String accountId;

    @Value("${twilio.token.id}")
    private String tokenId;

    @Value("${twilio.number}")
    private String twilioNumber;

    @GetMapping("/testTwilio")
    @ResponseBody
    public String testingTwilio(){
        Twilio.init(accountId, tokenId);

        Message message = Message.creator(
                new PhoneNumber("+12107082724"),  // this is the user's number (to)
                new PhoneNumber("+12564748407"),    // this is my twilio number (from)
                "We are glad to inform you that RJ is doing great").create();
        return  message.getSid();
    }






}

