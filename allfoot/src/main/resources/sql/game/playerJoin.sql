INSERT INTO player (esn, location) SELECT
                                     :esn,
                                     0
                                   WHERE NOT exists(SELECT 1
                                                    FROM player
                                                    WHERE esn = :esn);