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
package com.valdaris.shoppinglist.model;

import java.io.Serializable;

/**
 * @author Javier Estévez
 * 
 */
public class ListItem implements Serializable {

    private static final long serialVersionUID = 4922783195826653362L;

    public static final String LIST_ID_FIELD = "list";
    public static final String PRODUCT_ID_FIELD = "product";
    public static final String AMOUNT_FIELD = "amount";
    public static final String BOUGHT_FIELD = "bought";
    public static final String UNIT = "unit";

    private Integer id;

    private ShoppingList list;

    private Product product;

    private Double amount;

    private Boolean bought;

    private String unit;

    public ListItem() {

    }

    public ListItem(ShoppingList list, Product product, double amount,
            String unit, boolean bought) {
        this.list = list;
        this.product = product;
        this.amount = amount;
        this.bought = bought;
        this.unit = unit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ShoppingList getList() {
        return list;
    }

    public void setList(ShoppingList list) {
        this.list = list;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getBought() {
        return bought;
    }

    public void setBought(Boolean bought) {
        this.bought = bought;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
