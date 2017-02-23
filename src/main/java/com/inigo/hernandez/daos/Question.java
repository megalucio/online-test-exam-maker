package com.inigo.hernandez.daos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;
	private Boolean moreThanOneAnswer;
	private Integer displayOrder;

	protected Question() {
	}

	public Question(String title, Boolean moreThanOneAnswer, Integer displayOrder) {
		this.title = title;
		this.moreThanOneAnswer = moreThanOneAnswer;
		this.displayOrder = displayOrder;
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

	public Boolean getMoreThanOneAnswer() {
		return moreThanOneAnswer;
	}

	public void setMoreThanOneAnswer(Boolean moreThanOneAnswer) {
		this.moreThanOneAnswer = moreThanOneAnswer;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

}