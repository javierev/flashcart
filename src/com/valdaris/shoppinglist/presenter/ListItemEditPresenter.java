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
package com.valdaris.shoppinglist.presenter;

import java.util.List;

import com.valdaris.shoppinglist.dao.IListItemDao;
import com.valdaris.shoppinglist.exception.InvalidValueException;
import com.valdaris.shoppinglist.model.ListItem;
import com.valdaris.shoppinglist.model.Product;
import com.valdaris.shoppinglist.model.ShoppingList;
import com.valdaris.shoppinglist.view.IListItemEditView;

/**
 * 
 * @author Javier Estévez
 *
 */
public class ListItemEditPresenter {

    IListItemEditView view;
    IListItemDao listItemDao;
    ListItem listItem;
    
    private ListItemEditPresenter(IListItemEditView view, IListItemDao listItemDao) {
        this.view = view;
        this.listItemDao = listItemDao;
    }

    public ListItemEditPresenter(IListItemEditView view, IListItemDao listItemDao,
            ShoppingList list) {
        this(view, listItemDao);
        this.listItem = new ListItem();
        listItem.setBought(false);
        listItem.setList(list);
    }

    public ListItemEditPresenter(IListItemEditView view, IListItemDao listItemDao,
            ListItem listItem) {
        this(view, listItemDao);
        this.listItem = listItem;
            view.setProductName(listItem.getProduct().getName());
            view.setAmount(listItem.getAmount().toString());
            view.setUnit(listItem.getUnit());
    }

    public void saveListItem() throws InvalidValueException {
        Double amount;
        String productName = view.getProductName();
        String unit = view.getUnit();
        try {
            amount = Double.valueOf(view.getAmount());
        } catch (NumberFormatException e) {
            throw new InvalidValueException(
                    "Quantity is supossed to be a number value!", e);
        }
        
        listItem.setProduct(getProduct(productName));
        listItem.setAmount(amount);
        listItem.setUnit(unit);
        
        listItemDao.saveOrUpdate(listItem);

    }
    
    /**
     * Finds a product from database by its name. If none found, 
     * creates a new one.
     * 
     * @return product
     */
    private Product getProduct(String productName) {
        
        Product product;
        
        List<Product> products = listItemDao.findProductsByName(productName);
        if (products.size() > 0) {
            product = products.get(0);
        } else {
            product = new Product();
            product.setName(productName);
        }
        
        return product;
        
    }

}
