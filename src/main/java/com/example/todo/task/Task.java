package com.example.todo.task;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Task {

	@Id
	@SequenceGenerator(name = "task_sequence", sequenceName = "task_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
	private Long id;
	private String title;
	private String description;
	private String priority;
	private Date creationDate;
	private Date lastUpdate;
	private Boolean isCompleted;

	public Task() {
		this.creationDate = new Date();
		this.lastUpdate = new Date();
		this.isCompleted = false;
	}

	public Task(String title, String description, String priority) {
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.creationDate = new Date();
		this.lastUpdate = new Date();
		this.isCompleted = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
