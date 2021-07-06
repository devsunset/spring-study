package com.example.springwork.controller;


import com.example.springwork.domain.Mail;
import com.example.springwork.service.MailService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/mail-write")
    public String mailWrite() {
        return "mail";
    }

    @PostMapping("/mail-send")
    public void sendMail(Mail mail) {
        this.mailService.mailSend(mail);
    }
}
