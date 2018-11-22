package feihu.security.component;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import feihu.security.dao.entity.Account;
import feihu.security.dao.entity.Permission;
import feihu.security.dao.entity.Role;
import feihu.security.service.AccountService;
import feihu.security.service.AuthService;

/**
 * Login是一个session bean，在controller中通过注入该对象来获取登录用户信息
 * @author heihuhu
 * @createdate 2018年2月12日
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Login {

	public static final int login_status_success = 0;

	public static final int login_status_disabled = 1;

	public static final int login_status_accountdonotexist = 2;

	public static final int login_status_incorrectpassword = 3;

	@Autowired
	private AccountService aService;

	@Autowired
	private AuthService authService;

	/**
	 * 不考虑并发问题
	 */
	private String user;

	public boolean isLogin() {
		return user != null;
	}

	public int login(String user, String password) {
		if (this.user != null) {
			return login_status_success;
		}
		Account account = aService.queryAccount(user);
		if (account == null) {
			return login_status_accountdonotexist;
		}
		if (account.getPassword().equals(password)) {
			if (account.isEnable()) {
				this.user = user;
				return login_status_success;
			}
			return login_status_disabled;
		}
		return login_status_incorrectpassword;
	}

	public Account getUser() {
		if (user == null) {
			return null;
		}
		return aService.queryAccount(user);
	}

	public boolean isRole(String role) {
		if (user == null) {
			return false;
		}
		return authService.isRole(user, role);
	}

	public boolean isAdmin() {
		if (user == null) {
			return false;
		}
		return authService.isAdmin(user);
	}

	public int getPermission() {
		if (user == null) {
			return 0;
		}
		return authService.getPermission(user);
	}

	public boolean hasPermission(int permission) {
		if (user == null) {
			return false;
		}
		return authService.hasPermission_user(user, permission);
	}

	public boolean checkPermission(Permission permission) {
		return checkPermission(permission, true);
	}

	public boolean checkPermission(Permission permission, boolean isThrow) {
		boolean check = hasPermission(permission.getValue());
		if (isThrow) {
			if (!check)
				throw new RuntimeException("用户[" + user + "]沒有" + permission.getKey() + "权限");
			return check;
		}
		else {
			return check;
		}
	}

	public List<Role> getRoles() {
		if (user == null) {
			return Collections.emptyList();
		}
		return authService.getRoles(user);
	}
}
