/**
 * This file is part of Flash Cart.
 *
 * Copyright (C) 2012 Javier Estévez
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.valdaris.shoppinglist.exception;

/**
 * This class should be used when the user enters an invalid
 * value in a field. Like a letter when a number is expected.
 * 
 * @author Javier Estévez
 *
 */
public class InvalidValueException extends RuntimeException {
    
    private static final long serialVersionUID = -1392538034453618446L;

    public InvalidValueException(String message, Throwable cause) {
        super(message, cause);
    }

}