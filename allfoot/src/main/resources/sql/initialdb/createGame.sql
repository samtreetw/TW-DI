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
CREATE UNIQUE INDEX IF NOT EXISTS unique_link ON room_link(room_id_from, room_id_to);

--status 0:unlock 1:lock
CREATE TABLE IF NOT EXISTS player (
  esn               VARCHAR,
  location          INT,
  previous_location INT,
  line_id           VARCHAR,
  status            INT DEFAULT 0,
  score             INT DEFAULT 0,
  extra_distance    INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS room_rank (
  room_id    INT,
  player_esn VARCHAR,
  rank       INT
);

CREATE TABLE IF NOT EXISTS room_record (
  room_id    INT,
  player_esn VARCHAR
);

CREATE TABLE IF NOT EXISTS room_event (
  room_id    INT,
  event_type INT,
  event_id   VARCHAR
);

CREATE TABLE IF NOT EXISTS admin_user (
  name    VARCHAR,
  line_id VARCHAR
);

CREATE TABLE IF NOT EXISTS questions (
  question_id   VARCHAR,
  question_text VARCHAR,
  answer_id     INT
);

CREATE TABLE IF NOT EXISTS options (
  question_id  VARCHAR,
  options_id   INT,
  options_text VARCHAR
);

CREATE TABLE IF NOT EXISTS actions (
  action_id     VARCHAR,
  action_text_a VARCHAR,
  action_text_b VARCHAR
);

CREATE TABLE IF NOT EXISTS player_action_queue (
  player_esn VARCHAR,
  action_id  VARCHAR,
  time_stamp DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS score_map (
  rank  INT,
  score INT
);

CREATE TABLE IF NOT EXISTS room_trial (
  room_id    INT,
  player_esn VARCHAR
);