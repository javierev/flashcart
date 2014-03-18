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
package com.valdaris.shoppinglist.model;

import java.io.Serializable;

import com.valdaris.shoppinglist.util.ObjectUtil;

/**
 * @author Javier Estévez
 * 
 */
public class Product implements Serializable {

    private static final long serialVersionUID = -1935911318526069618L;

    public static final String TABLE_NAME = "product";
    
    public static final String ID_FIELD_NAME = "id";
    public static final String NAME_FIELD_NAME = "name";

    private Integer id;

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

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Product)) return false;
		Product other = (Product) o;
		if (!ObjectUtil.equals(this.id, other.id)) return false;
		if (!ObjectUtil.equals(this.name, other.name)) return false;
		
		return true;
	}

	@Override
	public int hashCode() {
		return (id != null ? id.hashCode() : 1) *
				(name != null ? name.hashCode() : 1);
	}
    
    

}
