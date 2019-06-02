package com.tasksquery.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tasksquery.models.Task;
import com.tasksquery.repositories.TaskRepository;

@Service
@Transactional
public class TaskService 
{
	@Autowired
	TaskRepository rep;
	
	public TaskService(TaskRepository rep) {
		//super();
		this.rep = rep;
	}
	
	public Page<Task>  findAll(Pageable pageable) 
	{
		rep.findAll();
		return null;
	}
}

