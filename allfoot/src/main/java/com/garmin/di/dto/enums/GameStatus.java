package com.garmin.di.dto.enums;

import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2017/8/15
 * Time: 09:43
 */
public enum GameStatus {
    START(0),
    PHASE_1(1),
    PHASE_2(2),
    PHASE_3(3);

    private int id;

    GameStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    /**
     * Lookup GameStatus by id
     * @return GameStatus
     * @throws NoSuchElementException if not found
     */
    public static GameStatus lookup(int id) {
        for (GameStatus gameStatus : GameStatus.values()) {
            if (gameStatus.getId() == id) {
                return gameStatus;
            }
        }
        throw new NoSuchElementException("No GameStatus for " + id);
    }
}
