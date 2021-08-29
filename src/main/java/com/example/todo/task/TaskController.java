package com.example.todo.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
public class TaskController {
	private final ITaskService taskService;
	
	@Autowired
	public TaskController(ITaskService taskService) {
		this.taskService = taskService;
	}
}
