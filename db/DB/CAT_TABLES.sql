USE JBlue;

-- 1. Eliminación de Tablas (Orden Inverso a Dependencias)
-- Las tablas dependientes deben eliminarse primero.

DROP TABLE IF EXISTS `wki_water_intake_type`;
DROP TABLE IF EXISTS `cat_user_type_document`;
DROP TABLE IF EXISTS `cat_user_movements`;
DROP TABLE IF EXISTS `cat_history_type_mov`;
DROP TABLE IF EXISTS `cat_payment_method`;
DROP TABLE IF EXISTS `cat_process_type`;
DROP TABLE IF EXISTS `cat_street`;
DROP TABLE IF EXISTS `cat_tables_db`;
DROP TABLE IF EXISTS `cat_user_category_documents`;
DROP TABLE IF EXISTS `cat_status`;
DROP TABLE IF EXISTS `cat_group_prefix`;
---

-- 2. Creación de Tablas

CREATE TABLE `cat_group_prefix` (
  `id` int NOT NULL AUTO_INCREMENT,
  `prefix_type` varchar(3) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `date_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_register` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_end` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`,`prefix_type`),
  UNIQUE KEY `prefix_type_UNIQUE` (`prefix_type`)
);

CREATE TABLE `cat_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `affected_group` varchar(50) NOT NULL,
  `date_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_finalize` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `cat_history_type_mov` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_history_type_mov_status_idx` (`status`),
  CONSTRAINT `fk_history_type_mov_status` FOREIGN KEY (`status`) REFERENCES `cat_status` (`id`)
);

CREATE TABLE `cat_payment_method` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(60) NOT NULL COMMENT 'METODO DE PAGO',
  `code` varchar(15) NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'FECHA DE REGISTRO DEL METODO DE PAGO',
  `date_end` timestamp NULL DEFAULT NULL COMMENT 'SE REGISTRA EN CASO DE QUE SE DESHABILITEN O YA NO SE PERMITA EL METODO DE PAGO',
  PRIMARY KEY (`id`),
  UNIQUE KEY `description_UNIQUE` (`description`),
  UNIQUE KEY `code_UNIQUE` (`code`)
);

CREATE TABLE `cat_process_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` int NOT NULL DEFAULT '1',
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_end` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pro_ty_status_idx` (`status`),
  CONSTRAINT `fk_pro_ty_status` FOREIGN KEY (`status`) REFERENCES `cat_status` (`id`)
);

CREATE TABLE `cat_street` (
  `id` int NOT NULL AUTO_INCREMENT,
  `street_name` varchar(100) NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  `date_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_end` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_street_status_idx` (`status`),
  CONSTRAINT `fk_street_status` FOREIGN KEY (`status`) REFERENCES `cat_status` (`id`)
);

CREATE TABLE `cat_tables_db` (
  `id` int NOT NULL AUTO_INCREMENT,
  `group_prefix` varchar(3) NOT NULL,
  `table_name` varchar(50) NOT NULL,
  `description` text,
  `user_who_creates` int NOT NULL,
  `user_who_creates_name` varchar(90) NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  `date_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'fecha en la que se hacen actualizaciones',
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'fecha en que se crean las tablas',
  `date_end` timestamp NULL DEFAULT NULL COMMENT 'En caso de querer dejar de usar una tabla pero mantener los registros',
  PRIMARY KEY (`id`),
  UNIQUE KEY `table_name_UNIQUE` (`table_name`),
  KEY `fk_table_db_status_idx` (`status`),
  KEY `fk_table_db_prefix_idx` (`group_prefix`),
  CONSTRAINT `fk_table_db_prefix` FOREIGN KEY (`group_prefix`) REFERENCES `cat_group_prefix` (`prefix_type`),
  CONSTRAINT `fk_table_db_status` FOREIGN KEY (`status`) REFERENCES `cat_status` (`id`)
) COMMENT='Catalogo de tablas del sistema, la tabla esta pensada para apuntar como tabla afectada';

