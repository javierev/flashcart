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
package com.valdaris.shoppinglist.test;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import com.valdaris.shoppinglist.model.ListItem;
import com.valdaris.shoppinglist.model.ShoppingList;
import com.valdaris.shoppinglist.presenter.ListItemEditPresenter;
import com.valdaris.shoppinglist.test.dao.ListItemDaoTest;
import com.valdaris.shoppinglist.test.view.ListItemEditViewTest;

/**
 * 
 * @author Javier Estévez
 * 
 */
public class ListItemEditTest extends TestCase {

    private ListItemDaoTest dao;
    private ListItemEditViewTest view;

    public void setUp() {
        dao = new ListItemDaoTest();
        view = new ListItemEditViewTest();
    }

    @Test
    public void testCreateListItem() {

        String productName = "Water";
        Double amount = 10.0;
        String unit = "Bottles";

        ShoppingList list = new ShoppingList();
        view.setProductName(productName);
        view.setAmount(amount.toString());
        view.setUnit(unit);
        ListItemEditPresenter presenter = new ListItemEditPresenter(view, dao,
                list);

        presenter.saveListItem();

        Assert.assertEquals(productName, presenter.getListItem().getProduct()
                .getName());
        Assert.assertEquals(amount, presenter.getListItem().getAmount());
        Assert.assertEquals(unit, presenter.getListItem().getUnit());

    }

    @Test
    public void testModifyListItem() {

        Double newAmount = 0.5;
        ListItem listItem = dao.getListItem(0);
        ListItemEditPresenter presenter = new ListItemEditPresenter(view, dao,
                listItem);

        view.setAmount(newAmount.toString());
        presenter.saveListItem();

        Assert.assertEquals(newAmount, presenter.getListItem().getAmount());

    }

}
