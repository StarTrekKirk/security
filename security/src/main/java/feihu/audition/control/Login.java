package feihu.audition.control;

import java.util.List;

import feihu.audition.entity.Account;
import feihu.audition.entity.Role;

public interface Login {

	public static final int login_status_success = 0;

	public static final int login_status_disabled = 1;

	public static final int login_status_accountdonotexist = 2;

	public static final int login_status_incorrectpassword = 3;

	public abstract boolean isLogin();

	public abstract int login(String user, String password);

	public abstract Account getUser();

	public abstract boolean isRole(String role);

	public abstract boolean isAdmin();

	public abstract int getPermission(String user);

	public abstract List<Role> getRoles();

}