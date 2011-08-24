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
package com.valdaris.shoppinglist.presenter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.valdaris.shoppinglist.data.IDataHandler;
import com.valdaris.shoppinglist.data.ShoppingList;
import com.valdaris.shoppinglist.view.IShoppingListListView;

/**
 * @author Javier Estévez
 *
 */
public class ShoppingListListPresenter {

    IShoppingListListView view;
    IDataHandler dataHandler;

    public ShoppingListListPresenter(IShoppingListListView view, IDataHandler dataHandler) {
	this.view = view;
	this.dataHandler = dataHandler;
    }

    /**
     * Fills UI List
     */
    public void fillList() {
	List<ShoppingList> list = dataHandler.getLists();
	ArrayList<String> shownList = new ArrayList<String>();
	for(ShoppingList sl : list) {
	    shownList.add(getCreationDateString(sl.getCreationDate()));
	}
	view.fillList(shownList);
    }

    /**
     * Creates a new list
     */
    public void createList() {
	ShoppingList sList = new ShoppingList();
	sList.setCreationDate(new Date());
	sList.setStatus(ShoppingList.EMPTY);
	dataHandler.create(sList);
	fillList();
    }

    private String getCreationDateString(Date creationDate) {
	Locale currentLocale = Locale.getDefault();
	DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT,
		DateFormat.MEDIUM, currentLocale);
	return creationDate == null ? "" : dateFormat.format(creationDate);
    }



}
