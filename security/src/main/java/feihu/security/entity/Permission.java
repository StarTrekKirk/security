package feihu.security.entity;

/**
 * 权限列表
 * @author heihuhu
 * @createdate 2018年2月12日
 */
public enum Permission {
	P_AUTHORIZATION(0x1, "赋权"), P_ADD_USER(0x2, "新增用户"), P_REMOVE_USER(0x4, "删除用户"), P_UPDATE_USER(0x8, "更新用户"), P_QUERY_USER(
			0x10, "查询用户"), P_ADD_ROLE(0x20, "新增角色"), P_REMOVE_ROLE(0x40, "删除角色"), P_UPDATE_ROLE(0x80, "更新角色"), P_QUERY_ROLE(
			0x100, "查询角色"), P_QUERY_PERMISSION(0x200, "查询权限"), P_LOGIN(0, "登录");

	public static Permission getPermission(int key) {
		switch (key) {
			case 0x1:
				return P_AUTHORIZATION;
			case 0x2:
				return P_ADD_USER;
			case 0x4:
				return P_REMOVE_USER;
			case 0x8:
				return P_UPDATE_USER;
			case 0x10:
				return P_QUERY_USER;
			case 0x20:
				return P_ADD_ROLE;
			case 0x40:
				return P_REMOVE_ROLE;
			case 0x80:
				return P_UPDATE_ROLE;
			case 0x100:
				return P_QUERY_ROLE;
			case 0x200:
				return P_QUERY_PERMISSION;
			default:
		}
		return P_LOGIN;
	}

	private int value;

	private String key;

	private Permission(int value, String key) {
		this.value = value;
		this.key = key;
	}

	public int getValue() {
		return value;
	}

	public String getKey() {
		return key;
	}
}
