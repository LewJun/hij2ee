package com.lewjun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author LewJun
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

	@RequestMapping(value = "/greet", method = RequestMethod.GET)
	public String greet(Model model) {
		model.addAttribute("username", "LewJun");
		return "/hello/greet";
	}
}
