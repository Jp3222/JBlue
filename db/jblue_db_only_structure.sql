DROP SCHEMA IF EXISTS `jblue_test_copy`;
CREATE SCHEMA  `jblue_test_copy`;

USE `jblue_test_copy`;

DROP TABLE IF EXISTS `employee_types`;
CREATE TABLE `employee_types` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_type` varchar(30) NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  `date_register` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `history_type_mov`;
CREATE TABLE `history_type_mov` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name_mov` varchar(50) NOT NULL,
  `description` text,
  `type_mov` varchar(20) NOT NULL,
  `affected_table` varchar(50) NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `items_status`;
CREATE TABLE `items_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(30) NOT NULL DEFAULT 'ACTIVO',
  `affected_table` varchar(30) NOT NULL DEFAULT 'GENERICO',
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_finalize` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `parameters`;
CREATE TABLE `parameters` (
  `id` int NOT NULL AUTO_INCREMENT,
  `parameter` varchar(45) NOT NULL,
  `description` text,
  `value` varchar(50) NOT NULL DEFAULT 'false',
  `data_type` varchar(30) NOT NULL DEFAULT 'BOOL',
  `status` int NOT NULL DEFAULT '1',
  `date_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`,`parameter`),
  UNIQUE KEY `parameter_UNIQUE` (`parameter`)
);

DROP TABLE IF EXISTS `street`;
CREATE TABLE `street` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `user_type`;
CREATE TABLE `user_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_type` varchar(30) NOT NULL,
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_finalize` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `water_intakes_types`;
CREATE TABLE `water_intakes_types` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(30) NOT NULL,
  `previus_price` double NOT NULL DEFAULT '0',
  `price` double NOT NULL DEFAULT '0',
  `surcharge` double NOT NULL DEFAULT '0',
  `status` int NOT NULL DEFAULT '1',
  `date_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) NOT NULL,
  `last_names` varchar(60) NOT NULL,
  `employee_type` int NOT NULL DEFAULT '1',
  `status` varchar(100) NOT NULL DEFAULT '1',
  `user` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(35) NOT NULL,
  `last_name1` varchar(35) NOT NULL,
  `last_name2` varchar(35) NOT NULL,
  `street` int NOT NULL,
  `house_number` varchar(5) NOT NULL DEFAULT 'S/N',
  `water_intakes` int NOT NULL,
  `user_type` int NOT NULL DEFAULT '1',
  `status` int NOT NULL DEFAULT '1',
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_users_1_idx` (`street`),
  KEY `fk_users_2_idx` (`water_intakes`),
  KEY `user_status_idx` (`status`),
  KEY `fk_user_type_idx` (`user_type`),
  CONSTRAINT `fk_user_street` FOREIGN KEY (`street`) REFERENCES `street` (`id`),
  CONSTRAINT `fk_user_type` FOREIGN KEY (`user_type`) REFERENCES `user_type` (`id`),
  CONSTRAINT `fkuser_water_intake` FOREIGN KEY (`water_intakes`) REFERENCES `water_intakes_types` (`id`),
  CONSTRAINT `user_status` FOREIGN KEY (`status`) REFERENCES `items_status` (`id`)
);

DROP TABLE IF EXISTS `consumers`;
CREATE TABLE `consumers` (
  `titular` int NOT NULL,
  `consumer` int NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  `date_update` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`titular`,`consumer`),
  KEY `fk_consumer_idx` (`consumer`),
  CONSTRAINT `fk_consumer` FOREIGN KEY (`consumer`) REFERENCES `users` (`id`)
) COMMENT='Evitar que el id del titular sea diferente del consumidor';

DROP TABLE IF EXISTS `history`;
CREATE TABLE `history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee` int NOT NULL,
  `db_user` varchar(50) DEFAULT NULL,
  `type` int NOT NULL,
  `description` text,
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_history_employee_idx` (`employee`),
  KEY `fk_type_mov_idx` (`type`),
  CONSTRAINT `fk_history_employee` FOREIGN KEY (`employee`) REFERENCES `employees` (`id`),
  CONSTRAINT `fk_type_mov` FOREIGN KEY (`type`) REFERENCES `history_type_mov` (`id`)
);

DROP TABLE IF EXISTS `service_payments`;
CREATE TABLE `service_payments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee` int NOT NULL,
  `user` int NOT NULL,
  `price` float NOT NULL,
  `month_name` varchar(3) NOT NULL,
  `month` int NOT NULL DEFAULT (month(curdate())),
  `status` int NOT NULL DEFAULT '1',
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_payments_user_idx` (`employee`),
  KEY `fk_payments_user_idx1` (`user`),
  CONSTRAINT `fk_payments_employee` FOREIGN KEY (`employee`) REFERENCES `employees` (`id`),
  CONSTRAINT `fk_payments_user` FOREIGN KEY (`user`) REFERENCES `users` (`id`)
);

DROP TABLE IF EXISTS `surcharge_payments`;
CREATE TABLE `surcharge_payments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee` int NOT NULL,
  `user` int NOT NULL,
  `price` double NOT NULL,
  `month_name` varchar(3) NOT NULL,
  `month` int NOT NULL DEFAULT (month(curdate())),
  `status` int NOT NULL DEFAULT '0',
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_surcharge_paymentst_employee` (`employee`),
  KEY `fk_surcharge_payments_user` (`user`),
  CONSTRAINT `fk_surcharge_payments_employee` FOREIGN KEY (`employee`) REFERENCES `employees` (`id`),
  CONSTRAINT `fk_surcharge_payments_user` FOREIGN KEY (`user`) REFERENCES `users` (`id`)
);













