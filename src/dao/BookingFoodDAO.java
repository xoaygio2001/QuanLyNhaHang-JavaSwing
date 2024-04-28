/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modal.BookingFood;
import modal.BookingTable;

/**
 *
 * @author DELL
 */
public class BookingFoodDAO implements DAOInterface<BookingFood> {

    public static BookingFoodDAO getInstance() {
        return new BookingFoodDAO();
    }

    @Override
    public int insert(BookingFood bookingFood) {
        int number = 0;
        try {

            Connection con = JDBCConnection.getConnect();

            String query = "INSERT INTO bookingFood (staffId, bookingTableId, foodId) "
                    + "VALUES (?,?,?)";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setInt(1, bookingFood.getStaffId());
            preparedStatement.setInt(2, bookingFood.getBookingTableId());
            preparedStatement.setInt(3, bookingFood.getFoodId());

            number = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BookingFoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return number;
    }

    @Override
    public int update(BookingFood t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<BookingFood> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BookingFood selectById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<BookingFood> selectBookingFoodByBookingTableId(int BookingTableId) {
        ArrayList<BookingFood> data = new ArrayList<>();

        try {

            Connection con = JDBCConnection.getConnect();

            String query = "SELECT * FROM bookingFood where bookingTableId = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setInt(1, BookingTableId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                BookingFood bookingFood = new BookingFood();
                bookingFood.setId(rs.getInt("id"));
                bookingFood.setBookingTableId(rs.getInt("bookingTableId"));
                bookingFood.setStaffId(rs.getInt("staffId"));
                bookingFood.setFoodId(rs.getInt("foodId"));
                bookingFood.setTime(rs.getDate("time"));

//                System.out.println(bookingTable.toString());
                data.add(bookingFood);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookingFoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }

}
