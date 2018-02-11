package feihu.audition.dao;

import java.util.List;

import feihu.audition.entity.Account;

public interface AccountDao {

	public Account queryAccount(String name);

	public Account queryAccountById(int id);

	public int addAccount(Account account);

	public int deleteAccount(int id);
	
	public int deleteAccounts(List<Integer> ids);

	public int updateAccount(Account account);

	public List<Account> queryAccounts();

}