package com.codegym.dao;

import com.codegym.model.Account;

import java.util.List;

public interface IAccountDao {
    Account findByLoginName(String loginName);
    void addNewAccount(Account account);
    String getMaxAccountID();
    public List<Account> viewAllAccount();
    Account findById(String accountId);
    public void updateAccountById(Account account);
    public void deleteAccountById(String accountId);
}
