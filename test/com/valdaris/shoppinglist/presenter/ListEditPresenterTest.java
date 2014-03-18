package com.valdaris.shoppinglist.presenter;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.valdaris.shoppinglist.dao.ListItemDao;
import com.valdaris.shoppinglist.dao.ListItemDaoTest;
import com.valdaris.shoppinglist.model.ListItem;
import com.valdaris.shoppinglist.model.Product;
import com.valdaris.shoppinglist.model.ShoppingList;

public class ListEditPresenterTest {
	
	ListEditPresenter presenter;
	
	@Before
	public void setup() {
		ShoppingList list = new ShoppingList();
		ListItemDao dao = new ListItemDaoTest();
		presenter = new ListEditPresenter(list, dao);
	}
	
	
	@Test
	public void addItemTest() {
		String itemName = "Patatas";
		String amount = "5";
		String unit = "Kg";
		presenter.addProduct(itemName, amount, unit);
		
		ListItem item = new ListItem();
		item.setAmount(5.0);
		item.setUnit(unit);
		Product p = new Product();
		p.setName(itemName);
		item.setProduct(p);
		item.setBought(false);
		
		Assert.assertEquals(item, presenter.getList().getLastInsertedItem());
	}
	
//	@Test
//	public void addItem

}
