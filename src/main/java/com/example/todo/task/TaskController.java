package com.example.todo.task;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/task")
public class TaskController {
	private final ITaskService taskService;

	@Autowired
	public TaskController(ITaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping()
	public List<Task> getAllTasks(@Nullable @RequestParam("search") String search) {
		
		List<Task> tasks = taskService.getAllTasks();
		if (search != null && !search.isBlank()) {
			tasks = tasks.stream().filter((t) -> {
				return t.getTitle().toLowerCase().contains(search.toLowerCase()) 
						||  t.getDescription().toLowerCase().contains(search.toLowerCase());
			}).collect(Collectors.toList());
		}
		return tasks;
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
