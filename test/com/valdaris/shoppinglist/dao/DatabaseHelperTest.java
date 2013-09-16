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

import junit.framework.Assert;

import org.junit.Test;

/**
 * 
 * @author Javier Estévez
 *
 */
public class DatabaseHelperTest {
    
    @Test
    public void shoppingListTableCreationTest() {
        
        String sql = "CREATE TABLE shopping_list(" +
        		"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "creation_date VARCHAR(19), " +
        		"buy_date VARCHAR(19), " +
                "status VARCHAR(1), " + 
        		"name VARCHAR(10))";
        
        Assert.assertEquals(sql, CreationStatements.shoppingListTableCreationStatement());
        
    }
    
    @Test
    public void productTableCreationTest() {
        
        String sql = "CREATE TABLE product(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(10))";
        
        Assert.assertEquals(sql, CreationStatements.productTableCreationStatement());
        
    }
    
    @Test
    public void listItemTableCreationTest() {
        
        String sql = "CREATE TABLE list_item(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "list_id INTEGER, " +
                "product_id INTEGER, " +
                "amount DOUBLE, " + 
                "bought BOOLEAN, " +
                "unit TEXT, " +
                "FOREIGN KEY(list_id) REFERENCES shopping_list(id), " +
                "FOREIGN KEY(product_id) REFERENCES product(id))";
        
        Assert.assertEquals(sql, CreationStatements.listItemTableCreationStatement());
        
    }

}
