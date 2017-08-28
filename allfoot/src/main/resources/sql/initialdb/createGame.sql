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
  esn               VARCHAR,
  location          INT,
  previous_location INT,
  line_id           VARCHAR,
  status            INT
);

CREATE TABLE IF NOT EXISTS room_record (
  room_id    INT,
  player_esn VARCHAR,
  rank       INT
);

CREATE TABLE IF NOT EXISTS room_event (
  room_id		INT,
  event_type 	INT,
  event_id		INT
);

CREATE TABLE IF NOT EXISTS admin_user (
  name    VARCHAR,
  line_id VARCHAR
);

CREATE TABLE IF NOT EXISTS questions (
<<<<<<< 1aac850c152aedc7294db9e411eb56035f779585
  question_id   INT,
  question_text VARCHAR,
  answer_id     INT
);

CREATE TABLE IF NOT EXISTS options (
  question_id  INT,
  options_id   INT,
  options_text VARCHAR
);
=======
	question_id 	INT,
	question_text 	VARCHAR,
	answer_id 		INT
);

CREATE TABLE IF NOT EXISTS options (
	question_id 	INT,
	options_id 		INT,
	options_text 	VARCHAR
)
>>>>>>> Get room with an event, get answer for the event.

