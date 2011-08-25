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

import java.util.List;

import com.valdaris.shoppinglist.data.IDataHandler;
import com.valdaris.shoppinglist.data.ListProduct;
import com.valdaris.shoppinglist.data.ShoppingList;
import com.valdaris.shoppinglist.view.IListEdit;

/**
 * @author Javier Estévez
 *
 */
public class ListEditPresenter {

    IListEdit view;
    IDataHandler dataHandler;

    public ListEditPresenter(IListEdit view, IDataHandler dataHandler) {
	this.view = view;
	this.dataHandler = dataHandler;
    }

    public void fillList(int listId) {

	ShoppingList list = new ShoppingList();
	list.setId(listId);

	List<ListProduct> products = dataHandler.getListProducts(list);

	view.fillList(products);
    }

}
