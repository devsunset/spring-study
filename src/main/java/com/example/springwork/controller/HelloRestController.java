package com.example.springwork.controller;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloRestController {

	@ApiOperation(httpMethod = "GET", value = "RestController sample")
	@GetMapping("/hello-rest")
	public String hello() {
		return "Hello World - RestController";
	}

}