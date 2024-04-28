/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.BookingFoodDAO;
import java.util.ArrayList;
import modal.BookingFood;

/**
 *
 * @author DELL
 */
public class BookingFoodService {

    public static ArrayList<BookingFood> getAllBookingFoodByBookingTableId(int BookingTableId) {
        return BookingFoodDAO.getInstance().selectBookingFoodByBookingTableId(BookingTableId);
    }
    
    public static int addNewBookingFood(BookingFood bookingFood) {
        return BookingFoodDAO.getInstance().insert(bookingFood);
    }
}
