package com.example.springwork.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.example.springwork.domain.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestfulController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/helloRestful")
	public Hello hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Hello(counter.incrementAndGet(), String.format(template, name+" - RestController RESTful (use Jackson &  return json)"));
	}
}