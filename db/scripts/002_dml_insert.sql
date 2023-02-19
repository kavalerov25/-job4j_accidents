INSERT INTO type (name) VALUES('Две машины');
INSERT INTO type (name) VALUES('Машина и человек');
INSERT INTO type (name) VALUES('Машина и велосипед');

INSERT INTO rule (name) VALUES('Статья 1');
INSERT INTO rule (name) VALUES('Статья 2');
INSERT INTO rule (name) VALUES('Статья 3');

INSERT INTO accident (name, text, address, type_id) VALUES ('Name 1', 'Description 1', 'Address 1', 1);
INSERT INTO accident (name, text, address, type_id) VALUES ('Name 2', 'Description 2', 'Address 2', 2);
INSERT INTO accident (name, text, address, type_id) VALUES ('Name 3', 'Description 3', 'Address 3', 3);

INSERT INTO accident_rule (accident_id, rule_id) VALUES (1, 1);
INSERT INTO accident_rule (accident_id, rule_id) VALUES (1, 3);
INSERT INTO accident_rule (accident_id, rule_id) VALUES (2, 2);
INSERT INTO accident_rule (accident_id, rule_id) VALUES (2, 3);
INSERT INTO accident_rule (accident_id, rule_id) VALUES (3, 3);