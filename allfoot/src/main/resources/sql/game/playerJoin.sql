INSERT INTO player (esn, location, line_id) SELECT
                                     :esn,
                                     0,
                                     '0'
                                   WHERE NOT exists(SELECT 1
                                                    FROM player
                                                    WHERE esn = :esn);