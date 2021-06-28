package com.example.springwork.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

	@RequestMapping("/hello")
	public String index() {
		return "Hello World - RestController";
	}

}