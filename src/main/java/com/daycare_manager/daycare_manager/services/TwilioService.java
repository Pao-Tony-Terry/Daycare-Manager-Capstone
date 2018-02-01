package com.daycare_manager.daycare_manager.services;


import com.daycare_manager.daycare_manager.daos.UsersRepository;
import com.daycare_manager.daycare_manager.model.User;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;

@Service
public class TwilioService {

    @Value("${twilio.account.id}")
    private String accountId;

    @Value("${twilio.token.id}")
    private String tokenId;

    @Value("${twilio.number}")
    private String twilioNumber;

    private UsersRepository usersRepository;


    public TwilioService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Iterable<User> findAll() {
        return usersRepository.findAll();
    }

    public String sendSMS(User user) {

        Twilio.init(this.accountId, this.tokenId);

        String response = null;



        PhoneNumber phoneNumberTo = new PhoneNumber(user.getPhone());
        PhoneNumber phoneNumberFrom = new PhoneNumber(twilioNumber);


        return response;

    }

    // for(DatatypeOfEachElementInTheArray   variableNameWeCallEachElement   : variableNameThatsTheArray)



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
