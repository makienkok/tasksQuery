package com.tasksquery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping(name = "/test")
public class MainController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
    public String indexController( Model model) throws Exception
    {
		System.out.println("Hello");
		return "index";
    }

}
