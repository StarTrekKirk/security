package feihu.audition.control;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import feihu.audition.entity.Account;
import feihu.audition.entity.Role;
import feihu.audition.service.AccountService;
import feihu.audition.service.AuthService;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginImpl implements Login {

	@Autowired
	private AccountService aService;

	@Autowired
	private AuthService authService;

	/**
	 * 不考虑并发问题
	 */
	private String user;

	/* (non-Javadoc)
	 * @see feihu.audition.service.Login#isLogin()
	 */
	@Override
	public boolean isLogin() {
		return user != null;
	}

	/* (non-Javadoc)
	 * @see feihu.audition.service.Login#login(java.lang.String, java.lang.String)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see feihu.audition.service.Login#getUser()
	 */
	@Override
	public Account getUser() {
		if (user == null) {
			return null;
		}
		return aService.queryAccount(user);
	}

	/* (non-Javadoc)
	 * @see feihu.audition.service.Login#isRole(java.lang.String)
	 */
	@Override
	public boolean isRole(String role) {
		if (user == null) {
			return false;
		}
		return authService.isRole(user, role);
	}

	/* (non-Javadoc)
	 * @see feihu.audition.service.Login#isAdmin()
	 */
	@Override
	public boolean isAdmin() {
		if (user == null) {
			return false;
		}
		return authService.isAdmin(user);
	}

	/* (non-Javadoc)
	 * @see feihu.audition.service.Login#getPermission(java.lang.String)
	 */
	@Override
	public int getPermission(String user) {
		if (user == null) {
			return 0;
		}
		return authService.getPermission(user);
	}

	/* (non-Javadoc)
	 * @see feihu.audition.service.Login#getRoles()
	 */
	@Override
	public List<Role> getRoles() {
		if (user == null) {
			return Collections.emptyList();
		}
		return authService.getRoles(user);
	}
}
