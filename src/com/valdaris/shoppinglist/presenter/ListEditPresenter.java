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

import com.valdaris.shoppinglist.data.IDataHandler;
import com.valdaris.shoppinglist.data.model.ListProduct;
import com.valdaris.shoppinglist.data.model.Product;
import com.valdaris.shoppinglist.data.model.ShoppingList;
import com.valdaris.shoppinglist.view.IListEdit;

/**
 * @author Javier Estévez
 * 
 */
public class ListEditPresenter {

    IListEdit view;
    IDataHandler dataHandler;
    private ShoppingList list;

    public ListEditPresenter(IListEdit view, IDataHandler dataHandler) {
        this.view = view;
        this.dataHandler = dataHandler;
        this.list = new ShoppingList();
    }

    public void fillList(int listId) {

        list.setId(listId);

        List<ListProduct> products = dataHandler.getListProducts(list);

        view.fillList(products);
    }

    public void saveList() {

        List<ListProduct> products = view.getProducts();

        dataHandler.setListProducts(list, products);

    }
    
    public void addProduct(ListProduct product) {
        
    }
    
    /**
     * Finds a product containing the given name or returns null if 
     * it does'nt exist.
     * @param name the name of the product to be found.
     * @return the product if it exists, null otherwise.
     */
    public Product findProductByName(String name) {
        List<Product> products = dataHandler.findProductsByName(name);
        if (products.size()>0) {
            return products.get(0);
        } else {
            return null;
        }
    }

}
