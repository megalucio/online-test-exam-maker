package com.inigo.hernandez.daos;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Exam {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany
	private List<Question> questions;

	private String name;
	private String description;
	private Double passScore;
	private Double totalScore;
	private Integer duration;

	protected Exam() {
	}

	public Exam(List<Question> questions, String name, String description, Double passScore, Double totalScore,
			Integer duration) {
		this.questions = questions;
		this.name = name;
		this.description = description;
		this.passScore = passScore;
		this.totalScore = totalScore;
		this.duration = duration;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPassScore() {
		return passScore;
	}

	public void setPassScore(Double passScore) {
		this.passScore = passScore;
	}

	public Double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

}