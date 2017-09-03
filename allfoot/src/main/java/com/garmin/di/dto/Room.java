package com.garmin.di.dto;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2017/8/5
 * Time: 19:29
 */
public class Room {
    private int roomId;
    private int roomPhase;
    private String name;
    private String desc;
    private RoomEvent roomEvent;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomPhase() {
        return roomPhase;
    }

    public void setRoomPhase(int roomPhase) {
        this.roomPhase = roomPhase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

	public RoomEvent getRoomEvent() {
		return roomEvent;
	}

	public void setRoomEvent(RoomEvent roomEvent) {
		this.roomEvent = roomEvent;
	}
}
