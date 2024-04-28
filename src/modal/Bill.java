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
public class Bill {
    int id;
    int bookingTableId;
    int foodNumber;
    int sum;
    int discount;
    int finalCost;
    
    Date date;

    public Bill() {
    }

    public Bill(int id, int bookingTableId, int foodNumber, int sum, int discount, int finalCost, Date date) {
        this.id = id;
        this.bookingTableId = bookingTableId;
        this.foodNumber = foodNumber;
        this.sum = sum;
        this.discount = discount;
        this.finalCost = finalCost;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public int getFoodNumber() {
        return foodNumber;
    }

    public void setFoodNumber(int foodNumber) {
        this.foodNumber = foodNumber;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(int finalCost) {
        this.finalCost = finalCost;
    }

    @Override
    public String toString() {
        return "Bill{" + "id=" + id + ", bookingTableId=" + bookingTableId + ", foodNumber=" + foodNumber + ", sum=" + sum + ", discount=" + discount + ", finalCost=" + finalCost + ", date=" + date + '}';
    }


    
    
}
