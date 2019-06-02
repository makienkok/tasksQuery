package com.tasksquery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController extends BaseController 
{

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public ModelAndView loginPage(Model model, @RequestParam(value = "error", required = false) String error)
	{

		ModelAndView modelView = new ModelAndView();

		if (error != null) {
			modelView.addObject("error", "Invalid Credentials provided.");
		}

		modelView.setViewName("loginPage");
		return modelView;
	}

}
