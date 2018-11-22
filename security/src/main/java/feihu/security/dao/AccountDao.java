package feihu.security.dao;

import java.util.List;

import feihu.security.dao.entity.Account;
import org.springframework.stereotype.Repository;

/**
 * 账户Dao接口
 * @author heihuhu
 * @createdate 2018年2月12日
 */
@Repository
public interface AccountDao {

	public Account queryAccount(String name);

	public Account queryAccountById(int id);

	public int addAccount(Account account);

	public int deleteAccount(int id);
	
	public int deleteAccounts(List<Integer> ids);

	public int updateAccount(Account account);

	public List<Account> queryAccounts();

}