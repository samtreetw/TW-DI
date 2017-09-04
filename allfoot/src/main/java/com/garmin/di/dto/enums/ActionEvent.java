package com.garmin.di.dto.enums;

import com.sun.istack.Nullable;

public enum ActionEvent {
	CHANGE_SCORE("change_score"),
	STOLE_SCORE("stole_score"),
	HIDE_EVENT("hide_event"),
	BACK_TO_LOBBY("back_to_lobby"),
	ADD_STEPS("add_steps"),
	DOUBLE_SCORE("double_score"),
	BOSS("boss");
	
	private String name;
	ActionEvent(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Nullable public static ActionEvent getByName(String name) {
		for (ActionEvent actionEvent : ActionEvent.values()) {
			if (actionEvent.getName().equalsIgnoreCase(name)) {
				return actionEvent;
			}
		}
		return null;
	}
}
