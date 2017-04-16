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
public class HelloController extends ApplicationBaseController{

	/**
	 * 返回一个页面和相关属性值
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/greet", method = RequestMethod.GET)
	public String greet(Model model) {
		LOGGER.info("【greet】");
		model.addAttribute("username", "LewJun");
		return "/hello/greet";
	}

	/**
	 * 通过返回"redirect:/hello/greet"完成跳转
	 * @return
	 */
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public String redirect() {
		LOGGER.info("【redirect】");
		return "redirect:/hello/greet";
	}
}
