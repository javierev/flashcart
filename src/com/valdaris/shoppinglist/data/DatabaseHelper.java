/**
 * This file is part of Flash Chart.
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
package com.valdaris.shoppinglist.data;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * @author Javier Estévez
 *
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "shoppinglist.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Product, Integer> productDao;
    private Dao<ShoppingList, Integer> listDao;

    public DatabaseHelper(Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase arg0, ConnectionSource connectionSource) {
	try {
	    TableUtils.createTable(connectionSource, Product.class);
	    TableUtils.createTable(connectionSource, ShoppingList.class);
	} catch (SQLException e) {
	    Log.e(DatabaseHelper.class.getName(), "Unable to create database");
	}
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion,
	    int newVersion) {
	try {
	    TableUtils.dropTable(connectionSource, Product.class, true);
	} catch (SQLException e) {
	    Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database");
	}
    }

    public Dao<Product, Integer> getProductDao() throws SQLException {
	if (productDao == null) {
	    productDao = getDao(Product.class);
	}
	return productDao;
    }

    public Dao<ShoppingList, Integer> getListDao() throws SQLException {
	if (listDao == null) {
	    listDao = getDao(ShoppingList.class);
	}
	return listDao;
    }

}
