package com.copeapp.utilities;

import java.lang.reflect.Field;

import com.sun.istack.internal.NotNull;

public interface ObjectsValidationUtility {

	public static boolean validateNotNullParameters (Object object) throws IllegalArgumentException, IllegalAccessException {
		
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(NotNull.class)) {
				if (field.get(object) == null) {
					return false;
				}
			}
		}
		return true;
	}
}
