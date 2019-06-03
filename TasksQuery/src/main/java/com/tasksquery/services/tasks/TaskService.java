package com.tasksquery.services.tasks;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tasksquery.models.Task;

public interface TaskService
{

	Page<Task> getPageTasks(Pageable pageable);

	void saveTask(Task task);

	void deleteTask(Task task);

}
