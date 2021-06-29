package com.example.springwork.domain;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HelloSub extends RepresentationModel<HelloSub> {

	private final String content;

	@JsonCreator
	public HelloSub(@JsonProperty("content") String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}
