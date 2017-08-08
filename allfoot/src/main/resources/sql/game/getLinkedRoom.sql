SELECT
  room_id_to,
  distance
FROM room_link
WHERE room_id_from = ?;