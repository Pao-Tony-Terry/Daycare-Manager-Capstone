
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
        Message message = Message.creator(
                new PhoneNumber("+12107082724"),
                new PhoneNumber("+12564748407"),
                "This is the ship that made the Kessel Run in fourteen parsecs?").create();
//        System.out.println(message.getSid());
        return  message.getSid();
    }
}


//import com.twilio.Twilio;
//        import com.twilio.rest.api.v2010.account.Message;
//        import com.twilio.type.PhoneNumber;
//
//public class Example {
//    // Find your Account Sid and Token at twilio.com/user/account
//    public static final String ACCOUNT_SID = "AC212127ad2e1affdd676c3554744fb6ee";
//    public static final String AUTH_TOKEN = "your_auth_token";
//
//    public static void main(String[] args) {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        Message message = Message.creator(new PhoneNumber("+15558675309"),
//                new PhoneNumber("+15017250604"),
//                "This is the ship that made the Kessel Run in fourteen parsecs?").create();
//
//        System.out.println(message.getSid());
//    }
//}