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
import modal.Food;

/**
 *
 * @author DELL
 */
public class FoodDAO implements DAOInterface<Food> {

    public static FoodDAO getInstance() {
        return new FoodDAO();
    }

    @Override
    public int insert(Food t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Food t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Food> selectAll() {
        ArrayList<Food> data = new ArrayList<>();
        try {

            Connection con = JDBCConnection.getConnect();

            String query = "SELECT * FROM food";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Food item = new Food();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getInt("price"));
                item.setType(rs.getString("type"));

                data.add(item);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    @Override
    public Food selectById(int id) {
        Food data = new Food();

        try {

            Connection con = JDBCConnection.getConnect();

            String query = "SELECT * FROM food where id = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Food item = new Food();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getInt("price"));
                item.setType(rs.getString("type"));

//                System.out.println(bookingTable.toString());
                data = item;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }

    public ArrayList<Food> selectOrderedFoodByBookingTableId(int bookingTableId) {

        ArrayList<Food> data = new ArrayList<>();

        try {

            Connection con = JDBCConnection.getConnect();

            String query = "SELECT bookingfood.foodId as 'id', food.name,food.price,food.type, "
                    + "COUNT(bookingfood.foodId) as 'quantity', SUM(food.price) as 'total' "
                    + "FROM bookingfood, food,bookingTable "
                    + "WHERE bookingfood.foodId = food.id and "
                    + "bookingTable.id = bookingfood.id and "
                    + "bookingTable.statusId = 'BOOKED' and "
                    + "bookingfood.bookingTableId = ? "
                    + "GROUP BY bookingfood.foodId";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setInt(1, bookingTableId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Food item = new Food();

                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getInt("price"));
                item.setType(rs.getString("type"));
                item.setQuantity(rs.getInt("quantity"));
                item.setTotal(rs.getInt("total"));

//                System.out.println(bookingTable.toString());
                data.add(item);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;

    }

    public Food selectFoodByName(String name) {
        Food data = new Food();

        try {

            Connection con = JDBCConnection.getConnect();

            String query = "SELECT * FROM food where name = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setString(1, name);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Food item = new Food();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getInt("price"));
                item.setType(rs.getString("type"));

//                System.out.println(bookingTable.toString());
                data = item;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;

    }
}
