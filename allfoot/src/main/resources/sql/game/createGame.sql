CREATE TABLE IF NOT EXISTS game (
  status int
);

CREATE TABLE IF NOT EXISTS room (
  room_id int,
  name varchar,
  description varchar
);

CREATE TABLE IF NOT EXISTS room_link (
  room_id_from int,
  room_id_to int,
  distance int
);

CREATE TABLE IF NOT EXISTS player (
  esn varchar,
  location int
);