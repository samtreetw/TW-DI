package com.garmin.di.dto;

import com.garmin.di.dto.enums.PlayerStatus;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2017/8/24
 * Time: 16:53
 */
public class Player {

	String esn;
    private int currentRoomId;
    private int previousRoomId;
    private PlayerStatus playerStatus;
    private String lineId;
    private int score;
    private int extraDistance;

    public String getEsn() {
		return esn;
	}

	public void setEsn(String esn) {
		this.esn = esn;
	}

	public int getCurrentRoomId() {
        return currentRoomId;
    }

    public void setCurrentRoomId(int currentRoomId) {
        this.currentRoomId = currentRoomId;
    }

    public int getPreviousRoomId() {
        return previousRoomId;
    }

    public void setPreviousRoomId(int previousRoomId) {
        this.previousRoomId = previousRoomId;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

    public int getExtraDistance() {
        return extraDistance;
    }

    public void setExtraDistance(int extraDistance) {
        this.extraDistance = extraDistance;
    }
}
