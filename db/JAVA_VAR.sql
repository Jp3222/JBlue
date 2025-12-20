USE jblue;
SELECT CONCAT('public static final int INDEX_', UPPER(description),' = ', id,';') FROM cat_status ORDER BY id;


USE jblue_local;
SELECT * FROM information_schema.TABLES WHERE table_schema = 'jblue_local';
-- TABLE INDEX
USE jblue_local;
SELECT CONCAT('public static final int INDEX_', UPPER(table_name),' = ', id,';') FROM cat_tables_db ORDER BY id;

-- 
-- TABLE_TRUCATE_QUERY
USE jblue_local;
SELECT CONCAT('TRUNCATE TABLE jblue_local.', TABLE_NAME)
FROM information_schema.TABLES
WHERE table_schema = 'jblue_local';

-- TABLE_NAMES
USE jblue_local;
SELECT CONCAT('protected static final String ', UPPER(TABLE_NAME), '_NAME = "', TABLE_NAME, '";')
FROM information_schema.tables
WHERE table_schema = 'jblue_local';

-- ARRAY CONST 
SELECT
	CONCAT('protected static final String[] ',
    UPPER(TABLE_NAME),'_FIELDS = {', GROUP_CONCAT('"', COLUMN_NAME,'"'),
    '};')
FROM
    information_schema.COLUMNS
WHERE
    table_schema = 'jblue_local'
GROUP BY
    table_name;
    
-- ARRAY CONST 
SELECT
	CONCAT('protected static final String[] ',
    UPPER(TABLE_NAME),'_GS = {', GROUP_CONCAT('"', UPPER(COLUMN_NAME),'"'),
    '};')
FROM
    information_schema.columns
WHERE
    table_schema = 'jblue_local'
GROUP BY
    table_name;
    
-- TABLE CONST 
SELECT
	CONCAT('public static final JDBTable ',
    UPPER(TABLE_NAME),'_TABLE = '
    'new JDBTable(',UPPER(TABLE_NAME),'_NAME,',UPPER(TABLE_NAME),'_FIELDS,',UPPER(TABLE_NAME),'_GS'
    ');')
FROM
    information_schema.columns
WHERE
    table_schema = 'jblue_local'
GROUP BY
    table_name;
    
