package co.interlo.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lists {

	public static <T> List<T> newArrayList(){
		return new ArrayList<T>();
	}
	
	
	public static <T> List<T> newArrayList(List<T> items){
		if(items == null){
			return newArrayList();
		}
		return new ArrayList<T>(items);
	}
	
	public static <T> List<T> newArrayList(T[] items){
		if(items == null || items.length == 0){
			return newArrayList();
		}
		return new ArrayList<T>(Arrays.asList(items));
		
	}
}
