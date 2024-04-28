/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class JDBCConnection {
    
    public static Connection getConnect() {
        Connection con = null;
        try {

            String url = "jdbc:mysql://localhost:3306/QuanLyNhaHang";
            String username = "root";
            String password = "";

            DriverManager.registerDriver(new Driver());
            con = DriverManager.getConnection(url, username, password);

        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public static void disconnect(Connection con) {
        
        try {
            if( con.isClosed() != true) {
                con.close();
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
