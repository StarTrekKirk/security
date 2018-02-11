package feihu.audition.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import feihu.audition.entity.Permission;

public class PermissionConst {

	private final static Map<String, Integer> permissionOperates;

	static {
		permissionOperates = new HashMap<String, Integer>(10);
		permissionOperates.put("p_authorization", Permission.P_AUTHORIZATION);
		permissionOperates.put("p_add_user", Permission.P_ADD_USER);
		permissionOperates.put("p_remove_user", Permission.P_REMOVE_USER);
		permissionOperates.put("p_update_user", Permission.P_UPDATE_USER);
		permissionOperates.put("p_query_user", Permission.P_QUERY_USER);
		permissionOperates.put("p_add_role", Permission.P_ADD_ROLE);
		permissionOperates.put("p_remove_role", Permission.P_REMOVE_ROLE);
		permissionOperates.put("p_update_role", Permission.P_UPDATE_ROLE);
		permissionOperates.put("p_query_role", Permission.P_QUERY_ROLE);
	}

	public static Map<String, Integer> getPermissionOperates() {
		return permissionOperates;
	}

	public static String[] getgetPermissionOperatesOptions() {
		List<String> options = new ArrayList<String>(permissionOperates.size());
		for (Map.Entry<String, Integer> operate : permissionOperates.entrySet()) {
			options.add(operate.getKey() + ":" + operate.getValue());
		}
		return options.toArray(new String[] {});
	}
}
