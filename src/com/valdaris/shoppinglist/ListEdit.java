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

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.valdaris.shoppinglist.dao.impl.ListItemDaoImpl;
import com.valdaris.shoppinglist.model.ListItem;
import com.valdaris.shoppinglist.model.Product;
import com.valdaris.shoppinglist.model.ShoppingList;
import com.valdaris.shoppinglist.presenter.ListEditPresenter;
import com.valdaris.shoppinglist.view.IListEdit;

/**
 * @author Javier Estévez
 * 
 */
public class ListEdit extends Activity implements
        IListEdit {

    private static final String LIST = "list";

    private ListView listView;
    private Button saveButton;
    private Button addProductButton;

    private ListEditPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new ListEditPresenter(getList(), new ListItemDaoImpl());

        setContentView(R.layout.list_edit);

        listView = (ListView) findViewById(R.id.edit_product_list);

        saveButton = (Button) findViewById(R.id.saveList);
        saveButton.setOnClickListener(new AdapterView.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                presenter.saveList(getProducts());
            }
        });
        
        addProductButton = (Button) findViewById(R.id.addProductButton);
        addProductButton.setOnClickListener(new AdapterView.OnClickListener() {

            @Override
            public void onClick(View arg1) {
                Log.i(ListEdit.class.getName(), "Adding a product in list id "
                        + getList().getId());
            }
        });

    }

    protected void onResume() {
        super.onResume();
        presenter.getProductList();
    }

    public static void callMe(Context c, ShoppingList list) {
        Intent intent = new Intent(c, ListEdit.class);
        intent.putExtra(LIST, list);
        c.startActivity(intent);
    }

    private ShoppingList getList() {
        return (ShoppingList) getIntent().getSerializableExtra(LIST);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.valdaris.shoppinglist.view.IListEdit#fillList(java.util.List)
     */
    @Override
    public void fillList(List<ListItem> products) {
        Log.i(ListEdit.class.getName(), "Showing products in list id "
                + getList().getId());
        ArrayAdapter<ListItem> arrayAdapter = new ListProductAdapter(this,
                R.layout.list_edit_row, products);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public List<ListItem> getProducts() {
        List<ListItem> list = new ArrayList<ListItem>();
        int productCount = listView.getAdapter().getCount();
        for (int i = 0; i < productCount; i++) {
            View v = listView.getAdapter().getView(i, null, null);
            TextView text = (TextView) v.findViewById(R.id.edit_product_list);
            String productName = text.getText().toString();
            ListItem lp = new ListItem();
            Product p = new Product();
            p.setName(productName);
            lp.setProduct(p);
            lp.setAmount(1.0);
            lp.setBought(false);
            list.add(lp);
        }
        return list;
    }

    private class ListProductAdapter extends ArrayAdapter<ListItem> {

        public ListProductAdapter(Context context, int textViewResourceId,
                List<ListItem> objects) {
            super(context, textViewResourceId, objects);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list_edit_row, null);
            }

            ListItem product = getItem(position);

            TextView text = (TextView) v.findViewById(R.id.editProductList);
            text.setText(product.getProduct().getName());

            return v;

        }

    }

}
