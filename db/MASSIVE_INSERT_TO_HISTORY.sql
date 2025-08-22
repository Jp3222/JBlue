INSERT INTO `jblue_test`.`general_history` (
    affected_environment_id,
    affected_environment_desc,
    affected_table,
    id_mov
)
SELECT
    1,
    'DATA BASE',
    'USERS',
    id
FROM
    `jblue_test`.`logbook_to_users`;
    
INSERT INTO `jblue_test`.`general_history` (
    affected_environment_id,
    affected_environment_desc,
    affected_table,
    id_mov
)
SELECT
    1,
    'PROGRAM',
    'HISTORY',
    id
FROM
    `jblue_test`.`history`;

