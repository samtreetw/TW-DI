package com.garmin.di.dto;

public class RoomEvent {
	private int eventId;
	private EventType eventType;
	private EventContent eventContent;
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
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
