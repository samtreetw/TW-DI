package com.garmin.di.domain;

import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2017/8/24
 * Time: 16:47
 */
public enum PlayerStatus {
    FREE(0),
    LOCK(1);

    private int id;

    PlayerStatus(int id) {
        this.id = id;
    }

    /**
     * Lookup GameStatus by id
     *
     * @return GameStatus
     * @throws NoSuchElementException if not found
     */
    public static PlayerStatus lookup(int id) {
        for (PlayerStatus playerStatus : PlayerStatus.values()) {
            if (playerStatus.getId() == id) {
                return playerStatus;
            }
        }
        throw new NoSuchElementException("No PlayerStatus for " + id);
    }

    public int getId() {
        return id;
    }
}
