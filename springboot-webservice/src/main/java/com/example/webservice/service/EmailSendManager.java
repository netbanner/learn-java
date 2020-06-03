package com.example.webservice.service;

import com.example.webservice.model.SimpleEmail;


import javax.mail.MessagingException;
import java.util.Map;

public interface EmailSendManager {

    public void sendThymeleafEmail(SimpleEmail simpleEmail, Map<String,Object> model,String templateLocation ) throws MessagingException;
}
