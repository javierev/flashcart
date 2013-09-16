/**
 * This file is part of Flash Cart.
 *
 * Copyright (C) 2013 Javier Estévez
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
package com.valdaris.shoppinglist.dao;

import com.valdaris.shoppinglist.model.ListItem;
import com.valdaris.shoppinglist.model.Product;
import com.valdaris.shoppinglist.model.ShoppingList;

/**
 * Static methods to build SQL creation statements. 
 * 
 * @author Javier Estévez
 *
 */
public class CreationStatements {
    
    /**
     * SQL creation for shopping list table.
     * 
     * @return SQL Statement
     */
    static String shoppingListTableCreationStatement() {
        return "CREATE TABLE " + ShoppingList.TABLE_NAME +
                "(" + ShoppingList.ID_FIELD_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ShoppingList.DATA_CREATION_FIELD_NAME + " VARCHAR(19), " +
                ShoppingList.DATA_BUY_FIELD_NAME + " VARCHAR(19), " +
                ShoppingList.STATUS_FIELD_NAME + " VARCHAR(1), " + 
                ShoppingList.LIST_NAME + " VARCHAR(10))";
    }
    
    /**
     * SQL creation for product table.
     * 
     * @return SQL Statement
     */
    static String productTableCreationStatement() {
        return "CREATE TABLE " + Product.TABLE_NAME +
                "(" + Product.ID_FIELD_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Product.NAME_FIELD_NAME + " VARCHAR(10))";
    }

    /**
     * SQL creation for list item table.
     * 
     * @return SQL Statement
     */
    static String listItemTableCreationStatement() {
        return "CREATE TABLE " + ListItem.TABLE_NAME +
                "(" + ListItem.ID_FIELD_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ListItem.LIST_ID_FIELD + " INTEGER, " + 
                ListItem.PRODUCT_ID_FIELD + " INTEGER, " +
                ListItem.AMOUNT_FIELD + " DOUBLE, " + 
                ListItem.BOUGHT_FIELD + " BOOLEAN, " + 
                ListItem.UNIT + " TEXT, " + 
                "FOREIGN KEY(" + ListItem.LIST_ID_FIELD + ") REFERENCES " + ShoppingList.TABLE_NAME + "(" + ShoppingList.ID_FIELD_NAME + "), " +
                "FOREIGN KEY(" + ListItem.PRODUCT_ID_FIELD + ") REFERENCES " + Product.TABLE_NAME + "(" + Product.ID_FIELD_NAME + "))";
    }
    
}
