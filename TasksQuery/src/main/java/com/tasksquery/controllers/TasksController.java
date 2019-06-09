package com.tasksquery.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class TasksController extends BaseController
{

	@Autowired
	private TaskService service;

	@Value("${imgsDir}")
	String propertyValue;

	@Value("${tmpImgsDir}")
	String tmpImgsDir;

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
		model.addAttribute("taskDTO", new TaskDTO());
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("createTask");
		return modelView;
	}

	@RequestMapping(value = {
			"/createTask"
	}, method = RequestMethod.POST)
	public String submitNewTask(@ModelAttribute(name = "taskDTO") TaskDTO taskDTO) throws Exception
	{
		Task taskEntity = new Task();
		taskEntity.setImg(ImgUtils.saveImg(taskDTO, false, propertyValue));

		taskDTO.convertDtoToEntity(taskEntity);

		service.saveTask(taskEntity);

		return "redirect:/tasksQuery";
	}

	@RequestMapping(value = {
			"/taskView"
	}, method = RequestMethod.GET)
	public ModelAndView taskView(@RequestParam(name = "id", required = true) Integer id, HttpServletRequest req)
			throws IOException
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("taskView");

		Task taskEntity = service.getTaskById(id);
		if (taskEntity == null)
			model.addObject("error", "Task was not found");
		else
		{

			String imgPath = String.format("imgs/%s", taskEntity.getImgName());
			model.addObject("imgPath", imgPath);

			TaskDTO taskDto = new TaskDTO();
			taskDto.convertEntityToDto(taskEntity);
			model.addObject("task", taskDto);
		}

		return model;
	}

	@RequestMapping(value = {
			"/submitTask"
	}, method = RequestMethod.POST)
	public @ResponseBody Object submitTask(@RequestParam("id") Integer id,
			@RequestParam("description") String description, @RequestParam("submited") Boolean submited)
	{

		// JsonbHttpMessageConverter - for json ; FormHttpMessageConverter - default
		Task taskEntity = service.getTaskById(id);
		if (taskEntity == null)
			System.out.println("Task was not found");
		else
		{
			taskEntity.setDescription(description);
			taskEntity.setState(submited != null && submited ? 1 : 0);
			service.saveTask(taskEntity);
			TaskDTO taskDto = new TaskDTO();
			taskDto.convertEntityToDto(taskEntity);
		}
		return "tasksQuery";
	}

	@RequestMapping(value = {
			"/preViewTask"
	}, method = RequestMethod.POST, consumes = {
			"multipart/form-data"
	})
	public String getPreViewTask(@ModelAttribute TaskDTO taskDto, Model model) throws Exception
	{
		Task task = new Task();
		taskDto.convertDtoToEntity(task);

		TaskDTO taskPreview = new TaskDTO();
		taskPreview.setUserEmail(taskDto.getUserEmail());
		taskPreview.setDescription(taskDto.getDescription());
		taskPreview.setUserName(taskDto.getUserName());
		ImgUtils.saveImg(taskDto, true, tmpImgsDir);

		String imgPath = String.format("tmpImgs/%s", taskDto.getNameImg());
		model.addAttribute("imgPath", imgPath);

		model.addAttribute("task", taskPreview);
		return "taskView";
	}

}
