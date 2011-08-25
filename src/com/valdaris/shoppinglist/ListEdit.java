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

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.valdaris.shoppinglist.data.DataHandler;
import com.valdaris.shoppinglist.data.DatabaseHelper;
import com.valdaris.shoppinglist.data.ListProduct;
import com.valdaris.shoppinglist.presenter.ListEditPresenter;
import com.valdaris.shoppinglist.view.IListEdit;

/**
 * @author Javier Estévez
 *
 */
public class ListEdit extends OrmLiteBaseActivity<DatabaseHelper> implements IListEdit{

    private static final String LIST_ID = "listId";

    private ListView listView;
//    private Button saveButton;

    private ListEditPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	presenter = new ListEditPresenter(this, new DataHandler(getHelper()));

	setContentView(R.layout.list_edit);

	listView = (ListView) findViewById(R.id.edit_product_list);

    }

    protected void onResume() {
	super.onResume();
	presenter.fillList(getListId());
    }


    public static void callMe(Context c, Integer listId) {
	Intent intent = new Intent(c, ListEdit.class);
	intent.putExtra(LIST_ID, listId);
	c.startActivity(intent);
    }

    private int getListId() {
	return getIntent().getIntExtra(LIST_ID, -1);
    }

    /* (non-Javadoc)
     * @see com.valdaris.shoppinglist.view.IListEdit#fillList(java.util.List)
     */
    @Override
    public void fillList(List<ListProduct> products) {
	Log.i(ListEdit.class.getName(), "Showing products in list id " + getListId());
	ArrayAdapter<ListProduct> arrayAdapter = new ListProductAdapter(this, R.layout.list_edit_row, products);
	listView.setAdapter(arrayAdapter);
    }

    private class ListProductAdapter extends ArrayAdapter<ListProduct> {


	public ListProductAdapter(Context context,
		int textViewResourceId, List<ListProduct> objects) {
	    super(context, textViewResourceId, objects);
	}

	public View getView(int position, View convertView, ViewGroup parent) {

	    View v = convertView;
	    if (v == null) {
		LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = vi.inflate(R.layout.list_edit_row, null);
	    }

	    ListProduct product = getItem(position);

	    TextView text = (TextView) v.findViewById(R.id.editProductList);
	    text.setText(product.getProduct().getName());

	    return v;

	}

    }

}
