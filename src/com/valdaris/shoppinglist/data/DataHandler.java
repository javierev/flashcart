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
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

/**
 * @author Javier Estévez
 *
 */
public class DataHandler implements IDataHandler {

    DatabaseHelper helper;

    public DataHandler(DatabaseHelper helper) {
	this.helper = helper;
    }

    /* (non-Javadoc)
     * @see com.valdaris.shoppinglist.data.IDataHandler#getLists()
     */
    @Override
    public List<ShoppingList> getLists() {

	Dao<ShoppingList, Integer> dao;
	try {
	    dao = helper.getShoppingListDao();
	    QueryBuilder<ShoppingList, Integer> builder = dao.queryBuilder();
	    builder.orderBy(ShoppingList.DATA_CREATION_FIELD_NAME, false);
	    List<ShoppingList> list = dao.query(builder.prepare());
	    return list;
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    /* (non-Javadoc)
     * @see com.valdaris.shoppinglist.data.IDataHandler#save(com.valdaris.shoppinglist.data.ShoppingList)
     */
    @Override
    public void create(ShoppingList list) {
	try {
	    Dao<ShoppingList, Integer> dao = helper.getShoppingListDao();
	    dao.create(list);
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }



}
