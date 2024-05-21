package com.project.exercise.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Arena {
	private int arenaId;
	private String field;
	private String starter;
	private int interest;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date openDay;
	private String content;
	
	public Arena() {
		
	}

	public Arena(int arenaId, String field, String starter, int interest, Date openDay, String content) {
		this.arenaId = arenaId;
		this.field = field;
		this.starter = starter;
		this.interest = interest;
		this.openDay = openDay;
		this.content = content;
	}

	public int getArenaId() {
		return arenaId;
	}

	public void setArenaId(int arenaId) {
		this.arenaId = arenaId;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getStarter() {
		return starter;
	}

	public void setStarter(String starter) {
		this.starter = starter;
	}

	public int getInterest() {
		return interest;
	}

	public void setInterest(int interest) {
		this.interest = interest;
	}

	public Date getOpenDay() {
		return openDay;
	}

	public void setOpenDay(Date openDay) {
		this.openDay = openDay;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Arena [arenaId=" + arenaId + ", field=" + field + ", starter=" + starter + ", interest=" + interest
				+ ", openDay=" + openDay + ", content=" + content + "]";
	}
}
