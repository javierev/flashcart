/**
 * This file is part of Flash Cart.
 *
 * Copyright (C) 2011 Javier Estévez
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
package com.valdaris.shoppinglist.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Javier Estévez
 * 
 */
public class ShoppingList implements Serializable {

    private static final long serialVersionUID = -2657829638082397222L;

    public static final String TABLE_NAME = "shopping_list";
    
    public static final String ID_FIELD_NAME = "id";
    public static final String DATA_CREATION_FIELD_NAME = "creation_date";
    public static final String DATA_BUY_FIELD_NAME = "buy_date";
    public static final String STATUS_FIELD_NAME = "status";
    public static final String LIST_NAME = "name";

    public static final char EMPTY = 'e';
    public static final char INCOMPLETE = 'i';
    public static final char COMPLETE = 'c';
    public static final char FINISHED = 'f';

    private Integer id;

    private Date creationDate;

    private Date buyDate;

    private char status;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;

    }

    public String getName() {
        if (name != null) {
            return name;
        } else {
            return getCreationDateString();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    private String getCreationDateString() {
        Locale currentLocale = Locale.getDefault();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(
                DateFormat.DEFAULT, DateFormat.MEDIUM, currentLocale);
        return creationDate == null ? "" : dateFormat.format(creationDate);
    }

}
