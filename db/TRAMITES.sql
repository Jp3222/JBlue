USE jblue_test;
SELECT 
	conpro.id, 
	CONCAT(em1.first_name, ' ', em1.last_names) AS 'employee_start',
    conpro.date_start, 
	CONCAT(em2.first_name, ' ', em2.last_names) AS 'employee_valid',
	conpro.date_valid, 
	CONCAT(em3.first_name, ' ', em3.last_names) AS 'employee_end',
	conpro.date_end,
    conpro.president,
    CONCAT(us.first_name, ' ', us.last_name1, ' ', us.last_name2) AS 'user',
    its.description AS 'description',
    conpro.date_register
FROM contract_procedure conpro    
INNER JOIN employees em1 ON em1.id = conpro.employee_start
INNER JOIN employees em2 ON em2.id = conpro.employee_start
INNER JOIN employees em3 ON em3.id = conpro.employee_start
INNER JOIN users us ON us.id = conpro.user
INNER JOIN items_status its ON its.id = conpro.status
;