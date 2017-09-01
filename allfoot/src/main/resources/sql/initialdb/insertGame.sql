INSERT INTO game (status) VALUES (-1);

INSERT INTO room (room_id, room_phase, name, description) VALUES (0, 0, 'lobby', 'lobby');

-- Phase 1 Rooms
INSERT INTO room (room_id, room_phase, name, description) VALUES (1, 1, 'room 1', 'This is room 1');
INSERT INTO room (room_id, room_phase, name, description) VALUES (2, 1, 'room 2', 'This is room 2');
INSERT INTO room (room_id, room_phase, name, description) VALUES (3, 1, 'room 3', 'This is room 3');
INSERT INTO room (room_id, room_phase, name, description) VALUES (4, 1, 'room 4', 'This is room 4');
INSERT INTO room (room_id, room_phase, name, description) VALUES (5, 1, 'room 5', 'This is room 5');
INSERT INTO room (room_id, room_phase, name, description) VALUES (6, 1, 'room 6', 'This is room 6');
INSERT INTO room (room_id, room_phase, name, description) VALUES (7, 1, 'room 7', 'This is room 7');
INSERT INTO room (room_id, room_phase, name, description) VALUES (8, 1, 'room 8', 'This is room 8');
INSERT INTO room (room_id, room_phase, name, description) VALUES (9, 1, 'room 9', 'This is room 9');
-- Phase 2 Rooms
INSERT INTO room (room_id, room_phase, name, description) VALUES (10, 2, 'room 10', '');
INSERT INTO room (room_id, room_phase, name, description) VALUES (11, 2, 'room 11', '');
INSERT INTO room (room_id, room_phase, name, description) VALUES (12, 2, 'room 12', '');
INSERT INTO room (room_id, room_phase, name, description) VALUES (13, 2, 'room 13', '');
INSERT INTO room (room_id, room_phase, name, description) VALUES (14, 2, 'room 14', '');
INSERT INTO room (room_id, room_phase, name, description) VALUES (15, 2, 'room 15', '');
INSERT INTO room (room_id, room_phase, name, description) VALUES (16, 2, 'room 16', '');
INSERT INTO room (room_id, room_phase, name, description) VALUES (17, 2, 'room 17', '');
INSERT INTO room (room_id, room_phase, name, description) VALUES (18, 2, 'room 18', '');
-- Room events Phase 1
INSERT INTO room_event (room_id, event_type, event_id) VALUES (1, 0, '1');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (2, 0, '2');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (3, 0, '3');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (4, 0, '4');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (5, 0, '5');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (6, 0, '6');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (7, 0, '7');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (8, 0, '8');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (9, 0, '9');
-- Room events Phase 2
INSERT INTO room_event (room_id, event_type, event_id) VALUES (10, 1, 'change_score');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (11, 1, 'stole_score');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (12, 1, 'hide_event');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (13, 1, 'back_to_lobby');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (14, 1, 'add_steps');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (15, 1, 'double_score');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (16, 0, 6);
INSERT INTO room_event (room_id, event_type, event_id) VALUES (17, 0, 7);
INSERT INTO room_event (room_id, event_type, event_id) VALUES (18, 0, 8);
-- Room link
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (0, 1, 100);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (1, 2, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (1, 3, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (1, 4, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (1, 5, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (1, 6, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (1, 7, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (1, 8, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (1, 9, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (2, 1, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (2, 3, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (2, 4, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (2, 5, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (2, 6, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (2, 7, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (2, 8, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (2, 9, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (3, 1, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (3, 2, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (3, 4, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (3, 5, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (3, 6, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (3, 7, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (3, 8, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (3, 9, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (4, 1, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (4, 2, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (4, 3, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (4, 5, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (4, 6, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (4, 7, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (4, 8, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (4, 9, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (5, 1, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (5, 2, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (5, 3, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (5, 4, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (5, 6, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (5, 7, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (5, 8, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (5, 9, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (6, 1, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (6, 2, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (6, 3, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (6, 4, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (6, 5, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (6, 7, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (6, 8, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (6, 9, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (7, 1, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (7, 2, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (7, 3, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (7, 4, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (7, 5, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (7, 6, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (7, 8, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (7, 9, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (8, 1, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (8, 2, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (8, 3, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (8, 4, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (8, 5, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (8, 6, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (8, 7, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (8, 9, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (9, 1, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (9, 2, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (9, 3, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (9, 4, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (9, 5, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (9, 6, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (9, 7, 0);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (9, 8, 0);
-- Players
INSERT INTO player (esn, location, line_id, status, score) VALUES ('1', 0, '0', 0, 0);
INSERT INTO player (esn, location, line_id, status, score) VALUES ('2', 0, '0', 0, 0);
INSERT INTO player (esn, location, line_id, status, score) VALUES ('3', 0, '0', 0, 0);
INSERT INTO player (esn, location, line_id, status, score) VALUES ('4', 0, '0', 0, 0);
INSERT INTO player (esn, location, line_id, status, score) VALUES ('5', 0, '0', 0, 0);
INSERT INTO player (esn, location, line_id, status, score) VALUES ('6', 0, '0', 0, 0);
INSERT INTO player (esn, location, line_id, status, score) VALUES ('7', 0, '0', 0, 0);
INSERT INTO player (esn, location, line_id, status, score) VALUES ('8', 0, '0', 0, 0);
-- Questions
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('1', 'Who are you?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('2', 'Where do you live?', 2);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('3', 'Where are you from?', 3);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('4', 'How old are you?', 4);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('5', 'How tall are you?', 4);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('6', 'How heavy are you?', 4);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('7', 'Are you happy?', 4);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('8', 'I want to play a game...Live or die, make your choice?', 4);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('9', 'Do you speak Chinese?', 4);
-- Options
INSERT INTO options (question_id, options_id, options_text) VALUES ('1', 1, 'Jerry');
INSERT INTO options (question_id, options_id, options_text) VALUES ('1', 2, 'Leo');
INSERT INTO options (question_id, options_id, options_text) VALUES ('1', 3, 'Samuel');
INSERT INTO options (question_id, options_id, options_text) VALUES ('1', 4, 'Chris');
INSERT INTO options (question_id, options_id, options_text) VALUES ('2', 1, 'Taipei');
INSERT INTO options (question_id, options_id, options_text) VALUES ('2', 2, 'Taichung');
INSERT INTO options (question_id, options_id, options_text) VALUES ('2', 3, 'Tainan');
INSERT INTO options (question_id, options_id, options_text) VALUES ('2', 4, 'Kauhsiung');
INSERT INTO options (question_id, options_id, options_text) VALUES ('3', 1, 'Taiwan');
INSERT INTO options (question_id, options_id, options_text) VALUES ('3', 2, 'China');
INSERT INTO options (question_id, options_id, options_text) VALUES ('3', 3, 'America');
INSERT INTO options (question_id, options_id, options_text) VALUES ('3', 4, 'Paris');
INSERT INTO options (question_id, options_id, options_text) VALUES ('4', 1, '29');
INSERT INTO options (question_id, options_id, options_text) VALUES ('4', 2, '30');
INSERT INTO options (question_id, options_id, options_text) VALUES ('4', 3, '31');
INSERT INTO options (question_id, options_id, options_text) VALUES ('4', 4, '32');
INSERT INTO options (question_id, options_id, options_text) VALUES ('5', 1, '160cm');
INSERT INTO options (question_id, options_id, options_text) VALUES ('5', 2, '165cm');
INSERT INTO options (question_id, options_id, options_text) VALUES ('5', 3, '170cm');
INSERT INTO options (question_id, options_id, options_text) VALUES ('5', 4, '175cm');
INSERT INTO options (question_id, options_id, options_text) VALUES ('6', 1, '70kg');
INSERT INTO options (question_id, options_id, options_text) VALUES ('6', 2, '75kg');
INSERT INTO options (question_id, options_id, options_text) VALUES ('6', 3, '80kg');
INSERT INTO options (question_id, options_id, options_text) VALUES ('6', 4, '85kg');
INSERT INTO options (question_id, options_id, options_text) VALUES ('7', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('7', 2, 'No');
INSERT INTO options (question_id, options_id, options_text) VALUES ('8', 1, 'Live');
INSERT INTO options (question_id, options_id, options_text) VALUES ('8', 2, 'Die');
INSERT INTO options (question_id, options_id, options_text) VALUES ('9', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('9', 2, 'No');
-- score map
INSERT INTO score_map (rank, score) VALUES (1, 3);
INSERT INTO score_map (rank, score) VALUES (2, 3);
INSERT INTO score_map (rank, score) VALUES (3, 3);
INSERT INTO score_map (rank, score) VALUES (4, 2);
INSERT INTO score_map (rank, score) VALUES (5, 2);
INSERT INTO score_map (rank, score) VALUES (6, 2);
INSERT INTO score_map (rank, score) VALUES (7, 1);
INSERT INTO score_map (rank, score) VALUES (8, 1);
INSERT INTO score_map (rank, score) VALUES (9, 1);
-- action
INSERT INTO actions (action_id, action_text_a, action_text_b) VALUES ('change_score', 'You have to select a team to exchange your score.', 'One team has exchanged your score.');
INSERT INTO actions (action_id, action_text_a, action_text_b) VALUES ('stole_score', 'You can steal 3 point from other team. \nPlease select a team.', 'One team has exchanged your score.');
INSERT INTO actions (action_id, action_text_a, action_text_b) VALUES ('hide_event', 'You hide one of your room event.', '');
INSERT INTO actions (action_id, action_text_a, action_text_b) VALUES ('back_to_lobby', 'You are transfered to the lobby.', '');
INSERT INTO actions (action_id, action_text_a, action_text_b) VALUES ('add_steps', 'You increase your distance to every room in your rest game.', '');
INSERT INTO actions (action_id, action_text_a, action_text_b) VALUES ('double_score', 'You double up your current score', '');

