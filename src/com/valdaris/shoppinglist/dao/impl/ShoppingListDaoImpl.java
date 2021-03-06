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
package com.valdaris.shoppinglist.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.valdaris.shoppinglist.dao.DatabaseHelper;
import com.valdaris.shoppinglist.dao.ShoppingListDao;
import com.valdaris.shoppinglist.model.ShoppingList;

public class ShoppingListDaoImpl implements ShoppingListDao {
    
    private static final String TAG = ShoppingListDaoImpl.class.getCanonicalName();

    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    @Override
    public List<ShoppingList> getLists() {

        ArrayList<ShoppingList> lists = new ArrayList<ShoppingList>();
        SQLiteDatabase db = new DatabaseHelper().getReadableDatabase();
        String sort = ShoppingList.DATA_CREATION_FIELD_NAME;
        Cursor cursor = db.query(ShoppingList.TABLE_NAME, null, null, null,
                null, null, sort);
        while (cursor.moveToNext()) {
            ShoppingList list = new ShoppingList();
            list.setId(cursor.getInt(cursor
                    .getColumnIndex(ShoppingList.ID_FIELD_NAME)));
            try {
            	String date = cursor.getString(cursor
                        .getColumnIndex(ShoppingList.DATA_CREATION_FIELD_NAME));
            	if (date != null) {
            		list.setCreationDate(FORMATTER.parse(date));
            	}
            	date = cursor.getString(cursor
                        .getColumnIndex(ShoppingList.DATA_BUY_FIELD_NAME));
            	if (date != null) {
            		list.setBuyDate(FORMATTER.parse(date));
            	}
            } catch (ParseException e) {
                Log.e(TAG, "Error formatting date", e);
            }
            list.setStatus(cursor.getString(cursor.getColumnIndex(ShoppingList.STATUS_FIELD_NAME)));
            list.setName(cursor.getString(cursor.getColumnIndex(ShoppingList.LIST_NAME)));
            lists.add(list);
        }
        
        cursor.close();
        db.close();

        return lists;
    }

    @Override
    public void create(ShoppingList list) {
    	
        ContentValues values = new ContentValues();
        
        
        if (list.getCreationDate() != null) {
        	values.put(ShoppingList.DATA_CREATION_FIELD_NAME, FORMATTER.format(list.getCreationDate()));
        }
        if (list.getBuyDate() != null) { 
        	values.put(ShoppingList.DATA_BUY_FIELD_NAME, FORMATTER.format(list.getBuyDate()));
        }
        values.put(ShoppingList.LIST_NAME, list.getName());
        values.put(ShoppingList.STATUS_FIELD_NAME, String.valueOf(list.getStatus()));

        SQLiteDatabase db = new DatabaseHelper().getReadableDatabase();
        try {
        	db.insertOrThrow(ShoppingList.TABLE_NAME, null, values);
        } catch (SQLException e) {
        	Log.e(TAG, "Error on saving list", e);
        	//TODO error message to user
        	
        } finally {
        	db.close();
        }

    }

}
