INSERT INTO game (status) VALUES (-1);

INSERT INTO room (room_id, room_phase, name, description) VALUES (0, 0, 'lobby', 'lobby');
-- Phase 1 Rooms
INSERT INTO room (room_id, room_phase, name, description) VALUES (1, 1, 'room 1', 'This is room 1 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (2, 1, 'room 2', 'This is room 2 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (3, 1, 'room 3', 'This is room 3 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (4, 1, 'room 4', 'This is room 4 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (5, 1, 'room 5', 'This is room 5 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (6, 1, 'room 6', 'This is room 6 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (7, 1, 'room 7', 'This is room 7 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (8, 1, 'room 8', 'This is room 8 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (9, 1, 'room 9', 'This is room 9 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (10, 1, 'room 10', 'This is room 10 with question event');
-- Phase 2 Rooms
INSERT INTO room (room_id, room_phase, name, description) VALUES (11, 2, 'room 11', 'This is room 11 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (12, 2, 'room 12', 'This is room 12 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (13, 2, 'room 13', 'This is room 13 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (14, 2, 'room 14', 'This is room 14 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (15, 2, 'room 15', 'This is room 15 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (16, 2, 'room 16', 'This is room 16 with action event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (17, 2, 'room 17', 'This is room 17 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (18, 2, 'room 18', 'This is room 18 with action event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (19, 2, 'room 19', 'This is room 19 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (20, 2, 'room 20', 'This is room 20 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (21, 2, 'room 21', 'This is room 21 with action event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (22, 2, 'room 22', 'This is room 22 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (23, 2, 'room 23', 'This is room 23 with action event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (24, 2, 'room 24', 'This is room 24 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (25, 2, 'room 25', 'This is room 25 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (26, 2, 'room 26', 'This is room 26 with action event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (27, 2, 'room 27', 'This is room 27 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (28, 2, 'room 28', 'This is room 28 with action event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (29, 2, 'room 29', 'This is room 29 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (30, 2, 'room 30', 'This is room 30 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (31, 2, 'room 31', 'This is room 31 with question event');
-- Phase 3 Rooms
INSERT INTO room (room_id, room_phase, name, description) VALUES (32, 3, 'room 31', 'This is room 32 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (33, 3, 'room 31', 'This is room 33 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (34, 3, 'room 31', 'This is room 34 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (35, 3, 'room 31', 'This is room 35 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (36, 3, 'room 31', 'This is room 36 with question event');
INSERT INTO room (room_id, room_phase, name, description) VALUES (37, 3, 'boss', 'This is boss room');

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
INSERT INTO room_event (room_id, event_type, event_id) VALUES (10, 0, '10');
-- Room events Phase 2
INSERT INTO room_event (room_id, event_type, event_id) VALUES (11, 0, '11');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (12, 0, '12');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (13, 0, '13');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (14, 0, '14');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (15, 0, '15');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (16, 1, 'change_score');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (17, 0,'17');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (18, 1, 'hide_event');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (19, 0, '19');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (20, 0, '20');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (21, 1, 'back_to_lobby');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (22, 0, '22');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (23, 1, 'double_score');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (24, 0, '24');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (25, 0, '25');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (26, 1, 'add_steps');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (27, 0, '27');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (28, 1, 'stole_score');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (29, 0, '29');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (30, 0, '30');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (31, 0, '31');
-- Room events Phase 3
INSERT INTO room_event (room_id, event_type, event_id) VALUES (32, 0, '32');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (33, 0, '33');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (34, 0, '34');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (35, 0, '35');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (36, 0, '36');
INSERT INTO room_event (room_id, event_type, event_id) VALUES (37, 1, 'boss');

-- Room link
-- Phase 1 links
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (1, 2, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (1, 3, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (1, 4, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (1, 5, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (1, 6, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (1, 7, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (1, 8, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (1, 9, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (1, 10, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (2, 1, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (2, 3, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (2, 4, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (2, 5, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (2, 6, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (2, 7, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (2, 8, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (2, 9, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (2, 10, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (3, 1, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (3, 2, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (3, 4, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (3, 5, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (3, 6, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (3, 7, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (3, 8, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (3, 9, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (3, 10, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (4, 1, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (4, 2, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (4, 3, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (4, 5, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (4, 6, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (4, 7, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (4, 8, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (4, 9, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (4, 10, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (5, 1, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (5, 2, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (5, 3, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (5, 4, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (5, 6, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (5, 7, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (5, 8, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (5, 9, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (5, 10, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (6, 1, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (6, 2, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (6, 3, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (6, 4, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (6, 5, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (6, 7, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (6, 8, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (6, 9, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (6, 10, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (7, 1, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (7, 2, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (7, 3, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (7, 4, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (7, 5, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (7, 6, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (7, 8, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (7, 9, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (7, 10, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (8, 1, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (8, 2, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (8, 3, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (8, 4, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (8, 5, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (8, 6, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (8, 7, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (8, 9, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (8, 10, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (9, 1, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (9, 2, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (9, 3, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (9, 4, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (9, 5, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (9, 6, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (9, 7, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (9, 8, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (9, 10, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (10, 1, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (10, 2, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (10, 3, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (10, 4, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (10, 5, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (10, 6, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (10, 7, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (10, 8, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (10, 9, 10);
-- Phase 2 links
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (11, 12, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (11, 13, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (11, 14, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (12, 11, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (12, 15, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (13, 11, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (13, 17, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (14, 11, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (14, 15, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (14, 17, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (14, 19, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (15, 12, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (15, 14, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (15, 16, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (15, 20, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (16, 15, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (16, 21, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (17, 13, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (17, 14, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (17, 18, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (17, 22, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (18, 17, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (18, 23, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (19, 14, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (19, 20, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (19, 22, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (19, 24, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (20, 15, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (20, 19, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (20, 21, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (20, 25, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (21, 16, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (21, 20, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (21, 26, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (22, 17, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (22, 19, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (22, 23, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (22, 27, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (23, 18, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (23, 22, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (23, 28, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (24, 19, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (24, 25, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (24, 27, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (24, 29, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (25, 20, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (25, 24, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (25, 26, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (25, 30, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (26, 21, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (26, 25, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (27, 22, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (27, 24, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (27, 28, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (27, 31, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (28, 23, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (28, 27, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (29, 24, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (29, 30, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (29, 31, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (30, 25, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (30, 29, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (31, 27, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (31, 29, 10);
-- Phase 3 links
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (32, 33, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (32, 34, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (33, 32, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (33, 34, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (33, 36, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (34, 32, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (34, 33, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (34, 35, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (35, 34, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (35, 36, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (35, 37, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (36, 33, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (36, 35, 10);
INSERT INTO room_link (room_id_from, room_id_to, distance) VALUES (36, 37, 10);

-- Players
INSERT INTO player (esn, location, line_id, status, score) values ('1', 0, '1', 0, 0);
INSERT INTO player (esn, location, line_id, status, score) values ('2', 0, '2', 0, 0);
INSERT INTO player (esn, location, line_id, status, score) values ('3', 0, '3', 0, 0);
INSERT INTO player (esn, location, line_id, status, score) values ('4', 0, '4', 0, 0);
INSERT INTO player (esn, location, line_id, status, score) values ('5', 0, '5', 0, 0);
INSERT INTO player (esn, location, line_id, status, score) values ('6', 0, '6', 0, 0);
INSERT INTO player (esn, location, line_id, status, score) values ('7', 0, '7', 0, 0);
INSERT INTO player (esn, location, line_id, status, score) values ('8', 0, '8', 0, 0);
-- Questions
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('1', 'Who are you?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('2', 'Where do you live?', 2);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('3', 'Where are you from?', 3);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('4', 'How old are you?', 4);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('5', 'How tall are you?', 4);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('6', 'How heavy are you?', 4);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('7', 'Are you happy?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('8', 'I want to play a game...Live or die, make your choice?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('9', 'Do you speak Chinese?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('10', 'Do you speak Chinese?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('11', 'Do you speak Chinese?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('12', 'Do you speak Chinese?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('13', 'Do you speak Chinese?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('14', 'Do you speak Chinese?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('15', 'Do you speak Chinese?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('17', 'Do you speak Chinese?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('19', 'Do you speak Chinese?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('20', 'Do you speak Chinese?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('22', 'Do you speak Chinese?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('24', 'Do you speak Chinese?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('25', 'Do you speak Chinese?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('27', 'Do you speak Chinese?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('29', 'Do you speak Chinese?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('30', 'Do you speak Chinese?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('31', 'Do you speak Chinese?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('32', 'Do you speak Chinese?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('33', 'Do you speak Chinese?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('34', 'Do you speak Chinese?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('35', 'Do you speak Chinese?', 1);
INSERT INTO questions (question_id, question_text, answer_id) VALUES ('36', 'Do you speak Chinese?', 1);


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
INSERT INTO options (question_id, options_id, options_text) VALUES ('10', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('10', 2, 'No');
INSERT INTO options (question_id, options_id, options_text) VALUES ('11', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('11', 2, 'No');
INSERT INTO options (question_id, options_id, options_text) VALUES ('12', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('12', 2, 'No');
INSERT INTO options (question_id, options_id, options_text) VALUES ('13', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('13', 2, 'No');
INSERT INTO options (question_id, options_id, options_text) VALUES ('14', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('14', 2, 'No');
INSERT INTO options (question_id, options_id, options_text) VALUES ('15', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('15', 2, 'No');
INSERT INTO options (question_id, options_id, options_text) VALUES ('17', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('17', 2, 'No');
INSERT INTO options (question_id, options_id, options_text) VALUES ('19', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('19', 2, 'No');
INSERT INTO options (question_id, options_id, options_text) VALUES ('20', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('20', 2, 'No');
INSERT INTO options (question_id, options_id, options_text) VALUES ('22', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('22', 2, 'No');
INSERT INTO options (question_id, options_id, options_text) VALUES ('24', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('24', 2, 'No');
INSERT INTO options (question_id, options_id, options_text) VALUES ('25', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('25', 2, 'No');
INSERT INTO options (question_id, options_id, options_text) VALUES ('27', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('27', 2, 'No');
INSERT INTO options (question_id, options_id, options_text) VALUES ('29', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('29', 2, 'No');
INSERT INTO options (question_id, options_id, options_text) VALUES ('30', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('30', 2, 'No');
INSERT INTO options (question_id, options_id, options_text) VALUES ('31', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('31', 2, 'No');
INSERT INTO options (question_id, options_id, options_text) VALUES ('32', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('32', 2, 'No');
INSERT INTO options (question_id, options_id, options_text) VALUES ('33', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('33', 2, 'No');
INSERT INTO options (question_id, options_id, options_text) VALUES ('34', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('34', 2, 'No');
INSERT INTO options (question_id, options_id, options_text) VALUES ('35', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('35', 2, 'No');
INSERT INTO options (question_id, options_id, options_text) VALUES ('36', 1, 'Yes');
INSERT INTO options (question_id, options_id, options_text) VALUES ('36', 2, 'No');
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
INSERT INTO actions (action_id, action_text_a, action_text_b) VALUES ('stole_score', 'Select a team to steal 3 point from them.', 'One team has stolen your score.');
INSERT INTO actions (action_id, action_text_a, action_text_b) VALUES ('hide_event', 'You hide one of your room event.', '');
INSERT INTO actions (action_id, action_text_a, action_text_b) VALUES ('back_to_lobby', 'You are transferred to the lobby.', '');
INSERT INTO actions (action_id, action_text_a, action_text_b) VALUES ('add_steps', 'You increase your distance to every room in your rest game.', '');
INSERT INTO actions (action_id, action_text_a, action_text_b) VALUES ('double_score', 'You double up your current score', '');
INSERT INTO actions (action_id, action_text_a, action_text_b) VALUES ('boss', 'Try to win the boss, good luck', '');

