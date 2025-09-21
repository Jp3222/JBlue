
/*
SET FOREIGN_KEY_CHECKS = 0; -- DESACTIVAMOS EL CHECK DE LAS LLAVES FORANEAS
TRUNCATE TABLE jblue.emp_employees;
TRUNCATE TABLE jblue.hys_history;
TRUNCATE TABLE jblue.hys_program_history;
TRUNCATE TABLE jblue.logbook_to_employees;
TRUNCATE TABLE jblue.logbook_to_payments;
TRUNCATE TABLE jblue.logbook_to_users;
TRUNCATE TABLE jblue.logbook_to_water_intakes;
TRUNCATE TABLE jblue.logbook_to_water_intakes_type;
TRUNCATE TABLE jblue.pro_change_owner;
TRUNCATE TABLE jblue.pro_process;
TRUNCATE TABLE jblue.pym_other_payments;
TRUNCATE TABLE jblue.pym_other_payments_type;
TRUNCATE TABLE jblue.pym_procedure_payments;
TRUNCATE TABLE jblue.pym_service_payments;
TRUNCATE TABLE jblue.pym_surcharge_payments;
TRUNCATE TABLE jblue.usr_users;
TRUNCATE TABLE jblue.usr_users_consumers;
TRUNCATE TABLE jblue.wki_water_intake_type;
TRUNCATE TABLE jblue.wki_water_intakes;
SET FOREIGN_KEY_CHECKS = 1; -- ACTIVAMOS EL CHECK DE LAS LLAVES FORANEAS
*/
SELECT * FROM jblue.logbook_to_payments; -- VERIFICAMPOS EL MOVIMIENTO