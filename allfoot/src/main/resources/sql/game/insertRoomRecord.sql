INSERT INTO room_record (room_id, player_esn, rank) SELECT
                                                     :room_id,
                                                     :esn,
                                                     (SELECT count(1)
                                                      FROM room_record
                                                      WHERE room_id = :room_id)
                                                   WHERE NOT exists(SELECT 1
                                                                    FROM room_record
                                                                    WHERE player_esn = :esn);