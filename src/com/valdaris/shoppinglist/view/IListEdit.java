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
package com.valdaris.shoppinglist.view;

import java.util.List;

import com.valdaris.shoppinglist.model.ListItem;

/**
 * @author Javier Estévez
 * 
 */
public interface IListEdit {

    /**
     * Fill the UI list with the given list of products.
     * 
     * @param products
     */
    public void fillList(List<ListItem> products);

    /**
     * Get all products from the UI list.
     * 
     * @return
     */
    public List<ListItem> getProducts();

}
