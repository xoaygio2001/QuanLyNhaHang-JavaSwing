/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import database.JDBCConnection;
import java.util.ArrayList;
import modal.Account;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modal.BookingTable;

/**
 *
 * @author DELL
 */
public class AccountDAO implements DAOInterface<Account> {

    public static AccountDAO getInstance() {
        return new AccountDAO();
    }

    @Override
    public int insert(Account t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Account t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Account> selectAll() {
        ArrayList<Account> accounts = new ArrayList<>();
        try {

            Connection con = JDBCConnection.getConnect();

            String query = "SELECT * FROM account";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setName(rs.getString("name"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setRole(rs.getString("role"));
                accounts.add(account);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accounts;
    }

    @Override
    public Account selectById(int id) {
        Account data = new Account();

        try {

            Connection con = JDBCConnection.getConnect();

            String query = "SELECT * FROM account where id = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setName(rs.getString("name"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setRole(rs.getString("role"));

//                System.out.println(bookingTable.toString());
                data = account;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }

    public static Account login(Account a) {
        Account data = new Account();

        try {

            Connection con = JDBCConnection.getConnect();

            String query = "SELECT * FROM account where username = ? AND password = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setString(1, a.getUsername());
            preparedStatement.setString(2, a.getPassword());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setName(rs.getString("name"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setRole(rs.getString("role"));

//                System.out.println(bookingTable.toString());
                data = account;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;

    }

}
