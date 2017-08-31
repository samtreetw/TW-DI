package com.garmin.di.dto;

import java.util.List;

import com.sun.istack.Nullable;

public class EventContentImp implements EventContent {
	private String event;
	@Nullable private List<String> eventOptions;
	
	@Override
	public String getEvent() {
		return event;
	}
	
	public void setEvent(String event) {
		this.event = event;
	}
	
	@Override
	@Nullable public List<String> getEventOptions() {
		return eventOptions;
	}
	
	public void setEventOptions(List<String> eventOptions) {
		this.eventOptions = eventOptions;
	}
}

