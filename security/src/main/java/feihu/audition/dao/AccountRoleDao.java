package feihu.audition.dao;

import java.util.List;
import java.util.Map;

import feihu.audition.entity.Account;
import feihu.audition.entity.Role;

public interface AccountRoleDao {

	public List<Role> queryRoles(Account account);

	public List<Integer> queryPermissions(Account account);

	public int addAccountRole(List<Map<String, Integer>> accountRoles);

	public int deleteAccountRole(int id);

	public int deleteAccountRoles(List<Integer> ids);
}