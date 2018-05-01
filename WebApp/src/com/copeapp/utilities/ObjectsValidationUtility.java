package com.copeapp.utilities;

import java.lang.reflect.Field;

import com.copeapp.exception.LoginFailedException;

import lombok.NonNull;

public interface ObjectsValidationUtility {

	public static boolean validateNotNullParameters (Object object) throws LoginFailedException {
		boolean b = true;
		Field[] declaredFields = object.getClass().getDeclaredFields();
		for(Field field : declaredFields) {
			field.setAccessible(true);
			if(field.isAnnotationPresent(NonNull.class)) {
				try {
					if (field.get(object) == null) {
						b= false;
						throw new LoginFailedException(HttpStatusUtility.BAD_REQUEST, "Alcuni dei parametri obbligatori sono vuoti");
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					b= false;
					e.printStackTrace();
					throw new LoginFailedException(HttpStatusUtility.INTERNAL_SERVER_ERROR, "Errore interno al server", e);
				}
			}
		}
		return b;
	}
}
