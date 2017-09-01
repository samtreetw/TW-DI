package com.garmin.di.dto;

import java.util.List;

import com.sun.istack.Nullable;

public interface EventContent {
	String getEvent();
	@Nullable
    List<String> getEventOptions();
}
