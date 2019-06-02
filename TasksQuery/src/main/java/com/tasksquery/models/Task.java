package com.tasksquery.models;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TasksController {

	@RequestMapping(value = { "/tasksQuery"}, method = RequestMethod.GET)
	public ModelAndView homePage()
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("tasksQuery");
		return model;
	}
	
	@RequestMapping(value = {"/createTask"}, method = RequestMethod.GET)
	public ModelAndView userPage() 
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("createTask");
		return model;
	}
	
	@RequestMapping(value = {"/submitTasks"}, method = RequestMethod.GET)
	public ModelAndView adminPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("submitTasks");
		return model;
	}
	
	
	
}
