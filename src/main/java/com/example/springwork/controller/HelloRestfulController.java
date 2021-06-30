package com.example.springwork.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.example.springwork.domain.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestfulController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/hello-restful")
	public Hello hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Hello(counter.incrementAndGet(), String.format(template, name+" - RestController RESTful (use Jackson &  return json)"));
	}

	@GetMapping("/hello-restful-sub")
	public Object hellosub(@RequestParam(value = "name", defaultValue = "World") String name) {
		
		ArrayList<HashMap<String,String>> dummyArrayList = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> dummyHashMap = new HashMap<String,String>();
		dummyHashMap.put("1", "A");
		dummyHashMap.put("2", "B");
		dummyHashMap.put("3", "C");
		dummyArrayList.add(dummyHashMap);

		dummyHashMap = new HashMap<String,String>();
		dummyHashMap.put("4", "a");
		dummyHashMap.put("5", "b");
		dummyHashMap.put("6", "c");
		dummyArrayList.add(dummyHashMap);

		return dummyArrayList;
	}
}