package feihu.security.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限接口
 * @author heihuhu
 * @createdate 2018年2月12日
 */
public class Permission {

	public static int P_AUTHORIZATION = 0x1;

	public static int P_ADD_USER = 0x2;

	public static int P_REMOVE_USER = 0x4;

	public static int P_UPDATE_USER = 0x8;

	public static int P_QUERY_USER = 0x10;

	public static int P_ADD_ROLE = 0x20;

	public static int P_REMOVE_ROLE = 0x40;

	public static int P_UPDATE_ROLE = 0x80;

	public static int P_QUERY_ROLE = 0x100;

	public static int P_QUERY_PERMISSION = 0x200;

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
