SELECT room_id
FROM room
WHERE room_id not in (
	SELECT room_id
	FROM room_record
	WHERE player_esn = ?
) ORDER BY RANDOM() LIMIT 1;