CREATE TABLE IF NOT EXISTS game (
  status INT
);

CREATE TABLE IF NOT EXISTS room (
  room_id     INT,
  room_phase  INT,
  name        VARCHAR,
  description VARCHAR
);

CREATE TABLE IF NOT EXISTS room_link (
  room_id_from INT,
  room_id_to   INT,
  distance     INT
);

CREATE TABLE IF NOT EXISTS player (
  esn      VARCHAR,
  location INT,
  line_id  VARCHAR
);

CREATE TABLE IF NOT EXISTS room_event (
  room_id    INT,
  player_esn VARCHAR,
  rank       INT
);

CREATE TABLE IF NOT EXISTS admin_user (
  name    VARCHAR,
  line_id VARCHAR
);