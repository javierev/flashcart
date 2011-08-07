package com.valdaris.shoppinglist.data;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "shoppinglist.db";
	private static final int DATABASE_VERSION = 1;
	
	private Dao<Product, Integer> productDao;
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Product.class);
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

}
