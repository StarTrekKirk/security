package feihu.audition.entity;

import java.io.Serializable;

public class Permission implements Serializable {

	private static final long serialVersionUID = 1L;

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

}
