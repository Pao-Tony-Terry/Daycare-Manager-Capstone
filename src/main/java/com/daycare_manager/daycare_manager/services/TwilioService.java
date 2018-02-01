package com.daycare_manager.daycare_manager.services;


import com.daycare_manager.daycare_manager.daos.UsersRepository;
import com.daycare_manager.daycare_manager.model.User;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;

@Service
public class TwilioService  extends HttpServlet {

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

    public String sendSMS(User user) {
        Twilio.init(this.accountId, this.tokenId);
        PhoneNumber phoneNumberTo = new PhoneNumber(user.getPhone());
        PhoneNumber phoneNumberFrom = new PhoneNumber(twilioNumber);

    }

}
