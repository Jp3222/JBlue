-- Agrupa los id de tupos de ,ovimiento segun la tabla afectada
USE jblue_test;
SELECT 
	affected_table,
	GROUP_CONCAT(id, ' ') as MOV
FROM 
	history_type_mov
-- WHERE affected_table = 'USERS'
GROUP BY affected_table;