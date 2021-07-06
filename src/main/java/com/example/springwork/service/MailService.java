package com.example.springwork.service;

import com.example.springwork.domain.Mail;
import com.example.springwork.support.handler.MailHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "devsunset@gmail.com";

    @Autowired
	public MailService() {
	}

    public void mailSend(Mail Mail) {
        try {
            MailHandler mailHandler = new MailHandler(mailSender);
            mailHandler.setTo(Mail.getAddress());
            mailHandler.setFrom(MailService.FROM_ADDRESS);
            mailHandler.setSubject(Mail.getTitle());
            String htmlContent = "<p>" + Mail.getMessage() + "<p> <img src='cid:sample-img'>";
            mailHandler.setText(htmlContent, true);
            mailHandler.setAttach("newTest.txt", "static/mail_attach_text.txt");
            mailHandler.setInline("sample-img", "static/mail_attach_image.jpg");
            mailHandler.send();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
