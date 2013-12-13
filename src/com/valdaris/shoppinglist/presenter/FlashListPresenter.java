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

import java.util.Calendar;
import java.util.List;

import com.valdaris.shoppinglist.dao.IDataHandler;
import com.valdaris.shoppinglist.model.ShoppingList;

/**
 * @author Javier Estévez
 * 
 */
public class FlashListPresenter {

    IDataHandler dataHandler;
    
    public FlashListPresenter(IDataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    /**
     * Gets shopping lists from DB
     */
    public List<ShoppingList> getLists() {
        return dataHandler.getLists();
    }

    /**
     * Creates a new list
     */
    public void createList() {
        ShoppingList sList = new ShoppingList();
        sList.setCreationDate(Calendar.getInstance().getTime());
        sList.setStatus(ShoppingList.EMPTY);
        dataHandler.create(sList);
    }

}
