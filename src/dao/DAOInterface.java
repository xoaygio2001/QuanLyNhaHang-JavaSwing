/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public interface DAOInterface<T> {

    public int insert(T t);

    public int update(T t);

    public int delete(int id);

    public ArrayList<T> selectAll();

    public T selectById(int id);

}
