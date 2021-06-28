package com.example.springwork.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloRestController {

	@RequestMapping("/hellorest")
	public String index() {
		return "Hello World - RestController";
	}

}