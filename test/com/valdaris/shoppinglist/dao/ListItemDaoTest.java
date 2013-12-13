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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.valdaris.shoppinglist.dao.ListItemDao;
import com.valdaris.shoppinglist.model.ListItem;
import com.valdaris.shoppinglist.model.Product;
import com.valdaris.shoppinglist.model.ShoppingList;

/**
 * 
 * @author Javier Estévez
 * 
 */
public class ListItemDaoTest implements ListItemDao {

    private HashMap<Integer, Product> products;
    private HashMap<Integer, ListItem> listItems;
    private Integer lastProduct = 0, lastListItem = 0;

    public ListItemDaoTest() {
        // creates some data for testing
        products = new HashMap<Integer, Product>();
        listItems = new HashMap<Integer, ListItem>();
        setMockData();
    }

    @Override
    public List<Product> findProductsByName(String name) {
        ArrayList<Product> productsFound = new ArrayList<Product>();
        Iterator<Integer> iterator = products.keySet().iterator();
        while (iterator.hasNext()) {
            Product p = products.get(iterator.next());
            if (p.getName().equalsIgnoreCase(name)) {
                productsFound.add(p);
            }
        }
        return productsFound;
    }

    @Override
    public void saveOrUpdate(ListItem listItem) {
        // save product
        saveProduct(listItem.getProduct());
        // save list item
        saveListItem(listItem);
    }

    @Override
    public ListItem getListItem(Integer id) {
        return listItems.get(id);
    }

    private void saveProduct(Product product) {
        if (product.getId() == null) {
            while (products.containsKey(lastProduct)) {
                lastProduct++;
            }
            product.setId(lastProduct);
        }
        products.put(product.getId(), product);

    }

    private void saveListItem(ListItem listItem) {
        if (listItem.getId() == null) {
            while (listItems.containsKey(lastListItem)) {
                lastListItem++;
            }
            listItem.setId(lastListItem);
        }
        listItems.put(listItem.getId(), listItem);
    }

    private void setMockData() {

        // products
        Product product1 = new Product();
        product1.setId(0);
        product1.setName("Rice");
        products.put(0, product1);
        Product product2 = new Product();
        product2.setId(1);
        product2.setName("Coffe");
        products.put(1, product1);

        // list items
        ShoppingList list = new ShoppingList();
        ListItem listItem = new ListItem(list, product1, 1.0, "Kg", false);
        listItems.put(0, listItem);
    }

}
