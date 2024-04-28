/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
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
import static view.ManageDesk.setBasicContruct;
import static view.ManageDesk.setMenuBarForJframe;

/**
 *
 * @author DELL
 */
public class ManageDetailBookingTable extends javax.swing.JFrame {

    public ManageDetailBookingTable() {

    }

    public static void ShowManageDetailBookingTable(int id) {
        bookingTableId = id;
        JFrame jframe = new JFrame();

        refreshDataOrCreateNewData(jframe);
    }

    /**
     * Creates new form ManageDetailBookingTable
     */
    static int bookingTableId;
    static BookingTable bookingTableData = new BookingTable();
    static Account staff = new Account();
    static Desk desk = new Desk();

    static ArrayList<BookingFood> bookingFood = new ArrayList<>();

    static ArrayList<Food> orderedFood = new ArrayList<>();

    static int total;
    static int discount;

    static ArrayList<Desk> emptyTable = new ArrayList<>();

    static ArrayList<Food> allFood = new ArrayList<>();

    ///
    public static DefaultTableModel tableModel;

    public static JLabel orderStaff;

    public static JLabel orderTable;

    public static void updateData() {

        bookingTableData = BookingTableService.getBookingTableById(bookingTableId);

        staff = AccountService.getAccountById(bookingTableData.getStaffId());

        desk = DeskService.getDeskById(bookingTableData.getTableId());

        bookingFood = BookingFoodService.getAllBookingFoodByBookingTableId(bookingTableData.getId());

        orderedFood = FoodService.getOrderedFoodByBookingTableId(bookingTableId);

        emptyTable = DeskService.getEmptyDesks();

        allFood = FoodService.getAllFoods();

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

    public static void setLayoutAndData(JFrame jframe) {
        jframe.setLayout(null);

        updateData();

        orderStaff = new JLabel("Nhân viên: " + staff.getName());
        orderStaff.setBounds(50, 50, 100, 20);  // Vị trí x, y và kích thước width, height

        orderTable = new JLabel("Ban: " + desk.getName());
        orderTable.setBounds(180, 50, 100, 20);  // Vị trí x, y và kích thước width, height

        jframe.add(orderStaff);
        jframe.add(orderTable);

        JSeparator separator = new JSeparator();

        // Đặt giới hạn cho JSeparator
        separator.setBounds(50, 90, 260, 10);

        jframe.add(separator);

        // Dữ liệu cho bảng
        String[] columnNames = {"Tên món", "Loại", "Số lượng", "Đơn giá", "Thành tiền"};

        // Tạo DefaultTableModel và JTable
        Object[][] data = new Object[orderedFood.size()][];

        for (int i = 0; i < orderedFood.size(); i++) {
            Food dete = orderedFood.get(i);
            data[i] = new Object[]{dete.getName(), dete.getType().equals("EAT") ? "Đồ ăn" : "Nước uống",
                dete.getQuantity(), dete.getPrice(), dete.getTotal()};
        }

        tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);

        // Sử dụng JScrollPane để cho phép cuộn nếu cần thiết
        JScrollPane scrollPane = new JScrollPane(table);

        // Đặt vị trí và kích thước của JScrollPane (bao quanh JTable)
        scrollPane.setBounds(50, 100, 450, 300);  // Vị trí x, y và kích thước width, height

        // Thêm JScrollPane vào JFrame
        jframe.add(scrollPane);

        ////////
        JComboBox<String> comboType = new JComboBox<>(new String[]{"EAT", "DRINK"});

        // Tạo Combobox tên thực phẩm
        ArrayList<Food> initFood = new ArrayList<>();
        for (Food f : allFood) {
            if (f.getType().equals("DRINK")) {
                initFood.add(f);
            }
        }

        JComboBox<String> comboFood = new JComboBox<>();

        for (Food f : allFood) {
            if (f.getType().equals("EAT")) {
                comboFood.addItem((String) f.getName());
            }

        }

        // Sự kiện khi thay đổi giá trị ở Combobox loại thực phẩm
        comboType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Làm mới Combobox tên thực phẩm dựa trên loại đã chọn
                    comboFood.removeAllItems(); // Xóa các mục hiện tại
                    String selectedType = (String) comboType.getSelectedItem();
                    // Thêm các mục tương ứng vào Combobox tên thực phẩm
                    for (Food item : allFood) {
                        if (item.getType().equals(selectedType)) {
                            comboFood.addItem((String) item.getName());
                        }
                    }
                }
            }
        });

        // Cài đặt GUI
        JPanel panel = new JPanel();
        panel.add(new JLabel("Chọn loại thực phẩm:"));
        panel.add(comboType);
        panel.add(new JLabel("Chọn tên thực phẩm:"));

        panel.add(comboFood);
        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 100, 1);
        JSpinner foodAmount = new JSpinner(model);
        foodAmount.setPreferredSize(new Dimension(70, 30));

        panel.add(foodAmount);

        panel.setBounds(400, 30, 300, 70);
