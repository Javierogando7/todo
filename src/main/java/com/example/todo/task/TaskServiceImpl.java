package com.example.todo.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements ITaskService {
	private final TaskRepository taskRepository;

	@Autowired
	public TaskServiceImpl(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	public Task getTaskById(Long taskId) {
		return taskRepository.getById(taskId);
	}

	public Task saveTask(Task task) {
		if (task == null) {
			throw new IllegalStateException("Task can not be null");
		}
		if (task.getTitle() == null || task.getTitle().isEmpty()) {
			throw new IllegalStateException("Tittle can not be null");
		}
		if (task.getId() != null && existsById(task.getId())) {
			throw new IllegalStateException("Id " + task.getId() + " already exists");
		}
		return taskRepository.save(task);
	}

	public Task updateTask(Long taskId, Task task) {
		if (task == null) {
			throw new IllegalStateException("Task can not be null");
		}
		if (task.getTitle() == null || task.getTitle().isEmpty()) {
			throw new IllegalStateException("Tittle can not be null");
		}
		if (!existsById(taskId)) {
			throw new IllegalStateException("Id " + taskId + " don't exists");
		}
		Task taskToUpdate = getTaskById(taskId);
		taskToUpdate.setTitle(task.getTitle());
		taskToUpdate.setDescription(task.getDescription());
		taskToUpdate.setPriority(task.getPriority());
		taskToUpdate.setIsCompleted(task.getIsCompleted());
		taskToUpdate.setLastUpdate(new Date());
		return taskRepository.save(taskToUpdate);
	}

	public void deleteTask(Long taskId) {
		if (!existsById(taskId)) {
			throw new IllegalStateException("Id " + taskId + " don't exists");
		}
		taskRepository.deleteById(taskId);
	}

	public Boolean existsById(Long taskId) {
		return taskRepository.existsById(taskId);
	}

}
