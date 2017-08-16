INSERT INTO game (status) VALUES (-1);

INSERT INTO room (room_id, room_phase, name, description) values (0, 0, 'lobby', 'lobby');

INSERT INTO room (room_id, room_phase, name, description) values (1, 1, 'room 1', 'This is room 1');

INSERT INTO room (room_id, room_phase, name, description) values (2, 1, 'room 2', 'This is room 2');

INSERT INTO room (room_id, room_phase, name, description) values (3, 2, 'room 3', 'This is room 3');

INSERT INTO room_link (room_id_from, room_id_to, distance) values (0, 1, 100);
INSERT INTO room_link (room_id_from, room_id_to, distance) values (1, 0, 100);

INSERT INTO room_link (room_id_from, room_id_to, distance) values (0, 2, 100);
INSERT INTO room_link (room_id_from, room_id_to, distance) values (2, 0, 100);

INSERT INTO room_link (room_id_from, room_id_to, distance) values (0, 3, 100);
INSERT INTO room_link (room_id_from, room_id_to, distance) values (3, 0, 100);

INSERT INTO room_link (room_id_from, room_id_to, distance) values (1, 2, 100);
INSERT INTO room_link (room_id_from, room_id_to, distance) values (2, 1, 100);

