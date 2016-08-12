package com.myb.portal.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class IteratorUtils {
	public static <T> List<T> asList(Iterable<T> it){
		List<T> list = new ArrayList<T>();
		Iterator<T> itImpl = it.iterator();
		while(itImpl.hasNext()) {
			list.add(itImpl.next());
		}
		return list;
	}
}
