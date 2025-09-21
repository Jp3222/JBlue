INSERT INTO jblue.cat_status SELECT * FROM jblue_structure.cat_status;
INSERT INTO jblue.cat_process_type SELECT * FROM jblue.cat_process_type;
INSERT INTO jblue.cat_history_type_mov SELECT * FROM jblue_structure.cat_history_type_mov;
INSERT INTO jblue.cat_tables_db SELECT * FROM jblue_structure.cat_tables_db;

COMMIT;
