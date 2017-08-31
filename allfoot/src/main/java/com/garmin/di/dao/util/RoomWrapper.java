package com.garmin.di.dao.util;

import com.garmin.di.dto.Room;

public class RoomWrapper {
	
	private Room room;
	private EventWrapper eventWrapper;
	
	public RoomWrapper(Room room) {
		this.room = room;
	}
	
	public Room getRoom() {
		if (eventWrapper != null) {
			room.getRoomEvent().setEventContent(eventWrapper.toEventContent());
		}
		return room;
	}

	public EventWrapper getEventWrapper() {
		return eventWrapper;
	}

	public void setEventWrapper(EventWrapper eventWrapper) {
		this.eventWrapper = eventWrapper;
	}
	
	
}
