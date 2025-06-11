CREATE VIEW `new_view` AS 
SELECT
	CONCAT(a.first_name, " ", a.last_name1, " ", a.last_name2) AS 'USUARIO',
	COUNT(b.month)
FROM 
	users a
INNER JOIN 
	service_payments b 
	ON a.`id` = b.`user`
WHERE 
	YEAR(a.date_register) = YEAR(NOW())
GROUP BY USUARIO;