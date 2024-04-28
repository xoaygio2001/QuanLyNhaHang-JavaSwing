/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.FoodDAO;
import java.util.ArrayList;
import modal.Food;

/**
 *
 * @author DELL
 */
public class FoodService {

    public static Food getFoodById(int id) {
        return FoodDAO.getInstance().selectById(id);
    }

    public static ArrayList<Food> getOrderedFoodByBookingTableId(int bookingTableId) {
        return FoodDAO.getInstance().selectOrderedFoodByBookingTableId(bookingTableId);
    }

    public static ArrayList<Food> getAllFoods() {
        return FoodDAO.getInstance().selectAll();
    }

    public static Food getFoodByName(String name) {
        return FoodDAO.getInstance().selectFoodByName(name);

    }
}
