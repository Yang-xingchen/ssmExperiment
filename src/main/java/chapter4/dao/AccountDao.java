package chapter4.dao;

import chapter4.model.Account;

import java.util.List;

public interface AccountDao {

    public int addAccount(Account account);

    public int updateAccount(Account account);

    public int deleteAccount(Account account);

    public Account findOne(long id);

    public List<Account> findAll();

}