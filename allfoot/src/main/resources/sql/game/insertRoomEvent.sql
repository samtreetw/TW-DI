INSERT INTO room_event (room_id, player_esn, rank) SELECT
                                                     :room_id,
                                                     :esn,
                                                     (SELECT count(1)
                                                      FROM room_event
                                                      WHERE room_id = :room_id)
                                                   WHERE NOT exists(SELECT 1
                                                                    FROM room_event
                                                                    WHERE player_esn = :esn);