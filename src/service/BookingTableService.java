/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.BookingTableDAO;
import modal.BookingTable;

/**
 *
 * @author DELL
 */
public class BookingTableService {

    public static BookingTable getBookingTableByDeskId(int deskId) {
        return BookingTableDAO.getInstance().selectByDeskId(deskId);
    }

    public static BookingTable getBookingTableById(int id) {
        return BookingTableDAO.getInstance().selectById(id);
    }
    
    public static int changeTableByDeskName(String deskName,int oldDeskId) {
        return BookingTableDAO.getInstance().updateBookingTableByDeskName(deskName,oldDeskId);
    }
    
    public static int setBookingTablePaidBill(int id) {
        return BookingTableDAO.getInstance().updateBookingTablePaidBill(id);
    }
    
    public static int addNewBookingTable(BookingTable b) {
        return BookingTableDAO.getInstance().insert(b);
    }
}
