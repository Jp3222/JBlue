USE `jblue_test_copy`;

-- USERS
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (1,'INSERT_TO_USER', 'INSERT', 'USERS');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (2,'UPDATE_TO_USER', 'UPDATE', 'USERS');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (3,'LOGIC_DELETE_TO_USER', 'LOGIC_DELETE', 'USERS');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (4,'DELETE_TO_USER', 'DELETE', 'USERS');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (5,'EXPORT_TO_USER', 'EXPORT', 'USERS');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (6,'IMPORT_TO_USER', 'IMPORT', 'USERS');

-- STREET
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (7,'INSERT_TO_STREET', 'INSERT', 'STREET');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (8,'UPDATE_TO_STREET', 'UPDATE', 'STREET');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (9,'LOGIC_DELETE_TO_STREET', 'LOGIC_DELETE', 'STREET');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (10,'DELETE_TO_STREET', 'DELETE', 'STREET');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (11,'EXPORT_TO_STREET', 'EXPORT', 'STREET');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (12,'IMPORT_TO_STREET', 'IMPORT', 'STREET');

-- TYPE_WATER_INTAKES
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (13,'INSERT_TO_TYPE_WATER_INTAKES', 'INSERT', 'TYPE_WATER_INTAKES');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (14,'UPDATE_TO_TYPE_WATER_INTAKES', 'UPDATE', 'TYPE_WATER_INTAKES');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (15,'LOGIC_DELETE_TO_TYPE_WATER_INTAKES', 'LOGIC_DELETE', 'TYPE_WATER_INTAKES');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (16,'DELETE_TO_TYPE_WATER_INTAKES', 'DELETE', 'TYPE_WATER_INTAKES');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (17,'EXPORT_TO_TYPE_WATER_INTAKES', 'EXPORT', 'TYPE_WATER_INTAKES');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (18,'IMPORT_TO_TYPE_WATER_INTAKES', 'IMPORT', 'TYPE_WATER_INTAKES');

-- EMPLOYEES
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (19, 'INSERT_TO_EMPLOYEES', 'INSERT', 'EMPLOYEES');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (20,'UPDATE_TO_EMPLOYEES', 'UPDATE', 'EMPLOYEES');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (21,'LOGIC_DELETE_TO_EMPLOYEES', 'LOGIC_DELETE', 'EMPLOYEES');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (22,'DELETE_TO_EMPLOYEES', 'DELETE', 'EMPLOYEES');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (23,'EXPORT_TO_EMPLOYEES', 'EXPORT', 'EMPLOYEES');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (24,'IMPORT_TO_EMPLOYEES', 'IMPORT', 'EMPLOYEES');

-- SERVICE_PAYMENTS
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (25,'INSERT_TO_SERVICE_PAYMENTS', 'INSERT', 'SERVICE_PAYMENTS');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (26,'UPDATE_TO_SERVICE_PAYMENTS', 'UPDATE', 'SERVICE_PAYMENTS');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (27,'LOGIC_DELETE_TO_SERVICE_PAYMENTS', 'LOGIC_DELETE', 'SERVICE_PAYMENTS');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (28,'DELETE_TO_SERVICE_PAYMENTS', 'DELETE', 'SERVICE_PAYMENTS');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (29,'EXPORT_TO_SERVICE_PAYMENTS', 'EXPORT', 'SERVICE_PAYMENTS');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (30,'IMPORT_TO_SERVICE_PAYMENTS', 'IMPORT', 'SERVICE_PAYMENTS');

-- SURCHARGE_PAYMENTS
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (31,'INSERT_TO_SURCHARGE_PAYMENTS', 'INSERT', 'SURCHARGE_PAYMENTS');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (32,'UPDATE_TO_SURCHARGE_PAYMENTS', 'UPDATE', 'SURCHARGE_PAYMENTS');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (33,'LOGIC_DELETE_TO_SURCHARGE_PAYMENTS', 'LOGIC_DELETE', 'SURCHARGE_PAYMENTS');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (34,'DELETE_TO_SURCHARGE_PAYMENTS', 'DELETE', 'SURCHARGE_PAYMENTS');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (35,'EXPORT_TO_SURCHARGE_PAYMENTS', 'EXPORT', 'SURCHARGE_PAYMENTS');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (36,'IMPORT_TO_SURCHARGE_PAYMENTS', 'IMPORT', 'SURCHARGE_PAYMENTS');

-- OTHERS_PAYMENTS
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (37,'INSERT_TO_OTHERS_PAYMENTS', 'INSERT', 'OTHERS_PAYMENTS');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (38,'UPDATE_TO_OTHERS_PAYMENTS', 'UPDATE', 'OTHERS_PAYMENTS');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (39,'LOGIC_DELETE_TO_OTHERS_PAYMENTS', 'LOGIC_DELETE', 'OTHERS_PAYMENTS');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (40,'DELETE_TO_OTHERS_PAYMENTS', 'DELETE', 'OTHERS_PAYMENTS');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (41,'EXPORT_TO_OTHERS_PAYMENTS', 'EXPORT', 'OTHERS_PAYMENTS');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (42,'IMPORT_TO_OTHERS_PAYMENTS', 'IMPORT', 'OTHERS_PAYMENTS');

-- LOGIN
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (43,'INSERT_LOGIN', 'INSERT', 'HISTORY');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (44,'EXIT_LOGIN', 'INSERT', 'HISTORY');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (45,'EXPORT_TO_LOGIN', 'EXPORT', 'HISTORY');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (46,'IMPORT_TO_LOGIN', 'IMPORT', 'HISTORY');
INSERT INTO `history_type_mov` (`id`, `name_mov`, `type_mov`, `affected_table`) VALUES (47,'DELETE_TO_HISTORY', 'DELETE', 'HISTORY');

SELECT * FROM jblue_test.history_type_mov;