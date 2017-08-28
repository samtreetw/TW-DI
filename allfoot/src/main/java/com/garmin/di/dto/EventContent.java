package com.garmin.di.dto;

import java.util.ArrayList;
import java.util.List;

public class EventContent {
	private String event;
	private List<String> eventOptions;
	
	public EventContent() {
		eventOptions = new ArrayList<>();
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public List<String> getEventOptions() {
		return eventOptions;
	}
	public void addEventOptions(String eventOptions) {
		this.eventOptions.add(eventOptions);
	}
}

