/**
 * This file is part of Flash Cart.
 *
 * Copyright (C) 2012 Javier Estévez
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

import java.util.List;

import com.valdaris.shoppinglist.model.ListItem;
import com.valdaris.shoppinglist.model.Product;
import com.valdaris.shoppinglist.model.ShoppingList;

public interface ListItemDao {

    /**
     * Obtains a list of products with the given name.
     * 
     * @param name
     *            the name of the products to be found.
     * @return list of products.
     */
    public List<Product> findProductsByName(String name);

    /**
     * Saves the list item.
     * 
     * @param listItem
     */
    public void saveOrUpdate(ListItem listItem);

    /**
     * Gets the list item with the given id.
     * 
     * @param id
     * @return
     */
    public ListItem getListItem(Integer id);
    
    /**
     * Obtains all products from a given shopping list.
     * 
     * @param list
     *            the shopping list.
     * @return list of products.
     */
    public List<ListItem> getListItems(ShoppingList list);

    /**
     * Saves all products in list.
     * 
     * @param list
     *            the shopping list
     * @param items
     *            list of products to attach to the shopping list.
     */
    public void setListItems(ShoppingList list, List<ListItem> items);

}