CREATE TABLE `cat_user_category_documents` (
  `id` int NOT NULL,
  `type_document` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

CREATE TABLE `cat_user_movements` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_mov` varchar(100) NOT NULL,
  `group` varchar(100) NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  `date_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_user_mov_status_idx` (`status`),
  CONSTRAINT `fk_user_mov_status` FOREIGN KEY (`status`) REFERENCES `cat_status` (`id`)
);

CREATE TABLE `cat_user_type_document` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_document` varchar(255) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `type_document` int NOT NULL,
  `date_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_end` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type_document_UNIQUE` (`user_document`),
  KEY `fk_user_document_type_idx` (`type_document`),
  CONSTRAINT `fk_user_document_type` FOREIGN KEY (`type_document`) REFERENCES `cat_user_category_documents` (`id`)
);

CREATE TABLE `wki_water_intake_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) NOT NULL,
  `current_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `previous_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `surcharge` decimal(10,2) NOT NULL DEFAULT '0.00',
  `status` int NOT NULL DEFAULT '1',
  `date_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_end` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_wi_type_status_idx` (`status`),
  CONSTRAINT `fk_wi_type_status` FOREIGN KEY (`status`) REFERENCES `cat_status` (`id`)
);

CREATE TABLE `cat_payment_type` (
  `id` int NOT NULL,
  `description` varchar(45) NOT NULL,
  `description_long` varchar(255) DEFAULT NULL,
  `status` int DEFAULT '1',
  `date_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_end` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_payment_type_status_idx` (`status`),
  CONSTRAINT `fk_payment_type_status` FOREIGN KEY (`status`) REFERENCES `cat_status` (`id`)
) COMMENT='MOTIVOS DE PAGO';

--
INSERT INTO cat_status(description, affected_group) 
VALUES ('ACTIVO', 'ALL'), ('INACTIVO', 'ALL'), ('ELIMINADO', 'ALL'), ('FINALIZADO', 'ALL'), ('CANCELADO', 'ALL'), ('NO ACCESIBLE PARA EL USUARIO', 'ALL'),
('PAGADO', 'PAYMENTS'),('PENDIENTE', 'PAYMENTS'),('EN PROCESO', 'PAYMENTS'),
('INICIADO', 'PROCEDURES'),('VALIDADO', 'PROCEDURES'),('PAGADO', 'PROCEDURES'),('FINALIZADO', 'PROCEDURES'),('CADUCADO', 'PROCEDURES'),
('HABILITADO', 'EMPLOYEES'), ('DESHABILITADO', 'EMPLOYEES'), ('ACTIVO TEMPORALMENTE', 'EMPLOYEES'),
('REGISTRADO', 'USER'), ('RECHAZADO', 'USER'),('EN PROCESO', 'USER'),
('REGISTRADA', 'WATER_INTAKE'), ('RECONECTADA', 'WATER_INTAKE'),('DESCONECTADA', 'WATER_INTAKE');

INSERT INTO cat_group_prefix(id, prefix_type, description) VALUES
('1', 'cat', 'PREFIJO DE \'CATALOGE\' O CATALGOS'),
('2', 'dev', 'PREFIJO DE \'DEVELOP\' O DESARROLLO'),
('3', 'emp', 'PREFIJO DE \'EMPLOYEES\' O EMPLEADOS'),
('4', 'hys', 'PREFIJO DE \'HISTORY\' O HISTORIAL'),
('5', 'lbk', 'PREFIJO DE \'LOGBOOK\' O BITACORA'),
('6', 'pro', 'PREFIJO DE \'PROCEDURE\' O PROCEDIMIENTO'),
('7', 'pym', 'PREFIJO DE \'PAYMENTS\' O PAGOS'),
('8', 'wki', 'PREFIJO DE \'WATER INTAKE\' O TOMAS DE AGUA'),
('9', 'usr', 'PREFIJO DE \'USERS\' O USUARIOS');

INSERT INTO cat_history_type_mov(id, description, status) VALUES
('1', 'INSERT', '1'),
('2', 'UPDATE', '1'),
('3', 'DELETE', '1'),
('4', 'SELECT', '1'),
('5', 'EXPORT', '1'),
('6', 'IMPORT', '1'),
('7', 'LOGIN', '1'),
('8', 'LOGOUT', '1'),
('9', 'LOGIC_DELETE', '1'),
('10', 'PASSWORD_CHANGE', '1'),
('11', 'USER_CHANGE', '1'),
('12', 'PRINT', '1');

