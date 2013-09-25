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

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.valdaris.shoppinglist.Main;

/**
 * Database helper to be used on DAOs. It includes creation and upgrade.
 * 
 * @author Javier Estévez
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    
    private final static String DATABASE_NAME = "FLASH_CART";
    private final static int DATABASE_VERSION = 1;
    
    
    public DatabaseHelper() {
        super(Main.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        
        String shoppingListSQL = CreationStatements.shoppingListTableCreationStatement();
        db.execSQL(shoppingListSQL);
        
        String productSQL = CreationStatements.productTableCreationStatement();
        db.execSQL(productSQL);
        
        String listItemSQL = CreationStatements.listItemTableCreationStatement();
        db.execSQL(listItemSQL);
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        
    }

}
