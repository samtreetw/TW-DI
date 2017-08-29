package com.garmin.di.dto.enums;

import com.sun.istack.Nullable;

public enum ActionEvent {
	CHANGE_SCORE("change_score"),
	STOLE_SCORE("stole_score"),
	HIDE_EVENT("hide_event"),
	BACK_TO_LOBBY("back_to_lobby"),
	ADD_STEPS("add_steps"),
	DOUBLE_SCORE("double_score");
	
	private String name;
	ActionEvent(String name) {
		this.name = name;
	}
	
	@Nullable public static ActionEvent getByName(String name) {
		try {
			return ActionEvent.valueOf(name);
		} catch (Exception e) {
			return null;
		}
	}
}
