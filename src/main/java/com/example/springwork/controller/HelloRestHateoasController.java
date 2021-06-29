package com.example.springwork.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.springwork.domain.HelloSub;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HelloRestHateoasController {

	private static final String TEMPLATE = "Hello, %s";

	@RequestMapping("/helloRestHateoas")
	public HttpEntity<HelloSub> index(
		@RequestParam(value = "name", defaultValue = "World") String name) {

		HelloSub greeting = new HelloSub(String.format(TEMPLATE, name +" - RestController Hateoas"));
		greeting.add(linkTo(methodOn(HelloRestHateoasController.class).index(name)).withSelfRel());

		return new ResponseEntity<>(greeting, HttpStatus.OK);
	}
}