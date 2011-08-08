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
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.valdaris.shoppinglist.data.DatabaseHelper;
import com.valdaris.shoppinglist.data.ShoppingList;

/**
 * @author Javier Estévez
 *
 */
public class Main extends OrmLiteBaseActivity<DatabaseHelper> {

    private ListView listView;
    private TextView textView;

    private static final int INSERT_ID = Menu.FIRST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
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
	    createList();
	    return true;
	}
	return super.onMenuItemSelected(featureId, item);
    }

    private void createList() {
	try {
	    ShoppingList sList = new ShoppingList();
	    sList.setCreationDate(new Date());
	    sList.setStatus(ShoppingList.EMPTY);
	    Dao<ShoppingList, Integer> dao = getHelper().getShoppingListDao();
	    dao.create(sList);
	    fillList();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
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
	Log.i(ShoppingList.class.getName(), "Showing shopping lists");
	Dao<ShoppingList, Integer> dao = getHelper().getShoppingListDao();
	QueryBuilder<ShoppingList, Integer> builder = dao.queryBuilder();
	builder.orderBy(ShoppingList.DATA_CREATION_FIELD_NAME, false);
	List<ShoppingList> list = dao.query(builder.prepare());
	if (list.size()>0) {
	    ArrayAdapter<ShoppingList> arrayAdapter = new ShoppingListAdapter(this, R.layout.shoppinglist_row, list);
	    listView.setAdapter(arrayAdapter);
	    listView.setVisibility(View.VISIBLE);
	    textView.setVisibility(View.GONE);
	} else {
	    listView.setVisibility(View.GONE);
	}
    }

    private class ShoppingListAdapter extends ArrayAdapter<ShoppingList> {

	public ShoppingListAdapter(Context context, int textViewResourceId, List<ShoppingList> objects) {
	    super(context, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

	    View v = convertView;
	    if (v == null) {
		LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = vi.inflate(R.layout.shoppinglist_row, null);
	    }

	    ShoppingList sList = getItem(position);

	    fillText(v, R.id.listCreationDate, sList.getCreationDate());

	    return v;
	}

	private void fillText(View v, int id, Date creationDate) {

	    Locale currentLocale = Locale.getDefault();
	    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT,
		        DateFormat.MEDIUM, currentLocale);

	    TextView textView = (TextView) v.findViewById(id);
	    textView.setText(creationDate == null ? "" : dateFormat.format(creationDate));

	}



    }

}
