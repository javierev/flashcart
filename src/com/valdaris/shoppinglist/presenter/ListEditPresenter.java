/**
 * This file is part of Flash Cart.
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

import com.valdaris.shoppinglist.dao.ListItemDao;
import com.valdaris.shoppinglist.model.ListItem;
import com.valdaris.shoppinglist.model.ShoppingList;

/**
 * @author Javier Estévez
 * 
 */
public class ListEditPresenter {

    ListItemDao dataHandler;
    private ShoppingList list;
    
    public ListEditPresenter(ShoppingList list, ListItemDao dao) {
    	this.list = list;
    	this.dataHandler = dao;
    }

    public List<ListItem> getProductList() {
    	List<ListItem> items = list.getListItems();
    	if (items == null || items.isEmpty()) {
    		items = dataHandler.getListItems(list);
    		list.setListItems(items);
    	}
    	return list.getListItems();
    }

    public void saveList(List<ListItem> products) {

        dataHandler.setListItems(list, products);

    }
    
    public void addProduct(ListItem product) {
    	product.setList(list);
    	list.addListItem(product);
    }
    


}
