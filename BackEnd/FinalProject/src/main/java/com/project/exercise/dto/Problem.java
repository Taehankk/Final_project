package com.project.exercise.dto;

public class Problem {
	private String problemId;
	private String problemName;
	private String problemAnswer;
	private String category;

	public Problem() {
	}

	public Problem(String problemId, String problemName, String problemAnswer, String category) {
		this.problemId = problemId;
		this.problemName = problemName;
		this.problemAnswer = problemAnswer;
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProblemId() {
		return problemId;
	}

	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}

	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

	public String getProblemAnswer() {
		return problemAnswer;
	}

	public void setProblemAnswer(String problemAnswer) {
		this.problemAnswer = problemAnswer;
	}

	@Override
	public String toString() {
		return "Problem [problemId=" + problemId + ", problemName=" + problemName + ", problemAnswer=" + problemAnswer
				+ ", category=" + category;
	}
}