package com.tasksquery.services.tasks;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tasksquery.models.Task;
import com.tasksquery.repositories.TaskRepository;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskRepository repository;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void saveTask(Task task) {
		repository.save(task);
	}

	@Override
	public void deleteTask(Task task) {
		repository.delete(task);
	}

	@Autowired
	TaskRepository rep;

	@Override
	public Page<Task> getPageTasks(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Task getTaskById(Integer taskId) {
		Optional<Task> result = repository.findById(taskId);
		return result.isPresent() ? result.get() : null;
	}

}
