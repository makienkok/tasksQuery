package com.tasksquery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(name = "/admin")
public class AdminController {

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String indexController( Model model) throws Exception
    {
		System.out.println("Hello admin");
		return "index";
    }

}
