package com.tasksquery.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tasksquery.models.Task;
import com.tasksquery.models.TaskDTO;
import com.tasksquery.services.tasks.TaskService;

@Controller
public class TasksController
{

	@Autowired
	private TaskService service;

	@RequestMapping(value = {
			"/tasksQuery"
	}, method = RequestMethod.GET)
	public String listTasks(@PageableDefault(size = 3, sort = "id") Pageable pageable, Model model)
	{
		Page<Task> page = service.getPageTasks(pageable);
		List<Sort.Order> sortOrders = page.getSort().stream().collect(Collectors.toList());
		if (sortOrders.size() > 0)
		{
			Sort.Order order = sortOrders.get(0);
			model.addAttribute("sortProperty", order.getProperty());
			model.addAttribute("sortDesc", order.getDirection() == Sort.Direction.DESC);
		}
		model.addAttribute("page", page);
		return "tasksQuery";
	}

	@RequestMapping(value = {
			"/createTask"
	}, method = RequestMethod.GET)
	public ModelAndView getNewTaskForm(Model model)
	{
		model.addAttribute(new TaskDTO());
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("createTask");
		return modelView;
	}

	@RequestMapping(value = {
			"/createTask"
	}, method = RequestMethod.POST)
	public String submitNewTask(@ModelAttribute("taskDTO") TaskDTO taskDTO, /*
																			 * @RequestParam("img") MultipartFile file,
																			 */
			BindingResult result, Model model)
	{
		Task taskEntity = new Task();
		taskDTO.convertDtoToEntity(taskEntity);

		service.saveTask(taskEntity);

		return "redirect:/tasksQuery";
	}

	@RequestMapping(value = {
			"/submitTasks"
	}, method = RequestMethod.GET)
	public ModelAndView adminPage()
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("submitTasks");
		System.out.println("test");
		return model;
	}

}
