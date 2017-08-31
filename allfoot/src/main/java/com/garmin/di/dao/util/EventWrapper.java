package com.garmin.di.dao.util;

import com.garmin.di.dto.EventContent;
import com.garmin.di.dto.EventContentImp;

public class EventWrapper<T extends EventContent> {
	private T rawObject;
	
	public EventWrapper(T rawObject) {
		this.rawObject = rawObject;
	}
	
	public T getRawObject() {
		return rawObject;
	}
	
	public EventContent toEventContent() {
		EventContentImp eventContentImp = new EventContentImp();
		eventContentImp.setEvent(rawObject.getEvent());
		eventContentImp.setEventOptions(rawObject.getEventOptions());
		return eventContentImp;
	}
	
}
