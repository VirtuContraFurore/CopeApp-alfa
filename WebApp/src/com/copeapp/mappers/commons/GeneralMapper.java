package com.copeapp.mappers.commons;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class GeneralMapper {
	
	public static Object convert(Object input, Class<?> output, HashMap<String, String> bindings) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		ArrayList<Field> inputFields = new ArrayList<Field>(Arrays.asList(input.getClass().getDeclaredFields()));
		ArrayList<Field> outputFields = new ArrayList<Field>(Arrays.asList(output.getDeclaredFields()));
		HashMap<String, Object> args = new HashMap<String, Object>();
		
		for (Field oF : outputFields) {
			oF.setAccessible(true);
			if (bindings.containsKey(oF.getName())) {
				for (Field iF : inputFields) {
					iF.setAccessible(true);
					if (iF.getName().equals(bindings.get(oF.getName()))) {
						args.put(oF.getName(),iF.get(input));
						inputFields.remove(iF);
						break;
					}
				}
			} else {
				for (Field iF : inputFields) {
					iF.setAccessible(true);
					if (iF.getName().equals(oF.getName())) {
						args.put(oF.getName(),iF.get(input));
						inputFields.remove(iF);
						break;
					}
				}
			}
		}
		Object outputObject = output.getConstructor().newInstance();
		BeanUtils.populate(outputObject, args);
		return outputObject;
	}
	
}
