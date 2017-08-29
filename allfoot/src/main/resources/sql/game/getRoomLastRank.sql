SELECT rank
FROM room_record
WHERE room_id = ? order by rank DESC limit 1;