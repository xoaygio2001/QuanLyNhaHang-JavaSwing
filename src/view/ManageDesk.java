/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import modal.BookingTable;
import modal.Desk;
import service.BookingTableService;
import service.DeskService;

/**
 *
 * @author DELL
 */
public class ManageDesk extends javax.swing.JFrame {

    /**
     * Creates new form ManageDesk
     */
    public ManageDesk() {
        initComponents();
    }

    public static void setMenuBarForJframe(JFrame jframe) {
        // Tạo thanh menu (JMenuBar)
        JMenuBar menuBar = new JMenuBar();

        // Tạo một menu chính (JMenu)
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");

        // Tạo các mục menu con (JMenuItem)
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");

        JMenuItem aboutItem = new JMenuItem("About");

        // Thêm các mục menu con vào menu chính
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator(); // Tạo một đường phân cách
        fileMenu.add(exitItem);

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        helpMenu.add(aboutItem);

        // Thêm các menu chính vào thanh menu
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        jframe.setJMenuBar(menuBar);
    }

    public static void setBasicContruct(JFrame jframe) {
        jframe.setVisible(true);
        jframe.setSize(950, 550);
        jframe.setLocationRelativeTo(null);
        jframe.setTitle("Quản Lý Nhà Hàng");
        jframe.setLayout(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void setButtonLayout(JFrame jframe) {

        ArrayList<Desk> data = DeskService.getAllDesks();

        int x = 100;
        int y = 50;
        int width = 130;
        int height = 70;

        for (int i = 1; i <= data.size(); i++) {
            Desk fuckBitch = data.get(i - 1);
            JButton button = new JButton(data.get(i - 1).getName() + " (Trống)");

            button.setCursor(new Cursor(Cursor.HAND_CURSOR));

            button.setBounds(x, y, width, height);
            x = x + width + 30;
            if (i % 4 == 1 && i != 1) {
                y = y + y + 30;
                x = 100;
            }

            int id = data.get(i - 1).getId();
            

            BookingTable booking1 = BookingTableService.getBookingTableByDeskId(id);

            if (booking1.getId() != 0) {
                button.setText(data.get(i - 1).getName() + " (Có người)");
                button.setBackground(Color.BLUE);
                button.setForeground(Color.WHITE);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Hiển thị hộp thoại xác nhận
                        int result = JOptionPane.showConfirmDialog(
                                jframe,
                                "Bàn đang được phục vụ bạn muốn xem chứ?",
                                "Xác nhận",
                                JOptionPane.YES_NO_OPTION
                        );

                        // Kiểm tra kết quả và thực hiện hành động tương ứng
                        if (result == JOptionPane.YES_OPTION) {
                            ManageDetailBookingTable.ShowManageDetailBookingTable(id);
                                 
                            jframe.hide();
                        }
                    }
                });

            } else {

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Hiển thị hộp thoại xác nhận
                        int result = JOptionPane.showConfirmDialog(
                                jframe,
                                "Bạn muốn order bàn này?",
                                "Xác nhận",
                                JOptionPane.YES_NO_OPTION
                        );

                        // Kiểm tra kết quả và thực hiện hành động tương ứng
                        if (result == JOptionPane.YES_OPTION) {
                            BookingTable b = new BookingTable();
                            b.setTableId(id);
                            b.setStaffId(1);
                            BookingTableService.addNewBookingTable(b);
                             ManageDetailBookingTable.ShowManageDetailBookingTable(id);
                            jframe.hide();
                        }
                    }
                });
            }

            jframe.add(button);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManageDesk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageDesk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageDesk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageDesk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new ManageDesk().setVisible(true);

                JFrame jframe = new JFrame();

                setMenuBarForJframe(jframe);

                setBasicContruct(jframe);

                setButtonLayout(jframe);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
