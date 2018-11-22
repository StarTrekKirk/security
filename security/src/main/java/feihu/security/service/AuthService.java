package feihu.security.service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import feihu.security.dao.AccountRoleDao;
import feihu.security.dao.entity.Account;
import feihu.security.dao.entity.Role;

/**
 * 权限验证服务
 * @author heihuhu
 * @createdate 2018年2月12日
 */
@Service
public class AuthService {

	@Autowired
	private AccountService accountService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private AccountRoleDao dao;

	public boolean isAdmin(String user) {
		return isRole(user, "admin");
	}

	public boolean isRole(String user, String role) {
		if (role == null) {
			return false;
		}
		Account account = accountService.queryAccount(user);
		List<Role> roles = dao.queryRoles(account);
		for (Role role2 : roles) {
			if (role.equalsIgnoreCase(role2.getName())) {
				return true;
			}
		}
		return false;
	}

	public List<Role> getRoles(String user) {
		if (user == null) {
			return Collections.emptyList();
		}
		Account account = accountService.queryAccount(user);
		if (account == null) {
			return Collections.emptyList();
		}
		return dao.queryRoles(account);
	}

	public int getPermission(String user) {
		if (user == null) {
			return 0;
		}
		Account account = accountService.queryAccount(user);
		if (account == null) {
			return 0;
		}
		List<Integer> permissions = dao.queryPermissions(account);
		if (permissions == null || permissions.isEmpty()) {
			return 0;
		}
		Iterator<Integer> iterator = permissions.iterator();
		int permission2 = iterator.next();
		while (iterator.hasNext()) {
			permission2 = permission2 & iterator.next();
		}
		return permission2;
	}

	public boolean hasPermission_role(String role, int perimssion) {
		if (role == null) {
			return false;
		}
		Role role2 = roleService.queryRole(role);
		if (role2 == null) {
			return false;
		}
		int permission2 = role2.getPermission();
		return (permission2 & perimssion) == perimssion;
	}

	public boolean hasPermission_user(String user, int permission) {
		int permission2 = getPermission(user);
		return (permission2 & permission) == permission;
	}

}
