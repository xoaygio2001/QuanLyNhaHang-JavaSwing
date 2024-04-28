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
import modal.Account;
import modal.BookingTable;
import modal.Desk;
import service.DeskService;

/**
 *
 * @author DELL
 */
public class BookingTableDAO implements DAOInterface<BookingTable> {

    public static BookingTableDAO getInstance() {
        return new BookingTableDAO();
    }

    @Override
    public int insert(BookingTable b) {
        int number = 0;
        try {

            Connection con = JDBCConnection.getConnect();

            String query = "INSERT INTO bookingTable (tableId, staffId, statusId) "
                    + "VALUES (?,?,'BOOKED')";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setInt(1, b.getTableId());
            preparedStatement.setInt(2, b.getStaffId());

            number = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BookingTable.class.getName()).log(Level.SEVERE, null, ex);
        }

        return number;
    }

    @Override
    public int update(BookingTable t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<BookingTable> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public BookingTable selectById() {
        return new BookingTable();
    }

    public BookingTable selectByDeskId(int deskId) {
        BookingTable data = new BookingTable();

        try {

            Connection con = JDBCConnection.getConnect();

            String query = "SELECT * FROM bookingTable where tableId = ? AND statusId = 'BOOKED'";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setInt(1, deskId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                BookingTable bookingTable = new BookingTable();
                bookingTable.setId(rs.getInt("id"));
                bookingTable.setTableId(rs.getInt("tableId"));
                bookingTable.setStaffId(rs.getInt("staffId"));
                bookingTable.setStatusId(rs.getString("statusId"));
                bookingTable.setTimeIn(rs.getTime("timeIn"));
                bookingTable.setTimeOut(rs.getTime("timeOut"));
                bookingTable.setDate(rs.getDate("date"));

//                System.out.println(bookingTable.toString());
                data = bookingTable;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DeskDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }

    @Override
    public BookingTable selectById(int id) {
        BookingTable data = new BookingTable();

        try {

            Connection con = JDBCConnection.getConnect();

            String query = "SELECT * FROM bookingTable where id = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                BookingTable item = new BookingTable();
                item.setId(rs.getInt("id"));
                item.setTableId(rs.getInt("tableId"));
                item.setStaffId(rs.getInt("staffId"));
                item.setTimeIn(rs.getTime("timeIn"));
                item.setTimeOut(rs.getTime("timeOut"));
                item.setDate(rs.getDate("date"));

                data = item;
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookingTableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }

    public static int updateBookingTableByDeskName(String deskName, int oldDeskId) {
        int number = 0;
        try {

            Connection con = JDBCConnection.getConnect();

            int newDeskId = DeskService.getDeskByName(deskName).getId();

            String query = "UPDATE bookingTable SET tableId = ? WHERE tableId = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setInt(1, newDeskId);
            preparedStatement.setInt(2, oldDeskId);

            number = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BookingFoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return number;
    }

    public static int updateBookingTablePaidBill(int id) {
        int number = 0;
        try {

            Connection con = JDBCConnection.getConnect();

            String query = "UPDATE bookingTable SET statusId = 'PAID', timeOut = CURRENT_TIMESTAMP WHERE id = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setInt(1, id);

            number = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BookingTableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return number;
    }

}
