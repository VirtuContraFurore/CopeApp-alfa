package com.copeapp.utilities;

public interface ObjectsValidationUtility {

	public static boolean validateNotNullParameters (Object object) {
//		Field[] fields = object.getClass().getDeclaredFields();
//		for (Field field : fields) {
//			field.setAccessible(true);
//			if (field.isAnnotationPresent(NonNull.class)) {
//				if (field.get(object) == null) {
//					return false;
//				}
//			}
//		}
		return true;
	}
}
