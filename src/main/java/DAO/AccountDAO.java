package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {

    public Account insertUser(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "INSERT INTO account (username, password) VALUES (?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) {
                int generated_account_id = (int) rs.getInt("account_id");
                return new Account(
                    generated_account_id,
                    account.getUsername(),
                    account.getPassword());
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account checkLogin (Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Account accounts = new Account(
                    rs.getInt("account_id"),
                    rs.getString("username"),
                    rs.getString("password"));
                    return accounts;
            }
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