//        panel.setBackground(Color.YELLOW);

        jframe.add(panel);

        ////////
        JButton addNewFoodButton = new JButton("Them mon");

        addNewFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String foodName = (String) comboFood.getSelectedItem();
                Food foodFind = FoodService.getFoodByName(foodName);

                BookingFood bookingStamp = new BookingFood();
                bookingStamp.setBookingTableId(bookingTableId);
                bookingStamp.setStaffId(staff.getId());
                bookingStamp.setFoodId(foodFind.getId());

                for (int i = 0; i < (int) foodAmount.getValue(); i++) {
                    BookingFoodService.addNewBookingFood(bookingStamp);

                }

                refeshData();

            }
        });

        addNewFoodButton.setBounds(700, 40, 130, 50);

        jframe.add(addNewFoodButton);

        JSeparator separator2 = new JSeparator();

        // Đặt giới hạn cho JSeparator
        separator2.setBounds(530, 100, 400, 10);

        jframe.add(separator2);

        String[] options = new String[emptyTable.size()];

        for (int i = 0; i < emptyTable.size(); i++) {
            options[i] = emptyTable.get(i).getName();
        }

        JComboBox<String> comboBox = new JComboBox<>(options);

        // Đặt vị trí và kích thước cho JComboBox
        comboBox.setBounds(620, 140, 100, 30);
        jframe.add(comboBox);

        JButton changeTableButton = new JButton("Chuyen ban");
        changeTableButton.setBounds(750, 140, 100, 30);
        changeTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookingTableService.changeTableByDeskName((String) comboBox.getSelectedItem(), desk.getId());
                refeshData();
            }
        });
        jframe.add(changeTableButton);

        JLabel discountLabel = new JLabel("Giam gia: ");
        discountLabel.setBounds(620, 250, 100, 20);  // Vị trí x, y và kích thước width, height

        jframe.add(discountLabel);

        JTextField textFieldDiscount = new JTextField();
        textFieldDiscount.setBounds(690, 250, 100, 30);  // (x, y, width, height)

        jframe.add(textFieldDiscount);

        JLabel ammoutLabel = new JLabel("Tong tien: 250.000d");
        ammoutLabel.setBounds(680, 300, 200, 20);  // Vị trí x, y và kích thước width, height

        jframe.add(ammoutLabel);

        JButton printInvoiceButton = new JButton("In hoa don");

        printInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refeshData();
            }
        });

        printInvoiceButton.setBounds(610, 350, 100, 30);

        jframe.add(printInvoiceButton);

        JButton paymentButton = new JButton("Thanh toan");
        paymentButton.setBounds(750, 350, 100, 30);
        jframe.add(paymentButton);
        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int getInvoice = 0;
                int giamgia = textFieldDiscount.getText().equals("") ? 0
                        : Integer.valueOf(textFieldDiscount.getText());

                for (Food f : orderedFood) {

                    getInvoice += f.getTotal();
                }

                Bill b = new Bill();

                b.setBookingTableId(bookingTableId);
                b.setSum(getInvoice);
                b.setDiscount(giamgia);
                b.setFinalCost(getInvoice - giamgia);

                int quantity = 0;
                for (Food f : orderedFood) {
                    quantity += f.getQuantity();
                }
                b.setFoodNumber(quantity);

                int length = BillService.addNewBill(b);

                if (length == 1) {
                    BookingTableService.setBookingTablePaidBill(bookingTableId);
                }

                System.out.println("Tong tien cua ban la: " + (getInvoice - giamgia));

                refeshData();
            }
        });

    }

    public static void refreshDataOrCreateNewData(JFrame jframe) {
        setMenuBarForJframe(jframe);

        setBasicContruct(jframe);

        setLayoutAndData(jframe);
    }

    public static void refeshData() {

        bookingTableData = BookingTableService.getBookingTableById(bookingTableId);

        staff = AccountService.getAccountById(bookingTableData.getStaffId());
        orderStaff.setText("Nhân viên: " + staff.getName());

        desk = DeskService.getDeskById(bookingTableData.getTableId());
        orderTable.setText("Ban: " + desk.getName());

        orderedFood = FoodService.getOrderedFoodByBookingTableId(bookingTableId);

        tableModel.setRowCount(0);

        for (Food f : orderedFood) {
            tableModel.addRow(new Object[]{f.getName(), f.getType().equals("EAT") ? "Đồ ăn" : "Nước uống", f.getQuantity(), f.getPrice(), f.getTotal()});
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jSpinner1 = new javax.swing.JSpinner();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Bàn: TA1");

        jLabel2.setText("Nhân viên order: Luân");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Số lượng", "Đơn giá", "Tổng"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Thêm món");
        jButton1.setToolTipText("");

        jButton2.setText("Thanh toán");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Tổng tiền: 300.000đ");

        jButton3.setText("In hóa đơn");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Giảm giá:");

        jTextField1.setText("0");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton4.setText("Chuyển bàn");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel3)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(ManageDetailBookingTable.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageDetailBookingTable.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageDetailBookingTable.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageDetailBookingTable.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new ManageDetailBookingTable().setVisible(true);

                JFrame jframe = new JFrame();

                refreshDataOrCreateNewData(jframe);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
