/**
 * This file is part of Flash Chart.
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
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.valdaris.shoppinglist.data.DatabaseHelper;
import com.valdaris.shoppinglist.data.Product;

/**
 * @author Javier Estévez
 *
 */
public class ProductList extends OrmLiteBaseActivity<DatabaseHelper> {

    private ListView listView;

    private static final int INSERT_ID = Menu.FIRST;
    private static final int ACTIVITY_CREATE = 0;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.product_list);

	listView = (ListView) findViewById(R.id.productList);

	listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

	    @Override
	    public boolean onItemLongClick(AdapterView<?> adapterView, View view,
		    int i, long l) {
		Product product = (Product) listView.getAdapter().getItem(i);
		Log.i(ProductList.class.getName(), "Product selected: " + product.getName());
		ProductEdit.callMe(ProductList.this, product.getId());
		return true;
	    }


	});

	registerForContextMenu(listView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	super.onCreateOptionsMenu(menu);
	menu.add(0, INSERT_ID, 0, R.string.menu_insert_product);
	return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
	switch(item.getItemId()) {
	case INSERT_ID:
	    createProduct();
	    return true;
	}
	return super.onMenuItemSelected(featureId, item);
    }

    private void createProduct() {
	Intent i = new Intent(this, ProductEdit.class);
	startActivityForResult(i, ACTIVITY_CREATE);
    }


    @Override
    protected void onResume() {
	super.onResume();
	try {
	    fillList();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }



    private void fillList() throws SQLException {
	Log.i(ProductList.class.getName(), "Showing product list");
	Dao<Product, Integer> dao = getHelper().getProductDao();
	QueryBuilder<Product, Integer> builder = dao.queryBuilder();
	builder.orderBy(Product.NAME_FIELD_NAME, true).limit(30);
	List<Product> list = dao.query(builder.prepare());
	ArrayAdapter<Product> arrayAdapter = new ProductsAdapter(this, R.layout.product_row, list);
	listView.setAdapter(arrayAdapter);
    }

    private class ProductsAdapter extends ArrayAdapter<Product> {

	public ProductsAdapter(Context context, int textViewResourceId,
		List<Product> objects) {
	    super(context, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

	    View v = convertView;
	    if (v == null) {
		LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = vi.inflate(R.layout.product_row, null);
	    }

	    Product product = getItem(position);

	    fillText(v, R.id.productName, product.getName());

	    return v;
	}

	private void fillText(View v, int id, String text) {
	    TextView textView = (TextView) v.findViewById(id);
	    textView.setText(text == null ? "" : text);
	}

    }
}