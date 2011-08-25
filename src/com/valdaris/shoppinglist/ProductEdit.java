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
package com.valdaris.shoppinglist;

import java.sql.SQLException;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;
import com.valdaris.shoppinglist.data.DatabaseHelper;
import com.valdaris.shoppinglist.data.Product;

/**
 * @author Javier Estévez
 *
 */
public class ProductEdit extends OrmLiteBaseActivity<DatabaseHelper> {

    private static final String PRODUCT_ID = "productId";

    private EditText productName;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	setContentView(R.layout.product_edit);

	productName = (EditText) findViewById(R.id.productName);
	saveButton = (Button) findViewById(R.id.createProduct);

	saveButton.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
		try {
		    Product product = saveToObject();
		    Dao<Product, Integer> dao = getHelper().getProductDao();
		    boolean alreadyCreated = false;
		    if (product.getId()!=null) {
			Product dbProduct = dao.queryForId(product.getId());
			if (dbProduct!=null) {
			    product.setName(dbProduct.getName());
			    dao.update(product);
			    alreadyCreated = true;
			}
		    }

		    if (alreadyCreated) {
			finish();
		    } else {
			dao.create(product);
			finish();
		    }
		} catch (SQLException e) {
		    throw new RuntimeException(e);
		}
	    }
	});
    }

    private Product saveToObject() {
	Product product = new Product();
	int productId = getProductId();
	if (productId > -1) {
	    product.setId(productId);
	}
	product.setName(productName.getText().toString());
	return product;
    }

    private int getProductId() {
	return getIntent().getIntExtra(PRODUCT_ID, -1);
    }

    public static void callMe(Context c) {
	c.startActivity(new Intent(c, ProductEdit.class));
    }

    public static void callMe(Context c, Integer productId) {
	Intent intent = new Intent(c, ProductEdit.class);
	intent.putExtra(PRODUCT_ID, productId);
	c.startActivity(intent);
    }

}
