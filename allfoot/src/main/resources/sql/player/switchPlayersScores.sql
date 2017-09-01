UPDATE player
SET score = (
    SELECT SUM(score)
    FROM player
    WHERE esn IN ((SELECT esn FROM player WHERE line_id = ?), ?)
  ) - score
WHERE esn IN ((SELECT esn FROM player WHERE line_id = ?), ?);