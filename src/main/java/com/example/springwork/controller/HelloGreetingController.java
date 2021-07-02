package com.example.springwork.controller;

import com.example.springwork.domain.HelloGreeting;
import com.example.springwork.domain.HelloMessage;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class HelloGreetingController {

  @MessageMapping("/hellowebsocket")
  @SendTo("/topic/greetings")
  public HelloGreeting greeting(HelloMessage message) throws Exception {
    Thread.sleep(1000); // simulated delay
    return new HelloGreeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
  }

}