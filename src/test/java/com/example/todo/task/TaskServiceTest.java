package com.example.todo.task;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

	@Mock
	private TaskRepository taskRepository;
	private ITaskService taskService;

	@BeforeEach
	void setUp() {
		taskService = new TaskServiceImpl(taskRepository);
		taskRepository.save(new Task("Test task", "Test", TaskPriority.LOW));
	}

	@Test
	void getAllTasks() {
		taskService.getAllTasks();

		verify(taskRepository).findAll();
	}

	@Test
	void addTask() {
		Task task = new Task("Test task", "Test", TaskPriority.LOW);
		taskService.saveTask(task);

		verify(taskRepository).save(task);
	}
}
