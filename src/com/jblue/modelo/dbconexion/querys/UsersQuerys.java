/*
 * Copyright (C) 2025 juanp
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jblue.modelo.dbconexion.querys;

/**
 *
 * @author juanp
 */
public class UsersQuerys {

    public static String users_not_paid = """
                                          SELECT
                                          	CONCAT(a.first_name, " ", a.last_name1, " ", a.last_name2) AS 'USUARIO',
                                              COUNT(b.month)
                                          FROM users a
                                          INNER JOIN service_payments b 
                                          	ON a.`id` = b.`user`
                                          WHERE YEAR(a.date_register) = %s
                                          GROUP BY USUARIO;
                                          """;
}
