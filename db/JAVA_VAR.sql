SELECT * FROM information_schema.tables
WHERE table_schema = 'jblue';

-- TABLE_NAMES
USE jblue;
SELECT CONCAT('TRUNCATE TABLE jblue.', TABLE_NAME)
FROM information_schema.tables
WHERE table_schema = 'jblue';
-- ARRAY CONST 
SELECT
	'public static String[] ',
    CONCAT(UPPER(table_name), ' = {'),
    GROUP_CONCAT('"', COLUMN_NAME ORDER BY ORDINAL_POSITION, '"') AS `Columns`,
    '};'
FROM
    information_schema.columns
WHERE
    table_schema = 'jblue'
GROUP BY
    table_name;