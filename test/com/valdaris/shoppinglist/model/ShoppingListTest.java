package com.valdaris.shoppinglist.model;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.valdaris.shoppinglist.exception.InvalidValueException;
import com.valdaris.shoppinglist.exception.RepeatedProductException;

/**
 * Testing on ShoppingList objects.
 * 
 * @author Javier Estévez
 *
 */
public class ShoppingListTest {
	
	private final static String POTATOES = "Potatoes";
	private final static String KG = "Kg";
	private final static String AMOUNT = "5";
	private final static Double AMOUNT_DOUB = 5.0;
	private final static String RICE = "Rice";
	private final static String APPLES = "Apples";
	
	private ListItem item;
	private ShoppingList list;
	
	@Before
	public void setup() {
		
		//valid ListItem for comparing results 
		item = new ListItem();
		Product p = new Product();
		p.setName(POTATOES);
		item.setProduct(p);
		item.setAmount(AMOUNT_DOUB);
		item.setUnit(KG);
		item.setBought(false);
		
		//list to test on
		list = new ShoppingList();
	}
	
	@Test
	public void addListItemTest() {
		
		list.addListItem(POTATOES, AMOUNT, KG);
		
		Assert.assertTrue(list.getListItems().contains(item));
		
	}
	
	@Test
	public void addListItemLastInsertion() {
		
		list.addListItem(POTATOES, AMOUNT, KG);
		
		Assert.assertEquals(item, list.getLastInsertedItem());
		
	}
	
	@Test(expected=InvalidValueException.class)
	public void addListItemTestInvalidAmountThrowsException() {
		
		list.addListItem(POTATOES, "FDSFDS", KG);
		
	}
	
	@Test
	public void addListItemSetsListIncomplete() {
		
		list.addListItem(POTATOES, AMOUNT, KG);
		
		Assert.assertEquals(ShoppingList.INCOMPLETE, list.getStatus());
		
	}
	
	@Test
	public void getListItemByProductName() {
		
		list.addListItem(RICE, AMOUNT, KG);
		list.addListItem(POTATOES, AMOUNT, KG);
		list.addListItem(APPLES, AMOUNT, KG);
		
		Assert.assertEquals(item, list.getListItemByProductName(POTATOES));
		
	}
	
	@Test
	public void getListItemByProductNameNoPresentRegutrnsNull() {
		
		list.addListItem(RICE, AMOUNT, KG);
		list.addListItem(APPLES, AMOUNT, KG);
		
		Assert.assertNull(list.getListItemByProductName(POTATOES));
		
	}
	
	@Test(expected=RepeatedProductException.class)
	public void addRepeatedListItemThrowsException() {
		
		list.addListItem(POTATOES, AMOUNT, KG);
		list.addListItem(POTATOES, "33", "Units");
		
	}
	
	@Test
	public void addListItemsList() {
		ArrayList<ListItem> items = new ArrayList<ListItem>();
		items.add(item);
		list.setListItems(items);
		
		Assert.assertTrue(list.getListItems().contains(item));
	}

}
