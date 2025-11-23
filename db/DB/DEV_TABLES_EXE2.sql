USE JBlue;

DROP TABLE IF EXISTS `dev_parameters`;
CREATE TABLE `dev_parameters` (
  `id` int NOT NULL AUTO_INCREMENT,
  `parameter` varchar(50) NOT NULL,
  `description` text,
  `value` varchar(50) NOT NULL DEFAULT 'false',
  `unit` varchar(20) DEFAULT NULL,
  `data_type` varchar(30) NOT NULL DEFAULT 'BOOL',
  `status` int NOT NULL DEFAULT '1',
  `db_user_create` varchar(20) DEFAULT NULL,
  `db_user_update` varchar(20) DEFAULT NULL,
  `date_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`,`parameter`),
  UNIQUE KEY `parameter_UNIQUE` (`parameter`),
  KEY `fk_parameters_status_idx` (`status`),
  CONSTRAINT `fk_parameters_status` FOREIGN KEY (`status`) REFERENCES `cat_status` (`id`)
) COMMENT='TABLA DE PARAMETROS PENSADOS PARA EL SISTEMA';

INSERT INTO dev_parameters
(id, parameter, description, value, unit, data_type, status, db_user_create)
VALUES
('1', 'HORA_DE_APERTURA', 'HORA DE APERTURA DEL PROGRAMA', '19:30:00', 'HOUR', 'TIME', '1', 'root@%'),
('2', 'HORA_DE_CIERRE', 'HORA DE CIERRE DEL PROGRAMA', '22:00:00', 'HOUR', 'TIME', '1', 'root@%'),
('3', 'VALIDAR_HORA_DE_ENTRADA', 'VALIDAR LA HORA DE ENTRADA Y SALIDA', 'false', 'BOOLEAN', 'BOOL', '1', 'root@%'),
('4', 'ULTIMO_DIA_DE_PAGO', 'ULTIMO DIA PARA PAGAR UNA MENSUALIDAD', '15', 'DAY_OF_MONTH', 'INT', '1', 'root@%'),
('5', 'RECARGO_AUTOMATICO', 'PARAMETRO PARA INDICAR UN RECARGO AUTOMATICO', 'false', 'BOOLEAN', 'BOOL', '1', 'root@%'),
('6', 'USUARIO_MAESTRO', 'CREDENCIA DE ACCESO', 'DYjV5v/rZTjlHOhU5GTewg==', 'MASTER_USER', 'TEXT', '1', 'root@%'),
('7', 'CONTRASEÑA_MAESTRA', 'CREDENCIA DE ACCESO', 'Gn6/q0zfTijiIiUDD5bnvA==', 'MASTER_PASSWORD', 'TEXT', '1', 'root@%'),
('8', 'MENSAJES_DEV', 'PERMITIR VER LOS MENSAJES DEL DESARROLLADOR', 'true', 'BOOLEAN', 'BOOL', '1', 'root@%'),
('9', 'MENSAJES_DB', 'PERMITIR VER LOS MENSAJES DE LA BASE DE DATOS', 'true', 'BOOLEAN', 'BOOL', '1', 'root@%'),
('10', 'FUNCIONES_DEV', 'PERMITIR PROBAR FUNCIONES EN DESARROLLO', 'true', 'BOOLEAN', 'BOOL', '1', 'root@%'),
('11', 'FUNCIONES_TEST', 'PERMITIR PROBAR FUNCIONES(VALIDADAS) FUTURAMENTE LANZADAS', 'true', 'BOOLEAN', 'BOOL', '1', 'root@%'),
('12', 'LOGS_DEV', 'PERMITIR VER MENSAJES DEL DESAROLLADOR EN CONSOLA', 'true', 'BOOLEAN', 'BOOL', '1', 'root@%'),
('13', 'LOGS_DB', 'PERMITIR VER LOS MENSAJES DE LA BASE DE DATOS EN CONSOLA', 'true', 'BOOLEAN', 'BOOL', '1', 'root@%'),
('14', 'FECHA_DE_INICIO', 'FECHA DE INICIO DEL PROGRAMA', '2025-01-01', 'DATE', 'DATE', '1', 'root@%'),
('15', 'FECHA_DEL_PROGRAMA', 'FECHA DE REFERENCIA PARA EL SISTEMA', '2025-01-01', 'DATE', 'DATE', '1', 'root@%');