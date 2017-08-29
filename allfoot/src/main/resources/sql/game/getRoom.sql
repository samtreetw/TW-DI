SELECT *
FROM room r
  JOIN room_event re ON r.room_id = re.room_id
WHERE r.room_id = ?;