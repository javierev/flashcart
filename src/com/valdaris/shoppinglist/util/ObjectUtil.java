package com.valdaris.shoppinglist.util;

public class ObjectUtil {
	
    public static boolean equals(Object o1, Object o2) {
    	if (o1 != null) {
    		return o1.equals(o2);
    	} else {
    		return o2 == null;
    	}
    }

}
