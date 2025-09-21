SELECT 
	CONCAT(usr.first_name, usr.last_name1, usr.last_name2) AS USER_NAME, 
    COUNT(wki.user) AS USER_REPEAT 
FROM 
	jblue.wki_water_intakes wki
LEFT JOIN 
	jblue.usr_users usr ON usr.id = wki.user
GROUP BY wki.user
ORDER BY USER_NAME;