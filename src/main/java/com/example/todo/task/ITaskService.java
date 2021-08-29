package com.example.todo.task;

import java.util.List;

public interface ITaskService {

	List<Task> getAllTasks();

	Task getTaskById(Long id);

	Task updateTask(Long id, Task order);
	
	Task saveTask(Task order);

	void deleteTask(Long id);
	
	Boolean existsById(Long id);
}
