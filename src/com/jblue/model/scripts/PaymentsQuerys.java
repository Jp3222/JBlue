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
package com.jblue.model.scripts;

/**
 *
 * @author juanp
 */
public class PaymentsQuerys {

    public static String pay_of_day = """
                                      SELECT * FROM service_payments 
                                      WHERE YEAR(date_register) = YEAR(NOW()) AND month = %s
                                      """;

    public static String GET_SURCHARGES = """
                                          SELECT id FROM surcharges_payments 
                                          WHERE WHERE YEAR(date_register) = YEAR(NOW()) AND month = %s
                                          """;

}
