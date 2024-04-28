/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.DeskDAO;
import java.util.ArrayList;
import modal.Desk;

/**
 *
 * @author DELL
 */
public class DeskService {

    public static ArrayList<Desk> getAllDesks() {
        return DeskDAO.getInstance().selectAll();
    }

    public static ArrayList<Desk> getEmptyDesks() {
        return DeskDAO.getInstance().SelectEmptyTables();
    }

    public static Desk getDeskById(int id) {
        return DeskDAO.getInstance().selectById(id);
    }

    public static Desk getDeskByName(String name) {
        return DeskDAO.getInstance().sellectByName(name);

    }
}
