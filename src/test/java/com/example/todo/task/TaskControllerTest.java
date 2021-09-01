package com.example.todo.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TaskRepository taskRepository;

	@Test
	void getAllTask() throws Exception {
		Task task1 = new Task("Task test 1", "Task description", TaskPriority.NO_PRIORITY);
		Task task2 = new Task("Task test 2", "Task description", TaskPriority.NO_PRIORITY);
		taskRepository.save(task1);
		taskRepository.save(task2);
		int expectedSize = taskRepository.findAll().size();
		
		mockMvc.perform(get("/api/task").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(expectedSize)));
	}

	@Test
	void addTask() throws Exception {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
		Task newTask = new Task("Test", "Test description", TaskPriority.NO_PRIORITY);
		String taskJson = gson.toJson(newTask);

		mockMvc.perform(post("/api/task").contentType(MediaType.APPLICATION_JSON).content(taskJson))
				.andExpect(status().isOk());
	}

	@Test
	void updateTask() throws Exception {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
		Task task = taskRepository.save(new Task("Test", "Test description", TaskPriority.NO_PRIORITY));
		task.setDescription("Updated description");
		
		mockMvc.perform(
				put("/api/task/" + task.getId()).contentType(MediaType.APPLICATION_JSON).content(gson.toJson(task)))
				.andExpect(status().isOk());
	}
	
	@Test
	void deleteTask() throws Exception {
		Task task = taskRepository.save(new Task("Test", "Test description", TaskPriority.NO_PRIORITY));
		mockMvc.perform(
				delete("/api/task/" + task.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
