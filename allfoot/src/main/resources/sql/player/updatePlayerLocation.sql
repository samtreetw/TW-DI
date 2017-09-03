UPDATE player
SET previous_location = location, location = ?
WHERE esn = ?;