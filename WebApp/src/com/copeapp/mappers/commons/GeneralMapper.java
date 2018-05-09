package com.copeapp.mappers.commons;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneralMapper {
	
	public static Object convert(Object input, Class<?> output) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		List<Constructor<?>> constructor = Arrays.asList(output.getConstructors());
		List<Field> inputFields = Arrays.asList(input.getClass().getDeclaredFields());
		List<Field> outputFields = Arrays.asList(output.getDeclaredFields());
		Object valueStore = new Object();
		for (Field f : inputFields) {
			f.setAccessible(true);
			if (f.get(valueStore) == null) { //TODO capire come funziona sto pezzo dimmerda
				inputFields.remove(f);
			}
		}
		for (Constructor<?> c : constructor) {
			if (c.getParameterTypes().length != inputFields.size()) {
				constructor.remove(c);
			}
			if (!constructor.containsAll(inputFields)) {
				constructor.remove(c);
			};
			if (constructor.isEmpty() || constructor.size() >= 2) {
				throw new NullPointerException("Sei un cretino");
			}
		}
		ArrayList<Object> args = new ArrayList<Object>();
		inputFields.get(0).getName();
		constructor.get(0).getParameters()[0].getName();
		for (Parameter p : constructor.get(0).getParameters()) {
			for (Field f : inputFields) {
				f.setAccessible(true);
				if (f.getName().equals(p.getName())) {
					args.add(f.get(valueStore));
					inputFields.remove(f);
				}
			}
		}
		
		Object outputObject = constructor.get(0).newInstance(args.toArray());
		return outputObject;
		
	}
	
}
