package com.garmin.di.dto;

import java.util.ArrayList;
import java.util.List;

import com.garmin.di.dao.PlayerDao;
import com.garmin.di.dto.enums.ActionEvent;
import com.sun.istack.Nullable;

public class ActionContent implements EventContent {
	private PlayerDao playerDao;
	private ActionEvent action;
	private String notificationTextA;
	private String notificationTextB;
	
	public ActionContent(PlayerDao playerDao, ActionEvent actionEvent, String notificationTextA, String notificationTextB) {
		this.playerDao = playerDao;
		this.action = actionEvent;
		this.notificationTextA = notificationTextA;
		this.notificationTextB = notificationTextB;
	}
	
	public ActionEvent getAction() {
		return action;
	}

	public String getNotificationTextA() {
		return notificationTextA;
	}
	
	public String getNotificationTextB() {
		return notificationTextB;
	}
	
	@Nullable
	@Override
	public String getEvent() {
		
		if (action == null) {
			return null;
		}
		
		switch (action) {
		case CHANGE_SCORE:
		case STOLE_SCORE:
		case HIDE_EVENT:
		case BACK_TO_LOBBY:
		case ADD_STEPS:
		case DOUBLE_SCORE:
			return notificationTextA;
		default:
			return null;
		}
	}
	
	@Override
	@Nullable 
	public List<String> getEventOptions() {
		if (action == null) {
			return null;
		}
		
		switch (action) {
		case CHANGE_SCORE:
		case STOLE_SCORE: {
			List<Player> players = playerDao.getAllPlayers();
			List<String> scores = new ArrayList<>();
			for (Player player: players) {
				scores.add(player.getEsn() + ":" + Integer.toString(player.getScore()));	
			}
			return scores;
		}
		case HIDE_EVENT:
		case BACK_TO_LOBBY:
		case ADD_STEPS:
		case DOUBLE_SCORE:
		default:
			return null;
		}
	}
	
	
}
