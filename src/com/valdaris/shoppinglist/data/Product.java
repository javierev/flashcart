package com.valdaris.shoppinglist.data;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 
 * @author Javier Estévez
 *
 */
@DatabaseTable
public class Product implements Serializable {

	private static final long serialVersionUID = -1935911318526069618L;
	
	public static final String NAME_FIELD_NAME = "name";
	
	@DatabaseField(generatedId = true)
	private Integer id;
	
	@DatabaseField(columnName = NAME_FIELD_NAME)
	private String name;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	
	
}
