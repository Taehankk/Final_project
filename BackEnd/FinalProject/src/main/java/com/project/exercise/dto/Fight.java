package com.project.exercise.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Fight {
	private int fightId;
	private int arenaId;
	private String content;
	private String fighter;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fightDay;
	
	public int getFightId() {
		return fightId;
	}
	
	public void setFightId(int fightId) {
		this.fightId = fightId;
	}
	
	public int getArenaId() {
		return arenaId;
	}
	
	public void setArenaId(int arenaId) {
		this.arenaId = arenaId;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getFighter() {
		return fighter;
	}
	
	public void setFighter(String fighter) {
		this.fighter = fighter;
	}
	
	public Date getFightDay() {
		return fightDay;
	}
	
	public void setFightDay(Date fightDay) {
		this.fightDay = fightDay;
	}

	@Override
	public String toString() {
		return "Fight [fightId=" + fightId + ", arenaId=" + arenaId + ", content=" + content + ", fighter=" + fighter
				+ ", fightDay=" + fightDay + "]";
	}
}
