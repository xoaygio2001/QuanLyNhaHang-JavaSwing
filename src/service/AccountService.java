/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.AccountDAO;
import java.util.ArrayList;
import modal.Account;

/**
 *
 * @author DELL
 */
public class AccountService {
   
    public static ArrayList getAllAccounts() {
        return AccountDAO.getInstance().selectAll();
    
    }
    
    public static  Account getAccountById(int id) {
        return AccountDAO.getInstance().selectById(id);
    }
    
    public static Account login(Account a) {
        return AccountDAO.getInstance().login(a);
    }
}
