DROP TABLE IF EXISTS `usr_user_type`;
CREATE TABLE `usr_user_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_type` varchar(30) NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_finalize` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_type_status_idx` (`status`),
  CONSTRAINT `fk_user_type_status` FOREIGN KEY (`status`) REFERENCES `cat_status` (`id`)
) COMMENT = 'TABLA QUE DEFINE LOS TIPOS DE USUARIO';

DROP TABLE IF EXISTS `usr_user`;
CREATE TABLE `usr_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `curp` varchar(45) DEFAULT NULL,
  `first_name` varchar(35) NOT NULL,
  `last_name1` varchar(35) NOT NULL,
  `last_name2` varchar(35) DEFAULT NULL,
  `gender` int NOT NULL DEFAULT '1' COMMENT '[1: NO DEFINIDO][2: MASCULINO][3: FEMENIDO]',
  `email` varchar(255) DEFAULT NULL,
  `phone_number1` varchar(12) DEFAULT NULL,
  `phone_number2` varchar(12) DEFAULT NULL,
  `street1` int NOT NULL,
  `street2` int DEFAULT NULL,
  `inside_number` varchar(5) NOT NULL DEFAULT 'S/N',
  `outside_number` varchar(5) DEFAULT 'S/N',
  `water_intake_type` int NOT NULL,
  `user_type` int NOT NULL DEFAULT '1',
  `status` int NOT NULL DEFAULT '1',
  `last_employee_update` int NOT NULL,
  `date_last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_end` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `curp_UNIQUE` (`curp`),
  KEY `fk_users_street1_idx` (`street1`),
  KEY `fk_users_street2_idx` (`street2`),
  KEY `fk_users_water_intake_type_idx` (`water_intake_type`),
  KEY `fk_users_user_type_idx` (`user_type`),
  KEY `fk_users_status_idx` (`status`),
  KEY `fk_users_last_emp_up_idx` (`last_employee_update`),
  CONSTRAINT `fk_user_last_emp_up` FOREIGN KEY (`last_employee_update`) REFERENCES `emp_employee` (`id`),
  CONSTRAINT `fk_users_status` FOREIGN KEY (`status`) REFERENCES `cat_status` (`id`),
  CONSTRAINT `fk_users_street1` FOREIGN KEY (`street1`) REFERENCES `cat_street` (`id`),
  CONSTRAINT `fk_users_street2` FOREIGN KEY (`street2`) REFERENCES `cat_street` (`id`),
  CONSTRAINT `fk_users_user_type` FOREIGN KEY (`user_type`) REFERENCES `usr_user_type` (`id`),
  CONSTRAINT `fk_users_water_intake_type` FOREIGN KEY (`water_intake_type`) REFERENCES `wki_water_intake_type` (`id`)
) COMMENT = 'TABLA DE USUARIOS';

DROP TABLE IF EXISTS `usr_user_document`;
CREATE TABLE `usr_user_document` (
  `id` int NOT NULL,
  `user` varchar(45) NOT NULL,
  `document_name` varchar(45) NOT NULL,
  `document_path` varchar(255) DEFAULT NULL,
  `doc_file` blob,
  `status` int NOT NULL DEFAULT '1',
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_end` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) COMMENT = 'TABLA PARA GUARDAR LOS DOCUMENTOS DEL USUARIO';

DROP TABLE IF EXISTS `usr_blocked_user`;
CREATE TABLE `usr_blocked_user` (
  `id` int NOT NULL,
  `user` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` int NOT NULL DEFAULT '1',
  `date_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_end` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_blocked_user_idx` (`user`),
  KEY `fl_blocked_user_idx` (`status`),
  CONSTRAINT `fk_blocked_user` FOREIGN KEY (`user`) REFERENCES `usr_user` (`id`),
  CONSTRAINT `fl_blocked_status` FOREIGN KEY (`status`) REFERENCES `cat_status` (`id`)
) COMMENT = 'TABLA DE USUARIOS BLOQUEADOS';

INSERT INTO usr_user_type(user_type) VALUES ('TITULAR'), ('CONSUMIDOR');