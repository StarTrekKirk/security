package feihu.security.dao;

import java.util.List;
import java.util.Map;

import feihu.security.entity.Account;
import feihu.security.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * 账户角色对应接口
 * @author heihuhu
 * @createdate 2018年2月12日
 */
@Repository
public interface AccountRoleDao {

	public List<Role> queryRoles(Account account);

	public List<Integer> queryPermissions(Account account);

	public int addAccountRole(List<Map<String, Integer>> accountRoles);

	public int deleteAccountRole(int id);

	public int deleteAccountRoles(List<Integer> ids);
}