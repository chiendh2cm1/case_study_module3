package com.codegym.dao;


import com.codegym.connection.DBConnect;
import com.codegym.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao implements IAccountDao {
    public static final String FIND_ACCOUNT_BY_LOGIN_NAME = "select * from chiendemo.account where LoginName=?;";
    public static final String FIND_MAX_ACCOUNT_ID = "select * from chiendemo.account where AccountID like 'KH%' order by AccountID desc limit 1;";
    public static final String ADD_NEW_ACCOUNT = "insert into chiendemo.account values (?,?,?,?,?,?,?,?,?)";
    public static final String SELECT_FROM_ACCOUNT = "select * from chiendemo.account;";
    public static final String SELECT_BY_ID = "select * from chiendemo.account where AccountID = ?;";
    public static final String UPDATE_ACCOUNT_BY_ID = "update chiendemo.account set AccountName = ?, LoginName=?,Password=?,AccountAccess=?,Address=?,PhoneNumber=?,Gender=?,Status=? where AccountID = ?;";
    public static final String DELETE_ACCOUNT_BY_ID = "delete from atagvn.account where AccountID = ?";
    @Override
    public Account findByLoginName(String loginName) {
        Connection connection = DBConnect.getConnection();
        Account account = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ACCOUNT_BY_LOGIN_NAME);
            preparedStatement.setString(1, loginName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String accountId = resultSet.getString("AccountID");
                String accountName = resultSet.getString("AccountName");
                String password = resultSet.getString("Password");
                String accountAccess = resultSet.getString("AccountAccess");
                String address = resultSet.getString("Address");
                String phoneNumber = resultSet.getString("PhoneNumber");
                boolean gender = Boolean.parseBoolean(resultSet.getString("Gender"));
                boolean status = Boolean.parseBoolean(resultSet.getString("Status"));

                account = new Account(accountId, accountName, loginName, password,accountAccess, address, phoneNumber, gender, status);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return account;
    }

    @Override
    public void addNewAccount(Account account) {
        Connection connection = DBConnect.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_ACCOUNT);
            preparedStatement.setString(1, account.getAccountId());
            preparedStatement.setString(2, account.getAccountName());
            preparedStatement.setString(3, account.getLoginName());
            preparedStatement.setString(4, account.getPassword());
            preparedStatement.setString(5, account.getAccountAccess());
            preparedStatement.setString(6, account.getAddress());
            preparedStatement.setString(7, account.getPhoneNumber());
            preparedStatement.setBoolean(8, account.isGender());
            preparedStatement.setBoolean(9, account.isStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getMaxAccountID() {
        Connection connection = DBConnect.getConnection();
        String maxId = "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_MAX_ACCOUNT_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                maxId = resultSet.getString("AccountID");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return maxId;
    }

    private List<Account> accounts;

    @Override
    public List<Account> viewAllAccount() {
        accounts = new ArrayList<>();
        String accountID = "";
        String accountName = "";
        String loginName = "";
        String password = "";
        String accountAccess = "";
        String address = "";
        String phoneNumber = "";
        boolean gender = false;
        boolean status = false;
        Connection connection = DBConnect.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_FROM_ACCOUNT);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                accountID = rs.getString(1);
                accountName = rs.getString(2);
                loginName = rs.getString(3);
                password = rs.getString(4);
                accountAccess = rs.getString(5);
                address = rs.getString(6);
                phoneNumber = rs.getString(7);
                gender = rs.getBoolean(8);
                status = rs.getBoolean(9);
                accounts.add(new Account(accountID, accountName, loginName, password, accountAccess, address, phoneNumber, gender, status));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Account findById(String input_accountId) {
        Connection connection = DBConnect.getConnection();
        Account account = null;
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setString(1, input_accountId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String accountId = resultSet.getString("AccountID");
                String accountName = resultSet.getString("AccountName");
                String loginName = resultSet.getString("LoginName");
                String password = resultSet.getString("Password");
                String accountAccess = resultSet.getString("AccountAccess");
                String address = resultSet.getString("Address");
                String phoneNumber = resultSet.getString("PhoneNumber");
                boolean gender = Boolean.parseBoolean(resultSet.getString("Gender"));
                boolean status = Boolean.parseBoolean(resultSet.getString("Status"));

                account = new Account(accountId, accountName, loginName, password, accountAccess, address, phoneNumber, gender, status);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return account;
    }

    @Override
    public void updateAccountById(Account account) {
        Connection connection = DBConnect.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE_ACCOUNT_BY_ID);
            ps.setString(1, account.getAccountName());
            ps.setString(2, account.getLoginName());
            ps.setString(3, account.getPassword());
            ps.setString(4, account.getAccountAccess());
            ps.setString(5, account.getAddress());
            ps.setString(6, account.getPhoneNumber());
            ps.setBoolean(7, account.isGender());
            ps.setBoolean(8, account.isStatus());
            ps.setString(9, account.getAccountId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Override
    public void deleteAccountById(String accountId) {
        Connection conn = DBConnect.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(DELETE_ACCOUNT_BY_ID);
            ps.setString(1,accountId);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
