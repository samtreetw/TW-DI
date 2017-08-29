package com.garmin.di.dto;

import com.garmin.di.dto.enums.EventType;

public class RoomEvent {
	private String eventId;
	private EventType eventType;
	private EventContent eventContent;
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	public EventContent getEventContent() {
		return eventContent;
	}
	public void setEventContent(EventContent eventContent) {
		this.eventContent = eventContent;
	}
}
