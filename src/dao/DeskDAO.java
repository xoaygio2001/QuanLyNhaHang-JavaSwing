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
import modal.BookingTable;
import modal.Desk;

/**
 *
 * @author DELL
 */
public class DeskDAO implements DAOInterface<Desk> {

    public static DeskDAO getInstance() {
        return new DeskDAO();
    }

    @Override
    public int insert(Desk t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Desk t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Desk> selectAll() {
        ArrayList<Desk> desks = new ArrayList<>();
        try {

            Connection con = JDBCConnection.getConnect();

            String query = "SELECT * FROM desk";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Desk desk = new Desk();
                desk.setId(rs.getInt("id"));
                desk.setName(rs.getString("name"));
                desks.add(desk);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DeskDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return desks;
    }

    @Override
    public Desk selectById(int id) {
        Desk data = new Desk();

        try {

            Connection con = JDBCConnection.getConnect();

            String query = "SELECT * FROM desk where id = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Desk item = new Desk();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));

//                System.out.println(bookingTable.toString());
                data = item;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DeskDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;

    }

    public ArrayList<Desk> SelectEmptyTables() {
        ArrayList<Desk> desks = new ArrayList<>();
        try {

            Connection con = JDBCConnection.getConnect();

            String query = "SELECT * FROM desk WHERE id NOT IN "
                    + "(SELECT tableId FROM bookingtable WHERE statusId = 'BOOKED' OR statusId = 'PAID')";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Desk desk = new Desk();
                desk.setId(rs.getInt("id"));
                desk.setName(rs.getString("name"));
                desks.add(desk);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DeskDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return desks;
    }

    public Desk sellectByName(String name) {
        Desk data = new Desk();

        try {

            Connection con = JDBCConnection.getConnect();

            String query = "SELECT * FROM desk WHERE name = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setString(1, name);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Desk item = new Desk();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                data = item;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DeskDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }

}
