SELECT
  room_id_to,
  distance,
  [name]
FROM room_link rl
  JOIN room r ON rl.room_id_to = r.room_id
WHERE room_id_from = ?
      AND r.room_phase <= (SELECT status
                           FROM game
                           LIMIT 1);