USE JBlue;

DROP TABLE IF EXISTS `hys_history`;
CREATE TABLE `hys_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `affected_environment_id` int NOT NULL,
  `affected_environment_desc` varchar(30) NOT NULL,
  `affected_table` int NOT NULL,
  `affected_table_description` varchar(60) NOT NULL,
  `id_mov` int NOT NULL COMMENT 'ID del movimiento en la bitacora indicada',
  `date_register` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_history_table_idx` (`affected_table`),
  CONSTRAINT `fk_history_table` FOREIGN KEY (`affected_table`) REFERENCES `cat_tables_db` (`id`)
) COMMENT = 'HISTORIAL GENERAL DE BITACORAS';

DROP TABLE IF EXISTS `hys_program_history`;
CREATE TABLE `hys_program_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee` int NOT NULL,
  `db_user` varchar(50) NOT NULL,
  `affected_table` int NOT NULL,
  `type_mov` int NOT NULL,
  `description` text,
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_program_history_affected_table_idx` (`affected_table`),
  KEY `fk_program_history_type_mov_idx` (`type_mov`),
  CONSTRAINT `fk_program_history_affected_table` FOREIGN KEY (`affected_table`) REFERENCES `cat_tables_db` (`id`),
  CONSTRAINT `fk_program_history_type_mov` FOREIGN KEY (`type_mov`) REFERENCES `cat_history_type_mov` (`id`)
) COMMENT = 'HISTORIAL DE MOVIMIENTOS DEL PROGRAMA';

DROP TABLE IF EXISTS `logbook_to_employee`;
CREATE TABLE `logbook_to_employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `enty_id` int DEFAULT NULL,
  `type_mov` int NOT NULL,
  `old_values` json DEFAULT NULL,
  `new_values` json DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `db_user` varchar(20) NOT NULL,
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_lb_to_emp_type_mov_idx` (`type_mov`),
  CONSTRAINT `fk_lb_to_emp_type_mov` FOREIGN KEY (`type_mov`) REFERENCES `cat_history_type_mov` (`id`)
) COMMENT = 'BITACORA DE MOVIMIENTOS DE LA TABLA: EMPLEADO';

DROP TABLE IF EXISTS `logbook_to_parametes`;
CREATE TABLE `logbook_to_parametes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `enty_id` int DEFAULT NULL,
  `type_mov` int NOT NULL,
  `old_values` json DEFAULT NULL,
  `new_values` json DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `db_user` varchar(20) NOT NULL,
  `date_register` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_lb_to_parameters_type_mov_idx` (`type_mov`),
  CONSTRAINT `fk_lb_to_parameters_type_mov` FOREIGN KEY (`type_mov`) REFERENCES `cat_history_type_mov` (`id`)
)COMMENT = 'BITACORA DE MOVIMIENTOS DE LA TABLA: PARAMETROS';

DROP TABLE IF EXISTS `logbook_to_payment`;
CREATE TABLE `logbook_to_payment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `enty_id` int DEFAULT NULL,
  `type_mov` int NOT NULL,
  `old_values` json DEFAULT NULL,
  `new_values` json DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `db_user` varchar(20) NOT NULL,
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_lb_payments_type_mov_idx` (`type_mov`),
  CONSTRAINT `fk_lb_payments_type_mov` FOREIGN KEY (`type_mov`) REFERENCES `cat_history_type_mov` (`id`)
)COMMENT = 'BITACORA DE MOVIMIENTOS DE LA TABLA: PAGOS';

DROP TABLE IF EXISTS `logbook_to_user`;
CREATE TABLE `logbook_to_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `enty_id` int DEFAULT NULL,
  `type_mov` int NOT NULL,
  `old_values` json DEFAULT NULL,
  `new_values` json DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `db_user` varchar(20) NOT NULL,
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_lb_users_type_mov_idx` (`type_mov`),
  CONSTRAINT `fk_lb_users_type_mov` FOREIGN KEY (`type_mov`) REFERENCES `cat_history_type_mov` (`id`)
)COMMENT = 'BITACORA DE MOVIMIENTOS DE LA TABLA: USUARIO';

DROP TABLE IF EXISTS `logbook_to_water_intake`;
CREATE TABLE `logbook_to_water_intake` (
  `id` int NOT NULL AUTO_INCREMENT,
  `enty_id` int DEFAULT NULL,
  `type_mov` int NOT NULL,
  `old_values` json DEFAULT NULL,
  `new_values` json DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `db_user` varchar(20) NOT NULL,
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_lb_wi_type_mov_idx` (`type_mov`),
  CONSTRAINT `fk_lb_wi_type_mov` FOREIGN KEY (`type_mov`) REFERENCES `cat_history_type_mov` (`id`)
)COMMENT = 'BITACORA DE MOVIMIENTOS DE LA TABLA: TOMAS DE AGUA';

DROP TABLE IF EXISTS `logbook_to_water_intakes_type`;
CREATE TABLE `logbook_to_water_intakes_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `enty_id` varchar(45) DEFAULT NULL,
  `type_mov` int NOT NULL,
  `old_values` json DEFAULT NULL,
  `new_values` json DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `db_user` varchar(20) NOT NULL,
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_lb_wit_type_mov_idx` (`type_mov`),
  CONSTRAINT `fk_lb_wit_type_mov` FOREIGN KEY (`type_mov`) REFERENCES `cat_history_type_mov` (`id`)
)COMMENT = 'BITACORA DE MOVIMIENTOS DE LA TABLA: TOMAS DE AGUA REGISTRADAS';