package com.example.todo.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping
	public List<Task> getAllTasks() {
		return taskService.getAllTasks();
	}

	@PostMapping
	public void addNewTask(@RequestBody Task task) {
		taskService.saveTask(task);
	}

	@PutMapping("{taskId}")
	public void updateTask(@PathVariable("taskId") Long taskId, @RequestBody Task task) {
		taskService.updateTask(taskId, task);
	}

	@DeleteMapping("{taskId}")
	public void deleteTask(@PathVariable("taskId") Long taskId) {
		taskService.deleteTask(taskId);
	}
}
