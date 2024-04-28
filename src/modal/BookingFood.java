/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modal;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class BookingFood {
    int id;
    int bookingTableId;
    int staffId;
    int foodId;
    Date time;

    public BookingFood() {
    }

    public BookingFood(int id, int bookingTableId, int staffId, int foodId) {
        this.id = id;
        this.bookingTableId = bookingTableId;
        this.staffId = staffId;
        this.foodId = foodId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookingTableId() {
        return bookingTableId;
    }

    public void setBookingTableId(int bookingTableId) {
        this.bookingTableId = bookingTableId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "BookingFood{" + "id=" + id + ", bookingTableId=" + bookingTableId + ", staffId=" + staffId + ", foodId=" + foodId + ", time=" + time + '}';
    }
    
    
    
}
