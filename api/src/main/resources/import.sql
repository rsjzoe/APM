-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

INSERT INTO categoryentity (id, name) VALUES (1, 'SI');
INSERT INTO categoryentity (id, name) VALUES (2, 'ODA');

INSERT INTO departemententity (id, name) VALUES (1, 'DSI');
INSERT INTO departemententity (id, name) VALUES (2, 'DMCC');
INSERT INTO departemententity (id, name) VALUES (3, 'OMM');