INSERT INTO cat_tables_db(id, group_prefix, table_name, description, user_who_creates, user_who_creates_name, status) 
VALUES ('1', 'cat', 'cat_history_type_mov', 'CATALOGO DE TIPOS DE MOVIMIENTOS ESTANDAR(PARA HISTORIAL Y BITACORAS)', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('2', 'cat', 'cat_status', 'CATALOGO DE ESTADOS GENERALES PARA EL SISTEMA', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('3', 'cat', 'cat_street', 'CATALOGO DE CALLES', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('4', 'cat', 'cat_tables_db', 'CATALOGO DE TABALAS E INFORMACION(PARA USO PERSONAL)', '1', 'JUAN PABLO CAMPOS CASASANERO', '6'),
('5', 'cat', 'cat_user_movements', 'CATALGO DE MOVIMIENTOS DE USUARIO', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('6', 'dev', 'dev_parameters', 'CATALOGO DE PARAMETROS PARA EL SISTEMA', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('7', 'emp', 'emp_employee_types', 'CATALGO DE TIMPO DE EMPLEADOS', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('8', 'emp', 'emp_employees', 'TABLA PARA GUARDAR LA INFORMACION DE LOS EMPLEADOS', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('9', 'hys', 'hys_history', 'HISTORIAL GENERAL DEL SISTEMA, GUARDA MOVIMIENTOS EN HISTORIALES Y BITACORAS', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('10', 'hys', 'hys_program_history', 'HISTORIAL DE LOS MOVIMIENTOS DEL PROGRAMA', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('11', 'lbk', 'logbook_to_payments', 'HISTORIAL DE LOS MOVIMIENTOS DE LA TABLA PAGOS', '1', 'JUAN PABLO CAMPOS CASASANERO', '6'),
('12', 'lbk', 'logbook_to_users', 'HISTORIAL DE LOS MOVIMIENTOS DE LA TABLA USUARIOS', '1', 'JUAN PABLO CAMPOS CASASANERO', '6'),
('13', 'lbk', 'logbook_to_water_intakes', 'HISTORIAL DE LOS MOVIMIENTOS DE LA TABLA TOMAS REGISTRADAS', '1', 'JUAN PABLO CAMPOS CASASANERO', '6'),
('14', 'lbk', 'logbook_to_water_intakes_type', 'HISTORIAL DE LOS MOVIMIENTOS DE LA TABLA TIPO DE TOMAS', '1', 'JUAN PABLO CAMPOS CASASANERO', '6'),
('15', 'pro', 'pro_contract_procedure', 'TABLA DE REGISTRO DE CONTRATOS', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('16', 'pym', 'pym_other_payments', 'TABLA DE REGISTRO DE OTROS TIPOS DE PAGOS', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('17', 'pym', 'pym_other_payments_type', 'TABLA DE CONCEPTOPS PARA OTROS PAGOS(SOLO PARA TABLA DE OTHER_PAYMENTS)', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('18', 'pym', 'pym_procedure_payments', 'TABLA DE PAGOS ASOCIADOS A LOS TRAMITES', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('19', 'pym', 'pym_service_payments', 'TABLA DE REGISTRO DE PAGOS POR EL SERVICIO', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('20', 'pym', 'pym_surcharge_payments', 'TABLA DE REGISTRO DE PAGOS POR RECARGOS', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('21', 'usr', 'usr_user_type', 'TABLA DE TIPOS DE USUARIO', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('22', 'usr', 'usr_users', 'TABLA DE USUARIOS', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('23', 'usr', 'usr_users_consumers', 'TABLA DE CONSUMIDORES', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('24', 'wki', 'wki_water_intake_type', 'TABLA DE TIPOS DE TOMAS', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('25', 'wki', 'wki_water_intakes', 'TABLA DE TOMAS DE AGUA POTABLE REGISTRADAS', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('26', 'pro', 'pro_change_owner', 'TABLA DE REGISTROS DE LOS CAMBIOS DE TITULAR', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('27', 'cat', 'cat_process_type', 'CATALOGO DE TIPOS DE PROCESOS', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('28', 'lbk', 'logbook_to_employee', 'HISTORIAL DE MOVIMIENTOS DE LA TABLA EMPLEADOS', '1', 'JUAN PABLO CAMAPOS CASASASANERO', '6'),
('29', 'cat', 'cat_group_prefix', 'CATATOLOGO DE GRUPO DE PREFIJOS', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('30', 'hys', 'hys_administration_history', 'HISTORIAL DE LA ADMINISTRACION ACTUAL', '1', 'JUAN PABLO CAMPOS CASASANERO', '1'),
('31', 'lbk', 'logbook_to_parameters', 'HISTORIAL MOVIMIENTOS DE PARAMETROS', '1', 'JUAN PABLO CAMPOS CASASANERO', '6');









