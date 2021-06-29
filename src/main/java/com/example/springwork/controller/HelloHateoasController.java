package com.example.springwork.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.springwork.domain.Greeting;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HelloHateoasController {

	private static final String TEMPLATE = "Hello, %s!";

	@RequestMapping("/helloHateoas")
	public HttpEntity<Greeting> greeting(
		@RequestParam(value = "name", defaultValue = "World") String name) {

		Greeting greeting = new Greeting(String.format(TEMPLATE, name +" - RestController Hateoas"));
		greeting.add(linkTo(methodOn(HelloHateoasController.class).greeting(name)).withSelfRel());

		return new ResponseEntity<>(greeting, HttpStatus.OK);
	}
}