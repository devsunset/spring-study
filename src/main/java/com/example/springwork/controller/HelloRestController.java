package com.example.springwork.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloRestController {

	@GetMapping("/hello-rest")
	public String hello() {
		return "Hello World - RestController";
	}

}