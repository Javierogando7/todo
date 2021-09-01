package com.example.todo.task;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@DataJpaTest
public class TaskRepositoryTest {

	@Autowired
	private TaskRepository taskRepository;

	@Test
	void getAllTasks() {
		List<Task> tasksToInsert = Arrays.asList(
				new Task("Test 1", "Test description", TaskPriority.NO_PRIORITY),
				new Task("Test 2", "Test description", TaskPriority.NO_PRIORITY)
		);
		taskRepository.saveAll(tasksToInsert);

		List<Task> tasks = taskRepository.findAll();

		assertThat(tasks.size()).isEqualTo(tasksToInsert.size());
	}

	@Test
	void addTask() {
		Task task = taskRepository.save(
				new Task("Test 1", "Test description", TaskPriority.NO_PRIORITY)
		);

		Task createdTask = taskRepository.getById(task.getId());

		assertThat(task.getId()).isEqualTo(createdTask.getId());
	}

	@Test
	void updateTask() {
		Task task = taskRepository.save(
				new Task("Test 1", "Test description", TaskPriority.NO_PRIORITY)
		);
		
		String newDescription = "Updated description";
		task.setDescription("Updated description");
		taskRepository.save(task);

		assertThat(task.getDescription()).isEqualTo(newDescription);
	}

	@Test
	void deleteTask() {
		Task task = taskRepository.save(
				new Task("Test 1", "Test description", TaskPriority.NO_PRIORITY)
		);
		
		taskRepository.deleteById(task.getId());
		
		assertThat(taskRepository.findAll().size()).isEqualTo(0);
	}
}
