package com.example.springwork.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.example.springwork.domain.Hello;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestCorsController {

	private static final String template = "Hello, %s!";

	private final AtomicLong counter = new AtomicLong();
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/hello-rest-cors")
	public Hello hello(@RequestParam(required = false, defaultValue = "World - RestController CORS") String name) {
		return new Hello(counter.incrementAndGet(), String.format(template, name));
	}

    // @CrossOrigin(origins = "http://localhost:8080")
    // origins
    // methods
    // allowedHeaders
    // exposedHeaders
    // allowCredentials
    // maxAge

}