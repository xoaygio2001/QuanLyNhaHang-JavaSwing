/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modal;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author DELL
 */
public class BookingTable {

    int id;
    int tableId;
    int staffId;
    String statusId;
    Time timeIn;
    Time timeOut;
    Date date;

    public BookingTable() {
    }

    public BookingTable(int id, int tableId, int staffId, String statusId, Time timeIn, Time timeOut, Date date) {
        this.id = id;
        this.tableId = tableId;
        this.staffId = staffId;
        this.statusId = statusId;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public Time getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Time timeIn) {
        this.timeIn = timeIn;
    }

    public Time getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Time timeOut) {
        this.timeOut = timeOut;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BookingTable{" + "id=" + id + ", tableId=" + tableId + ", staffId=" + staffId + ", statusId=" + statusId + ", timeIn=" + timeIn + ", timeOut=" + timeOut + ", date=" + date + '}';
    }
    
    

}
