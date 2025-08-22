USE jblue_test;
INSERT INTO `water_intakes` (
	`procedure_payments`,
    `water_intake_type`,
    `user`,
    `street1`,
    `street2`,
    `location`,
    `description`,
    `status`,
    `date_update`,
    `date_register`
)
SELECT
	u.id,
    u.water_intakes,  -- Corresponde al tipo de toma de agua
    u.id,             -- Corresponde al usuario
    u.street,         -- Corresponde a la calle principal
    NULL,             -- street2, no existe en la tabla users, se establece como NULL
    NULL,             -- location, no existe en la tabla users, se establece como NULL
    CONCAT('Toma de agua del usuario ', u.first_name, ' ', u.last_name1),
    11,
    CURRENT_TIMESTAMP,
    u.date_register
    
FROM
    `users` AS u;