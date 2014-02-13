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

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.valdaris.shoppinglist.dao.DatabaseHelper;
import com.valdaris.shoppinglist.dao.ListItemDao;
import com.valdaris.shoppinglist.model.ListItem;
import com.valdaris.shoppinglist.model.Product;
import com.valdaris.shoppinglist.model.ShoppingList;

public class ListItemDaoImpl implements ListItemDao {
	
	private static final String TAG = ListItemDaoImpl.class.getCanonicalName();

	@Override
	public List<Product> findProductsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(ListItem listItem) {
		
		SQLiteDatabase db = new DatabaseHelper().getReadableDatabase();
		db.beginTransaction();
		try {
			saveOrUpdateListItem(db, listItem);
			db.setTransactionSuccessful();
		} catch (Exception e) {
			Log.e(TAG, "Error on saving list item", e);
		} finally {
		    db.close();
		}

	}

	@Override
	public ListItem getListItem(Integer id) {
		
		ListItem item = null;
		
		SQLiteDatabase db = new DatabaseHelper().getReadableDatabase();

		String query = "SELECT * FROM %s l " + " JOIN %s p ON l.%s = p.%s "
				+ " WHERE l.%s = ?";
		query = String.format(query, ListItem.TABLE_NAME, Product.TABLE_NAME,
				ListItem.PRODUCT_ID_FIELD, Product.ID_FIELD_NAME,
				ListItem.ID_FIELD_NAME);

		String[] selectionArgs = { Integer.toString(id) };
		
		Cursor cursor = db.rawQuery(query, selectionArgs);
		
		if (cursor.moveToNext()) {
			item = createListItemFromQuery(cursor);
		}
		
		return item;
	}
	
	@Override
	public List<ListItem> getListItems(ShoppingList list) {

		ArrayList<ListItem> listItems = new ArrayList<ListItem>();
		SQLiteDatabase db = new DatabaseHelper().getReadableDatabase();

		String query = "SELECT * FROM %s l " + " JOIN %s p ON l.%s = p.%s "
				+ " WHERE l.%s = ?";
		query = String.format(query, ListItem.TABLE_NAME, Product.TABLE_NAME,
				ListItem.PRODUCT_ID_FIELD, Product.ID_FIELD_NAME,
				ListItem.LIST_ID_FIELD);

		String[] selectionArgs = { Integer.toString(list.getId()) };

		Cursor cursor = db.rawQuery(query, selectionArgs);

		while (cursor.moveToNext()) {

			ListItem listItem = createListItemFromQuery(cursor);

			listItem.setList(list);
			listItems.add(listItem);

		}
		
		cursor.close();
		db.close();

		return listItems;
	}

	/**
	 * It creates a list item instance from the database query returned values.
	 * 
	 * @param cursor cursor of the query.
	 * @return list item.
	 */
	private ListItem createListItemFromQuery(Cursor cursor) {
		ListItem listItem = new ListItem();
		listItem.setAmount(cursor.getDouble(cursor.getColumnIndex(ListItem.AMOUNT_FIELD)));
		listItem.setBought(cursor.getString(cursor.getColumnIndex(ListItem.BOUGHT_FIELD)));
		listItem.setId(cursor.getInt(cursor.getColumnIndex(ListItem.ID_FIELD_NAME)));
		listItem.setUnit(cursor.getString(cursor.getColumnIndex(ListItem.UNIT)));
		
		Product product = new Product();
		product.setId(cursor.getInt(cursor.getColumnIndex(Product.ID_FIELD_NAME)));
		product.setName(cursor.getString(cursor.getColumnIndex(Product.NAME_FIELD_NAME)));
		
		listItem.setProduct(product);
		return listItem;
	}

    @Override
    public void setListItems(ShoppingList list, List<ListItem> products) {
    	
    	SQLiteDatabase db = new DatabaseHelper().getReadableDatabase();
    	db.beginTransaction();
    	
    	try {
    		for (ListItem item : products) {
    			saveOrUpdateListItem(db, item);
    		}
    		db.setTransactionSuccessful();
    	} catch (Exception e) {
    		Log.e(TAG, "Error on saving list items", e);
    		//TODO show error to user
    	} finally {
    		db.close();
    	}
    	
        
    }

    /**
     * Inserts or updates a list item. The associated product will be saved
     * if it is not already present on DB.
     * 
     * @param db database connection.
     * @param item list item to ve saved.
     */
	private void saveOrUpdateListItem(SQLiteDatabase db, ListItem item) {
	    
	    //Step 0: make sure the foreign key is correct
	    db.setForeignKeyConstraintsEnabled(true);
	    
		//Step 1: save product if it's not already on DB
		Product product = item.getProduct();
		if (product.getId() == null) {
			ContentValues values = new ContentValues();
			values.put(Product.NAME_FIELD_NAME, product.getName());
			long productId = db.insertOrThrow(Product.TABLE_NAME, null, values);
			if (productId != -1) {
			    product.setId((int) productId);
			}
		}
		
		//Step 2: save list item (it may be inserted or updated) 
		ContentValues values = new ContentValues();
		values.put(ListItem.AMOUNT_FIELD, item.getAmount());
		values.put(ListItem.BOUGHT_FIELD, item.getBought());
		values.put(ListItem.UNIT, item.getUnit());
		values.put(ListItem.PRODUCT_ID_FIELD, product.getId());
		if (item.getId() == null) {
			db.insertOrThrow(ListItem.TABLE_NAME, null, values);
		} else {
			String whereClause = String.format("%s = ?", ListItem.ID_FIELD_NAME);
			String[] whereArgs = { Integer.toString(item.getId()) };
			db.update(ListItem.TABLE_NAME, values, whereClause, whereArgs);
		}
	}

}
