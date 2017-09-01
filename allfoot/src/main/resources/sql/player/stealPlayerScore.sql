UPDATE player
SET score = CASE
    WHEN esn IN (SELECT esn FROM player WHERE line_id = ?) THEN (score + ?)
    WHEN esn IN (?) THEN (score - ?)
    END
WHERE esn IN ((SELECT esn FROM player WHERE line_id = ?), ?);