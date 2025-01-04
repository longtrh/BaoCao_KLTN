package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.toedter.calendar.JDateChooser;

public class FrmNhapLaiThongTin extends JDialog {
    private JTextField txtTenThuoc, txtNhaCungCap, txtSoLuong;
    private JDateChooser dateNgaySX, dateHanSD;
    private boolean isConfirmed = false; // Xác nhận nhập lại

    public FrmNhapLaiThongTin(Frame owner, String tenThuoc, String nhaCungCap) {
        super(owner, "Nhập lại thông tin thuốc", true);
        setSize(400, 300);
        setLocationRelativeTo(owner);
        setLayout(null);

        JLabel lblTenThuoc = new JLabel("Tên thuốc:");
        lblTenThuoc.setBounds(20, 20, 80, 25);
        add(lblTenThuoc);

        txtTenThuoc = new JTextField(tenThuoc);
        txtTenThuoc.setEditable(false);
        txtTenThuoc.setBounds(120, 20, 200, 25);
        add(txtTenThuoc);

        JLabel lblNhaCungCap = new JLabel("Nhà cung cấp:");
        lblNhaCungCap.setBounds(20, 60, 100, 25);
        add(lblNhaCungCap);

        txtNhaCungCap = new JTextField(nhaCungCap);
        txtNhaCungCap.setEditable(false);
        txtNhaCungCap.setBounds(120, 60, 200, 25);
        add(txtNhaCungCap);

        JLabel lblNgaySX = new JLabel("Ngày SX:");
        lblNgaySX.setBounds(20, 100, 80, 25);
        add(lblNgaySX);

        dateNgaySX = new JDateChooser();
        dateNgaySX.setBounds(120, 100, 200, 25);
        add(dateNgaySX);

        JLabel lblHanSD = new JLabel("Hạn SD:");
        lblHanSD.setBounds(20, 140, 80, 25);
        add(lblHanSD);

        dateHanSD = new JDateChooser();
        dateHanSD.setBounds(120, 140, 200, 25);
        add(dateHanSD);

        JLabel lblSoLuong = new JLabel("Số lượng:");
        lblSoLuong.setBounds(20, 180, 80, 25);
        add(lblSoLuong);

        txtSoLuong = new JTextField();
        txtSoLuong.setBounds(120, 180, 200, 25);
        add(txtSoLuong);

        JButton btnXacNhan = new JButton("Xác nhận");
        btnXacNhan.setBounds(70, 220, 100, 25);
        add(btnXacNhan);

        JButton btnHuy = new JButton("Hủy");
        btnHuy.setBounds(200, 220, 100, 25);
        add(btnHuy);

        // Event xác nhận
        btnXacNhan.addActionListener(e -> {
            try {
                // Kiểm tra số lượng
                int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
                if (soLuong <= 50) {
                    JOptionPane.showMessageDialog(this, 
                        "Số lượng phải lớn hơn 50!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra ngày sản xuất và hạn sử dụng
                if (dateNgaySX.getDate() == null || dateHanSD.getDate() == null) {
                    JOptionPane.showMessageDialog(this, 
                        "Vui lòng nhập đầy đủ Ngày sản xuất và Hạn sử dụng!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Nếu điều kiện hợp lệ
                isConfirmed = true;
                dispose(); // Đóng form
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Số lượng phải là số nguyên hợp lệ!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Event hủy
        btnHuy.addActionListener(e -> dispose());
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public String getSoLuong() {
        return txtSoLuong.getText();
    }

    public java.util.Date getNgaySX() {
        return dateNgaySX.getDate();
    }

    public java.util.Date getHanSD() {
        return dateHanSD.getDate();
    }
}

