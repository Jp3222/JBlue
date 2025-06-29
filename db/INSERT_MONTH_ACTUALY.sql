CREATE DEFINER=`jp`@`%` PROCEDURE `insert_month_actualy`(IN p_employee INT, IN p_user INT, IN p_price INT)
BEGIN
    DECLARE v_current_month INT;
    DECLARE v_current_year INT;
    DECLARE v_month_counter INT;
    DECLARE v_month_name VARCHAR(3);
    DECLARE v_payment_exists INT; -- Variable para verificar si el pago ya existe

    -- Obtener el número del mes y el año actuales
    SET v_current_month = MONTH(CURRENT_TIMESTAMP());
    SET v_current_year = YEAR(CURRENT_TIMESTAMP());

    -- Inicializar el contador del bucle en 1 (Enero)
    SET v_month_counter = 1;

    -- Bucle desde Enero hasta el mes actual
    WHILE v_month_counter <= v_current_month DO
        -- Determinar la abreviatura del mes
        CASE v_month_counter
            WHEN 1 THEN SET v_month_name = 'ENE';
            WHEN 2 THEN SET v_month_name = 'FEB';
            WHEN 3 THEN SET v_month_name = 'MAR';
            WHEN 4 THEN SET v_month_name = 'ABR';
            WHEN 5 THEN SET v_month_name = 'MAY';
            WHEN 6 THEN SET v_month_name = 'JUN';
            WHEN 7 THEN SET v_month_name = 'JUL';
            WHEN 8 THEN SET v_month_name = 'AGO';
            WHEN 9 THEN SET v_month_name = 'SEP';
            WHEN 10 THEN SET v_month_name = 'OCT';
            WHEN 11 THEN SET v_month_name = 'NOV';
            WHEN 12 THEN SET v_month_name = 'DIC';
        END CASE;

        -- Verificar si ya existe un pago para este usuario, este mes y este año
        -- ASUNCION IMPORTANTE: La tabla 'service_payments' tiene una columna 'date_register'
        -- o una columna similar de tipo DATE/DATETIME que registra cuándo se hizo el pago,
        -- y de la cual podemos extraer el AÑO.
        SELECT COUNT(*)
        INTO v_payment_exists
        FROM service_payments
        WHERE user = p_user
          AND month = v_month_name
          AND YEAR(date_register) = v_current_year;

        -- Si el pago NO existe para este mes y año, entonces lo insertamos
        IF v_payment_exists = 0 THEN
            INSERT INTO service_payments(employee, user, price, month)
            VALUES(p_employee, p_user, p_price, v_month_name);
            -- Si 'service_payments' también tuviera una columna 'date_register'
            -- y quisieras que registrara la fecha de HOY, la inserción sería:
            -- INSERT INTO service_payments(employee, user, price, month, date_register)
            -- VALUES(p_employee, p_user, p_price, v_month_name, CURRENT_DATE());
        END IF;

        -- Incrementar el contador del mes
        SET v_month_counter = v_month_counter + 1;
    END WHILE;
END