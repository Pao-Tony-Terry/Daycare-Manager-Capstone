
package com.daycare_manager.daycare_manager.controllers;

import com.daycare_manager.daycare_manager.model.Nogit tification;
import com.daycare_manager.daycare_manager.model.User;
import com.daycare_manager.daycare_manager.services.NotitficationService;
import com.daycare_manager.daycare_manager.services.TwilioService;
import com.daycare_manager.daycare_manager.services.UserService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TwilioController {

    private final TwilioService twilioService;

    private final UserService userService;

    private final NotitficationService notitficationService;

    @Value("${twilio.account.id}")
    private String accountId;

    @Value("${twilio.token.id}")
    private String tokenId;

    @Value("${twilio.number}")
    private String twilioNumber;



    // Constructor:


    public TwilioController(TwilioService twilioService, UserService userService, NotitficationService notitficationService) {
        this.twilioService = twilioService;
        this.userService = userService;
        this.notitficationService = notitficationService;
    }


    @GetMapping("/user/sendSMS")
    public String showSMSform(Model viewModel) {
        // new User to catch the form
        viewModel.addAttribute("notification", new Notification());
        return "users/notification_form";
    }


    // Method to send notifications to hardcoded users:
    @PostMapping("/user/sendSMShardcodedUser")
    public String sendNotificationHardcodedUsers(@ModelAttribute Notification notification, Model viewModel) {

        viewModel.addAttribute("user", userService.findOne(3));

        Twilio.init(accountId, tokenId);
        Message message = Message.creator(
                new PhoneNumber("+1"+userService.findOne(1).getPhone()),  // this is the user's number (to)
                new PhoneNumber("+12564748407"),    // this is my twilio number (from)
                notification.getBody()).create();
        message.getSid();
        return "redirect:/user/teacher";


    }

    // Method to all the users:
    @PostMapping("/user/sendSMS")
    public String sendNotification(@ModelAttribute Notification notification, Model viewModel) {

        Iterable<User> users = userService.findAll();
        for (User user: users){
            long id = user.getId();
            Twilio.init(accountId, tokenId);
            Message message = Message.creator(
                    new PhoneNumber("+1"+userService.findOne(id).getPhone()),  // this is the user's number (to)
                    new PhoneNumber("+1"+twilioNumber),                         // this is my twilio number (from)
                    notification.getBody()).create();
            message.getSid();
        }
        notitficationService.save(notification);  //  saves the notification to the table
        return "redirect:/user/teacher";

    }

    // 1st Test:
    @GetMapping("/testTwilio")
    @ResponseBody
    public String testingTwilio(){
        Twilio.init(accountId, tokenId);

        Message message = Message.creator(
                new PhoneNumber("+12107082724"),  // this is the user's number (to)
                new PhoneNumber("+12564748407"),    // this is my twilio number (from)
                "working with Luis").create();
        return  message.getSid();
    }

}

