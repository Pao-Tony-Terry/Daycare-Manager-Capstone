
package com.daycare_manager.daycare_manager.controllers;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TwilioController {

    // Find your Account Sid and Token at twilio.com/user/account

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
//        Message message = Message.creator(new PhoneNumber("+12102755716"), new PhoneNumber("+12564748407"), "testing again").create();
        Message message = Message.creator(
                new PhoneNumber("+12107082724"),  // this is the user's number (to)
                new PhoneNumber("+12564748407"),    // this is my twilio number (from)
                "We are glad to inform you that RJ is doing great").create();
        return  message.getSid();
    }


//    @GetMapping ("/sendSMS")
//    @ResponseBody
//    public String sendSMS () {
//
//        return twilioSvc.sendInitialSMS(new PhoneNumber("+17203930339"), new PhoneNumber("+12104053232"), "https://cdn.pixabay.com/photo/2013/12/12/03/08/kitten-227009_960_720.jpg");
//
//    }

}

