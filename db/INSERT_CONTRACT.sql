USE jblue_test;
INSERT INTO contract_procedure (
	employee_start, 
    date_start, 
    employee_valid, 
    date_valid, 
    employee_ends, 
    date_end, 
    president, 
    user, 
    water_intake, 
    date_register
) SELECT 
	1, 
	us.date_register,
	1, 
	us.date_register,
	1, 
	us.date_register,
	1, 
	us.id,
    wk.id,
    us.date_register 
FROM 
	water_intakes wk 
INNER JOIN users us
	ON wk.user = us.id
;