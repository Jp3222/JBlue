CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `jp`@`%` 
    SQL SECURITY DEFINER
VIEW `jblue_test`.`all_data_user` AS
    SELECT 
        `a`.`id` AS `id`,
        CONCAT(`a`.`first_name`,
                '_',
                `a`.`last_name1`,
                '_',
                `a`.`last_name2`) AS `Nombre`,
        `b`.`name` AS `Calle`,
        `a`.`house_number` AS `No. de Casa`,
        `c`.`type` AS `Tipo de Toma`,
        `a`.`status` AS `Estado`,
        `a`.`date_register` AS `Fecha de Registro`
    FROM
        ((`jblue_test`.`users` `a`
        JOIN `jblue_test`.`street` `b` ON ((`a`.`street` = `b`.`id`)))
        JOIN `jblue_test`.`water_intakes` `c` ON ((`a`.`water_intakes` = `c`.`id`)))