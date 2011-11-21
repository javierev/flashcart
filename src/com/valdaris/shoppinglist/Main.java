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
import com.valdaris.shoppinglist.data.DataHandler;
import com.valdaris.shoppinglist.data.DatabaseHelper;
import com.valdaris.shoppinglist.data.model.ShoppingList;
import com.valdaris.shoppinglist.presenter.FlashListPresenter;
import com.valdaris.shoppinglist.view.IFlashListView;

/**
 * @author Javier Estévez
 * 
 */
public class Main extends OrmLiteBaseActivity<DatabaseHelper> implements
        IFlashListView {

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                ShoppingList list = (ShoppingList) listView.getAdapter()
                        .getItem(arg2);
                Log.i(Main.class.getName(), "List selected: " + list.getName());
                switch (list.getStatus()) {
                case ShoppingList.EMPTY:
                case ShoppingList.INCOMPLETE:
                    ListEdit.callMe(Main.this, list.getId());
                    break;
                case ShoppingList.COMPLETE:
                case ShoppingList.FINISHED:
                default:
                    // TODO open non editable list
                }
            }

        });

        registerForContextMenu(listView);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, INSERT_ID, 0, R.string.menu_create_list);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
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

    private class ShoppingListAdapter extends ArrayAdapter<ShoppingList> {

        public ShoppingListAdapter(Context context, int textViewResourceId,
                List<ShoppingList> objects) {
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

            TextView textView = (TextView) v
                    .findViewById(R.id.listCreationDate);
            textView.setText(sList.getName());

            return v;
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.valdaris.shoppinglist.view.IShoppingListListView#fillList(java.util
     * .List)
     */
    @Override
    public void fillList(List<ShoppingList> list) {
        Log.i(ShoppingList.class.getName(), "Showing shopping lists");
        if (list.size() > 0) {
            ArrayAdapter<ShoppingList> arrayAdapter = new ShoppingListAdapter(
                    this, R.layout.shoppinglist_row, list);
            listView.setAdapter(arrayAdapter);
            listView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        } else {
            listView.setVisibility(View.GONE);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.valdaris.shoppinglist.view.IShoppingListListView#getListItem(long)
     */
    @Override
    public String getListItem(int pos) {
        // TODO get string
        ShoppingList list = (ShoppingList) listView.getAdapter().getItem(pos);
        return list.getName();
    }

}
