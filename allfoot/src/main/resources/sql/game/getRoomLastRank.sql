SELECT rank
FROM room_rank
WHERE room_id = ? order by rank DESC limit 1;