-- EMPLOYEE
DROP TRIGGER IF EXISTS `jblue_test`.`users_delete`;
DELIMITER $$
USE `jblue_test`$$
CREATE DEFINER = CURRENT_USER TRIGGER `jblue_test`.`users_delete` AFTER DELETE ON `users` FOR EACH ROW
BEGIN
	INSERT INTO history(employee, type, description) VALUES(1, 4, 'BORRADO POR EL USUARIO MAESTRO');
END$$
DELIMITER ;

-- EMPLOYEES
DROP TRIGGER IF EXISTS `jblue_test`.`employees_delete`;
DELIMITER $$
USE `jblue_test`$$
CREATE DEFINER = CURRENT_USER TRIGGER `jblue_test`.`employees_delete` AFTER DELETE ON `employees` FOR EACH ROW
BEGIN
	INSERT INTO history(employee, type, description) VALUES(1, 22, 'BORRADO POR EL USUARIO MAESTRO');
END$$
DELIMITER ;

-- SERVICE_PAYMET
DROP TRIGGER IF EXISTS `jblue_test`.`service_payment_delete`;
DELIMITER $$
USE `jblue_test`$$
CREATE DEFINER = CURRENT_USER TRIGGER `jblue_test`.`service_payment_delete` AFTER DELETE ON `service_payments` FOR EACH ROW
BEGIN
	INSERT INTO history(employee, type, description) VALUES(1, 28, 'BORRADO POR EL USUARIO MAESTRO');
END$$
DELIMITER ;

-- SURCHARGE_PAYMET
DROP TRIGGER IF EXISTS `jblue_test`.`surcharge_payment_delete`;
DELIMITER $$
USE `jblue_test`$$
CREATE DEFINER = CURRENT_USER TRIGGER `jblue_test`.`surcharge_payment_delete` AFTER DELETE ON `surcharge_payments` FOR EACH ROW
BEGIN
	INSERT INTO history(employee, type, description) VALUES(1, 34, 'BORRADO POR EL USUARIO MAESTRO');
END$$
DELIMITER ;


