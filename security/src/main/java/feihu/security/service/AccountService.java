package feihu.security.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import feihu.security.dao.AccountDao;
import feihu.security.dao.AccountRoleDao;
import feihu.security.dao.entity.Account;
import feihu.security.dao.entity.Role;

/**
 * 账户服务类，提供账户增删改查功能
 * @author heihuhu
 * @createdate 2018年2月12日
 */
@Service
public class AccountService {

	@Autowired
	private AccountDao aDao;

	@Autowired
	private AccountRoleDao ardao;

	public Account queryAccount(int id) {
		return aDao.queryAccountById(id);
	}

	public Account queryAccount(String name) {
		return aDao.queryAccount(name);
	}

	private List<Map<String, Integer>> getAccountRoles(Account account, List<Integer> roles) {
		List<Map<String, Integer>> accountRoles = new ArrayList<Map<String, Integer>>(roles.size());
		for (Integer role : roles) {
			HashMap<String, Integer> accountRole = new HashMap<String, Integer>(2);
			accountRole.put("aid", account.getId());
			accountRole.put("rid", role);
			accountRoles.add(accountRole);
		}
		return accountRoles;
	}

	@Transactional(rollbackFor = Throwable.class)
	public int addAccount(Account account, List<Integer> roles) {
		int cnt = aDao.addAccount(account);
		ardao.addAccountRole(getAccountRoles(account, roles));
		return cnt;
	}

	@Transactional(rollbackFor = Throwable.class)
	public int deleteAccount(List<Integer> ids) {
		int cnt = 0;
		if (ids.size() == 1) {
			int id = ids.get(0);
			cnt = aDao.deleteAccount(id);
			ardao.deleteAccountRole(id);
		} else {
			cnt = aDao.deleteAccounts(ids);
			ardao.deleteAccountRoles(ids);
		}
		return cnt;
	}

	@Transactional(rollbackFor = Throwable.class)
	public int updateAccount(Account account, List<Integer> roles) {
		int cnt = aDao.updateAccount(account);
		ardao.deleteAccountRole(account.getId());
		ardao.addAccountRole(getAccountRoles(account, roles));
		return cnt;
	}

	@Transactional(readOnly = true)
	public List<Account> queryAccounts() {
		return aDao.queryAccounts();
	}

	@Transactional(readOnly = true)
	public List<Role> queryRoles(Account account) {
		return ardao.queryRoles(account);
	}
}
