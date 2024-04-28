/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modal.Bill;

/**
 *
 * @author DELL
 */
public class BillDAO implements DAOInterface<Bill> {
    
    public static BillDAO getInstance() {
        return new BillDAO();
    }

    @Override
    public int insert(Bill t) {
        int number = 0;
        try {

            Connection con = JDBCConnection.getConnect();

            String query = "INSERT INTO bill (bookingTableId, foodNumber, sum,discount,finalCost) "
                    + "VALUES (?,?,?,?,?)";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setInt(1, t.getBookingTableId());
            preparedStatement.setInt(2, t.getFoodNumber());
            preparedStatement.setInt(3, t.getSum());
            preparedStatement.setInt(4, t.getDiscount());
            preparedStatement.setInt(5, t.getFinalCost());

            number = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return number;
    }

    @Override
    public int update(Bill t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Bill> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Bill selectById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
