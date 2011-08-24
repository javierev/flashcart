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

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.valdaris.shoppinglist.data.DataHandler;
import com.valdaris.shoppinglist.data.DatabaseHelper;
import com.valdaris.shoppinglist.data.ShoppingList;
import com.valdaris.shoppinglist.presenter.FlashListPresenter;
import com.valdaris.shoppinglist.view.IFlashListView;

/**
 * @author Javier Estévez
 *
 */
public class Main extends OrmLiteBaseActivity<DatabaseHelper> implements IFlashListView {

    private ListView listView;
    private TextView textView;

    private static final int INSERT_ID = Menu.FIRST;

    private FlashListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

	super.onCreate(savedInstanceState);
	DatabaseHelper helper = getHelper();

	presenter = new FlashListPresenter(this, new DataHandler(helper));

	setContentView(R.layout.main);

	listView = (ListView) findViewById(R.id.list);
	textView = (TextView) findViewById(R.id.list_empty);

	registerForContextMenu(listView);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
	super.onCreateOptionsMenu(menu);
	menu.add(0, INSERT_ID, 0, R.string.menu_create_list);
	return true;
    }


    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
	switch(item.getItemId()) {
	case INSERT_ID:
	    presenter.createList();
	    return true;
	}
	return super.onMenuItemSelected(featureId, item);
    }

    @Override
    protected void onResume() {
	super.onResume();
	presenter.fillList();
    }

    private class ShoppingListAdapter extends ArrayAdapter<String> {

	public ShoppingListAdapter(Context context, int textViewResourceId, List<String> objects) {
	    super(context, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

	    View v = convertView;
	    if (v == null) {
		LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = vi.inflate(R.layout.shoppinglist_row, null);
	    }

	    String sList = getItem(position);

	    TextView textView = (TextView) v.findViewById(R.id.listCreationDate);
	    textView.setText(sList);

	    return v;
	}

    }

    /* (non-Javadoc)
     * @see com.valdaris.shoppinglist.view.IShoppingListListView#fillList(java.util.List)
     */
    @Override
    public void fillList(List<String> list) {
	Log.i(ShoppingList.class.getName(), "Showing shopping lists");
	if (list.size()>0) {
	    ArrayAdapter<String> arrayAdapter = new ShoppingListAdapter(this, R.layout.shoppinglist_row, list);
	    listView.setAdapter(arrayAdapter);
	    listView.setVisibility(View.VISIBLE);
	    textView.setVisibility(View.GONE);
	} else {
	    listView.setVisibility(View.GONE);
	}
    }

    /* (non-Javadoc)
     * @see com.valdaris.shoppinglist.view.IShoppingListListView#getListItem(long)
     */
    @Override
    public String getListItem(long pos) {
	// TODO get string
	return "";
    }

}
