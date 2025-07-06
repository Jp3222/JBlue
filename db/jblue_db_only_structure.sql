-- CREATE SCHEMA `jblue_test`; -- CREA LA PASE DE DATOS SI ES NECESARIO

USE jblue_test_copy;

CREATE TABLE `street` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
);

CREATE TABLE `water_intakes_types` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(30) NOT NULL,
  `previus_price` float NOT NULL DEFAULT '0',
  `price` float NOT NULL DEFAULT '0',
  `surcharge` float NOT NULL DEFAULT '0',
  `status` int NOT NULL DEFAULT '1',
  `date_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

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
  CONSTRAINT `users_street` FOREIGN KEY (`street`) REFERENCES `street` (`id`),
  CONSTRAINT `users_water_intake` FOREIGN KEY (`water_intakes`) REFERENCES `water_intakes_types` (`id`)
);

CREATE TABLE `consumers` (
  `titular` int NOT NULL,
  `consumer` int NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  `date_update` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`titular`,`consumer`),
  KEY `fk_consumer_idx` (`consumer`),
  CONSTRAINT `fk_consumer` FOREIGN KEY (`consumer`) REFERENCES `users` (`id`)
);

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

CREATE TABLE `surcharge_payments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee` int NOT NULL,
  `user` int NOT NULL,
  `price` float NOT NULL,
  `month` varchar(3) NOT NULL,
  `status` int NOT NULL DEFAULT '0',
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_surcharge_payments_employee_idx` (`employee`),
  KEY `fk_surcharge_payments_user_idx` (`user`),
  CONSTRAINT `fk_surcharge_payments_employee` FOREIGN KEY (`employee`) REFERENCES `employees` (`id`),
  CONSTRAINT `fk_surcharge_payments_user` FOREIGN KEY (`user`) REFERENCES `users` (`id`)
);

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

CREATE TABLE `history_type_mov` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name_mov` varchar(50) NOT NULL,
  `description` text,
  `type_mov` varchar(20) NOT NULL,
  `affected_table` varchar(50) NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE `history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee` int NOT NULL,
  `type` int NOT NULL,
  `description` text,
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_history_employee_idx` (`employee`),
  KEY `fk_type_mov_idx` (`type`),
  CONSTRAINT `fk_history_employee` FOREIGN KEY (`employee`) REFERENCES `employees` (`id`),
  CONSTRAINT `fk_type_mov` FOREIGN KEY (`type`) REFERENCES `history_type_mov` (`id`)
) 




