USE jblue_test;

SELECT 
	us.id AS 'ID',
    us.first_name AS 'NOMBRE',
    us.last_name1 AS 'A. PATERNO',
    us.last_name2 AS 'A. MATERNO',
    st.name AS 'CALLE',
    house_number AS 'NO. EXT',
    wt.type AS 'TIPO DE TOMA',
    us.user_type AS 'TIPO DE USUARIO',
    it.description AS 'ESTATO',
    us.date_register AS 'FECHA DE REGISTRO'
FROM users us
INNER JOIN street st ON st.id = us.street
INNER JOIN water_intakes_types wt ON wt.id = us.water_intakes
INNER JOIN items_status it ON it.id = us.status;