package com.tasksquery.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tasksquery.models.Task;
import com.tasksquery.models.TaskDTO;
import com.tasksquery.services.tasks.TaskService;
import com.tasksquery.utils.ImgUtils;

@Controller
public class TasksController extends BaseController {

	@Autowired
	private TaskService service;

	@RequestMapping(value = { "/tasksQuery" }, method = RequestMethod.GET)
	public String listTasks(@PageableDefault(size = 3, sort = "id") Pageable pageable, Model model) {
		Page<Task> page = service.getPageTasks(pageable);
		List<Sort.Order> sortOrders = page.getSort().stream().collect(Collectors.toList());
		if (sortOrders.size() > 0) {
			Sort.Order order = sortOrders.get(0);
			model.addAttribute("sortProperty", order.getProperty());
			model.addAttribute("sortDesc", order.getDirection() == Sort.Direction.DESC);
		}
		model.addAttribute("page", page);
		return "tasksQuery";
	}

	@RequestMapping(value = { "/createTask", "editTask" }, method = RequestMethod.GET)
	public ModelAndView getNewTaskForm(Model model) {
		model.addAttribute("taskDTO", new TaskDTO());
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("createTask");
		return modelView;
	}

	@RequestMapping(value = { "/createTask", "/submitTasks" }, method = RequestMethod.POST)
	public String submitNewTask(@ModelAttribute(name = "taskDTO") TaskDTO taskDTO) throws Exception {
		MultipartFile mpFile = taskDTO.getImg();
		byte[] resizedImg = null;
		String nameImg = null;
		if (taskDTO != null && mpFile != null) {
			nameImg = mpFile.getOriginalFilename();
			if (!ImgUtils.checkImgExtention(nameImg))
				throw new Exception("You have to use picture with extention : png, img, jpg ");

			resizedImg = ImgUtils.resizeImg(mpFile);
		}

		Task taskEntity = new Task();
		taskEntity.setImg(resizedImg);
		taskDTO.setNameImg(nameImg);
		taskDTO.convertDtoToEntity(taskEntity);

		service.saveTask(taskEntity);

		return "redirect:/tasksQuery";
	}

	@RequestMapping(value = { "/taskView" }, method = RequestMethod.GET)
	public ModelAndView taskView(@RequestParam("id") Integer id) {

		ModelAndView model = new ModelAndView();
		model.setViewName("submitTasks");

		Task taskEntity = service.getTaskById(id);
		if (taskEntity == null)
			model.addObject("error", "Task was not found");
		else {
			TaskDTO taskDto = new TaskDTO();
			taskDto.convertEntityToDto(taskEntity);
		}

		return model;
	}

	@RequestMapping(value = { "/preViewTask" }, method = RequestMethod.GET)
	public @ResponseBody Object getPreViewTask(@RequestParam("id") Integer id) {

		// JsonbHttpMessageConverter
		Task taskEntity = service.getTaskById(id);
		if (taskEntity == null)
			System.out.println("Error");// model.addObject("error", "Task was not found");
		else {
			TaskDTO taskDto = new TaskDTO();
			taskDto.convertEntityToDto(taskEntity);
		}
		return null;
	}
}
