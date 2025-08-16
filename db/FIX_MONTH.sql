USE jblue_test;

UPDATE service_payments SET month = 1 WHERE month_name = 'ENE';
UPDATE service_payments SET month = 2 WHERE month_name = 'FEB';
UPDATE service_payments SET month = 3 WHERE month_name = 'MAR';
UPDATE service_payments SET month = 4 WHERE month_name = 'ABR';
UPDATE service_payments SET month = 5 WHERE month_name = 'MAY';
UPDATE service_payments SET month = 6 WHERE month_name = 'JUN';
UPDATE service_payments SET month = 7 WHERE month_name = 'JUL';
UPDATE service_payments SET month = 8 WHERE month_name = 'AGO';
UPDATE service_payments SET month = 9 WHERE month_name = 'SEP';
UPDATE service_payments SET month = 10 WHERE month_name = 'OCT';
UPDATE service_payments SET month = 11 WHERE month_name = 'NOV';
UPDATE service_payments SET month = 12 WHERE month_name = 'DIC';

SELECT * FROM service_payments;