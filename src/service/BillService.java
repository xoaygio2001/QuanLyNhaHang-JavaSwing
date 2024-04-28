/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.BillDAO;
import modal.Bill;

/**
 *
 * @author DELL
 */
public class BillService {

    public static int addNewBill(Bill b) {
        return BillDAO.getInstance().insert(b);
    }
}
