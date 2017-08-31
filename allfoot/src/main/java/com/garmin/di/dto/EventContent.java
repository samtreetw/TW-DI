package com.garmin.di.dto;

import java.util.List;

import com.sun.istack.Nullable;

public interface EventContent {
	public String getEvent();
	@Nullable public List<String> getEventOptions();
}
