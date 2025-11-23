USE JBlue;
DROP TABLE IF EXISTS emp_employee_type;
CREATE TABLE emp_employee_type (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_type` varchar(30) NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  `date_register` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_employee_types_status_idx` (`status`),
  CONSTRAINT `fk_employee_types_status` FOREIGN KEY (`status`) REFERENCES `cat_status` (`id`)
) COMMENT='TIPOS DE EMPLEADOS DEFINIDOS';

DROP TABLE IF EXISTS emp_employee;
CREATE TABLE emp_employee (
  `id` int NOT NULL AUTO_INCREMENT,
  `curp` varchar(18) DEFAULT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name1` varchar(30) NOT NULL,
  `last_name2` varchar(30) DEFAULT NULL,
  `gender` int NOT NULL DEFAULT '1',
  `email` varchar(255) DEFAULT NULL,
  `date_birday` date NOT NULL,
  `phone_number1` varchar(45) DEFAULT NULL,
  `phone_number2` varchar(45) DEFAULT NULL,
  `employee_type` int NOT NULL DEFAULT '1',
  `status` int NOT NULL DEFAULT '1',
  `user` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `date_update` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_end` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_employee_type_idx` (`employee_type`),
  KEY `fk_employee_status_idx` (`status`),
  CONSTRAINT `fk_employee_status` FOREIGN KEY (`status`) REFERENCES `cat_status` (`id`),
  CONSTRAINT `fk_employee_type` FOREIGN KEY (`employee_type`) REFERENCES `emp_employee_type` (`id`)
)COMMENT='TABLA EMPLEADOS';

INSERT INTO emp_employee_type (id, employee_type, status) 
VALUES('1', 'ROOT', '6'), ('2', 'ADMINISTRADOR', '1'), ('3', 'PRESIDENTE', '1'), ('4', 'TESORERO', '1'),
('5', 'SECRETARIO', '1'), ('6', 'PASANTE', '1'), ('7', 'DESARROLLADOR', '6'), ('8', 'USUARIO_DE_PRUEBAS', '6');

INSERT INTO emp_employee (first_name, last_name1, last_name2, gender, date_birday, employee_type, status, user, password) 
VALUES('SUPER', 'ROOT', 'USER', '2', '2025-06-17', '3', '1', 'DYjV5v/rZTjlHOhU5GTewg==', 'Gn6/q0zfTijiIiUDD5bnvA==');

