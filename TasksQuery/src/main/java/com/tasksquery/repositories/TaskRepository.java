package com.tasksquery.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tasksquery.models.Task;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Integer>
{

}
