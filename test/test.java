
import database.JDBCConnection;
import java.sql.Connection;
import java.util.ArrayList;
import modal.Account;
import modal.Bill;
import modal.BookingFood;
import modal.BookingTable;
import modal.Desk;
import modal.Food;
import service.AccountService;
import service.BillService;
import service.BookingFoodService;
import service.BookingTableService;
import service.DeskService;
import service.FoodService;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author DELL
 */
public class test {

    public static void main(String[] args) {
        
        
//        Desk a = new Desk();
//        a = DeskService.getDeskByName("Sa1");
//        
//        System.out.println(a.toString());



//        ArrayList<Food> a = new ArrayList<>();
//        a = FoodService.getAllFoods();
//
//        for (Food b: a) {
//            System.out.println(b.toString());
//        }


          BookingTable a = new BookingTable();
          a.setTableId(1);
          a.setStaffId(1);
          
          BookingTableService.addNewBookingTable(a);


//        System.out.println( BookingTableService.setBookingTablePaidBill(11)); 



    }
}
