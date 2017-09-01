INSERT INTO room_rank (room_id, player_esn, rank) SELECT
                                                     :room_id,
                                                     :esn,
                                                     (SELECT count(1)
                                                      FROM rank
                                                      WHERE room_id = :room_id)
                                                   WHERE NOT exists(SELECT 1
                                                                    FROM room_rank
                                                                    WHERE player_esn = :esn);