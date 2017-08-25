package com.garmin.di.util;

/**
 * Created with IntelliJ IDEA.
 * User: tsaisamuel
 * Date: 2017/8/24
 * Time: 17:44
 */
public class SqlHelper {

    private static String FORMAT = "INSERT INTO room_link (room_id_from, room_id_to, distance) values (%1$d, %2$d, 0);";

    public static void main(String[] args) {
        int num = 10;
        for (int i = 1; i < num; i++) {
            for (int j = 1; j < num; j++) {
                if (i != j) {
                    System.out.println(String.format(FORMAT, i, j));
                }
            }
        }
    }
}
