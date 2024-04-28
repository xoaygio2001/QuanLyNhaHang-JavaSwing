
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Lớp đại diện cho dữ liệu doanh thu
class Revenue {
    int stt;
    String id;
    double total;
    String date;

    public Revenue(int stt, String id, double total, String date) {
        this.stt = stt;
        this.id = id;
        this.total = total;
        this.date = date;
    }
}

// Lớp chính cho ứng dụng quản lý doanh thu
public class RevenueManager extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public RevenueManager() {
        setTitle("Quản lý doanh thu");
        setSize(950, 550); // Kích thước cửa sổ
        setLocationRelativeTo(null); // Định vị ở giữa màn hình
        setResizable(false); // Không cho phép thay đổi kích thước
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // Bảng và mô hình bảng
        String[] columnNames = {"STT", "ID", "Tổng tiền", "Ngày"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Tạo bảng điều khiển để chọn khoảng thời gian
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JLabel lblFromDate = new JLabel("Từ ngày:");
        JLabel lblToDate = new JLabel("Đến ngày:");
        
        JSpinner spinnerFromDate = createDateSpinner();
        JSpinner spinnerToDate = createDateSpinner();

        JButton btnView = new JButton("Xem");
        controlPanel.add(lblFromDate);
        controlPanel.add(spinnerFromDate);
        controlPanel.add(lblToDate);
        controlPanel.add(spinnerToDate);
        controlPanel.add(btnView);

        add(controlPanel, BorderLayout.NORTH); // Panel điều khiển
        add(new JScrollPane(table), BorderLayout.CENTER); // Bảng với thanh cuộn

        List<Revenue> revenues = getSampleData();

        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date fromDate = (Date) spinnerFromDate.getValue();
                Date toDate = (Date) spinnerToDate.getValue();
                loadRevenueByDateRange(revenues, fromDate, toDate);
            }
        });
    }

    private JSpinner createDateSpinner() {
        // Tạo spinner để chọn ngày
        JSpinner.DateEditor editor;
        JSpinner spinner = new JSpinner(new SpinnerDateModel());
        editor = new JSpinner.DateEditor(spinner, "yyyy-MM-dd");
        spinner.setEditor(editor);
        return spinner;
    }

    private List<Revenue> getSampleData() {
        List<Revenue> revenues = new ArrayList<>();
        revenues.add(new Revenue(1, "001", 50000, "2023-04-28"));
        revenues.add(new Revenue(2, "002", 100000, "2023-04-15"));
        revenues.add(new Revenue(3, "003", 75000, "2023-05-10"));
        return revenues;
    }

    private void loadRevenueByDateRange(List<Revenue> revenues, Date fromDate, Date toDate) {
        tableModel.setRowCount(0); // Xóa bảng
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        for (Revenue revenue : revenues) {
            try {
                Date revenueDate = sdf.parse(revenue.date);
                
                if (revenueDate.compareTo(fromDate) >= 0 && revenueDate.compareTo(toDate) <= 0) {
                    tableModel.addRow(new Object[]{revenue.stt, revenue.id, revenue.total, revenue.date});
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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
            java.util.logging.Logger.getLogger(RevenueManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RevenueManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RevenueManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RevenueManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RevenueManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
