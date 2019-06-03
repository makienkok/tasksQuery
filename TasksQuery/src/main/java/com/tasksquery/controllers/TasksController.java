package com.tasksquery.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tasksquery.models.Task;
import com.tasksquery.repositories.TaskRepository;

@Controller
public class TasksController {

	 @Autowired private TaskRepository repository;
	
	
	
	
	@RequestMapping(value = { "/tasksQuery"}, method = RequestMethod.GET)
	public String listTasks(@PageableDefault(size = 3, sort = "id") Pageable pageable,
	                             Model model) {
	      Page<Task> page = repository.findAll(pageable);
	      List<Sort.Order> sortOrders = page.getSort().stream().collect(Collectors.toList());
	      if (sortOrders.size() > 0) {
	          Sort.Order order = sortOrders.get(0);
	          model.addAttribute("sortProperty", order.getProperty());
	          model.addAttribute("sortDesc", order.getDirection() == Sort.Direction.DESC);
	      }
	      model.addAttribute("page", page);
	      return "tasksQuery";
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
		System.out.println("test");
		return model;
	}
	
}
