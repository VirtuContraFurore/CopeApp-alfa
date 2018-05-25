package com.copeapp.utilities;

import java.util.ArrayList;
import java.util.List;

import com.copeapp.entities.common.Role;

public class MiscUtilities {
	
	public static boolean checkRoles(List<Role> roles0, List<Role> roles1) {
		for (Role r : roles0) {
			if (r.getRole().equalsIgnoreCase("admin")) {
				return true;
			}
		}

		ArrayList<Role> commonRole = new ArrayList<Role>(roles0);
		commonRole.retainAll(roles1);
		if (!commonRole.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static boolean checkRoles(List<Role> roles) {
		for (Role r : roles) {
			if (r.getRole().equalsIgnoreCase("amministratore")) {
				return true;
			}
		}
		return false;
	}
	
}