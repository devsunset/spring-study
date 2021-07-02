package com.example.springwork.controller;

import javax.validation.Valid;

import com.example.springwork.domain.PersonForm;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Controller
public class WebController implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/vaildresults").setViewName("vaildresults");
	}

	@GetMapping("/vaild")
	public String showForm(PersonForm personForm) {
		return "form";
	}

	@PostMapping("/valid")
	public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "form";
		}

		return "redirect:/vaildresults";
	}
}