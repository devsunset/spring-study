package com.example.springwork.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloRestController {

	@RequestMapping("/hello-rest")
	public String hello() {
		return "Hello World - RestController";
	}

}