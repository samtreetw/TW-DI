UPDATE game
SET status = -1;

DELETE FROM room;

DELETE FROM room_link;

DELETE FROM player;

DELETE FROM room_event;

DELETE FROM room_rank;

DELETE FROM room_record;

DELETE FROM question;

DELETE FROM options;

DELETE FROM actions;

DELETE FROM player_action_queue;

DELETE FROM score_map;