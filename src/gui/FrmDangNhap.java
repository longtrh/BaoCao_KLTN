package gui;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import dao.ThongTinCaNhanDAO;
import database.ConectDatabase;
import entities.TaiKhoan;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FrmDangNhap extends JFrame  implements ActionListener,KeyListener{

	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtLoginFRM;
	private JLabel lblUser;
	private JTextField txtPass;
	private JLabel lblPassword;
	private JButton btnLogin;
	private JButton btnReset;
	private JLabel lblSao;
	private JLabel lblSao2;
	private JLabel lblBatBuoc;
	private JLabel lblBatBuoc2;
	private JLabel lblMessLoiUser;
	private JLabel lblMessLoiPass;
	public static TaiKhoan taiKhoan ;
	public static boolean TrangThaiDangNhapNhanVien = false;
	public static boolean TrangThaiDangNhapQuanLy = false;
	private String tenTaiKhoanAdmin = "ADMIN";
	private String matKhauAdmin = "ADMIN";
	public static String usernameToGetNhanVien="";
	private static ThongTinCaNhanDAO thongTinCaNhanDAO=new ThongTinCaNhanDAO();
	/**
	 * Create the frame.
	 */
	public FrmDangNhap() {		
		setBackground(new Color(153, 153, 255));
		setTitle("Đăng nhập");
		setBounds(100, 100, 578, 339);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		txtUser = new JTextField();
		txtUser.setForeground(new Color(51, 0, 51));
		txtUser.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtUser.setBackground(new Color(255, 255, 255));
		txtUser.setBounds(352, 83, 189, 25);
		contentPane.add(txtUser);
		txtUser.setColumns(10);

		lblUser = new JLabel("Username:");
		lblUser.setBackground(Color.LIGHT_GRAY);
		lblUser.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblUser.setBounds(254, 84, 88, 25);
		contentPane.add(lblUser);

		txtPass = new JPasswordField();
		txtPass.setBackground(new Color(255, 255, 255));
		txtPass.setBounds(352, 144, 189, 25);
		txtPass.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(txtPass);
		txtPass.setColumns(10);

		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPassword.setBounds(254, 146, 81, 25);
		contentPane.add(lblPassword);

		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnLogin.setBounds(254, 222, 131, 32);
		btnLogin.setIcon(new ImageIcon("Hinh\\login.png"));
		contentPane.add(btnLogin);

		btnReset = new JButton("Quên mật khẩu");
		btnReset.setIcon(new ImageIcon("Hinh\\arrow_refresh.png"));
		btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnReset.setBounds(395, 222, 148, 32);
		contentPane.add(btnReset);

		lblMessLoiUser = new JLabel("");
		lblMessLoiUser.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblMessLoiUser.setForeground(Color.RED);
		lblMessLoiUser.setBounds(240, 112, 298, 21);
		contentPane.add(lblMessLoiUser);

		lblMessLoiPass = new JLabel("");
		lblMessLoiPass.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblMessLoiPass.setForeground(Color.RED);
		lblMessLoiPass.setBounds(254, 172, 287, 25);
		contentPane.add(lblMessLoiPass);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("Hinh\\logo-login.png"));
		label.setBounds(0, 0, 220, 300);
		contentPane.add(label);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(276, 11, 189, 49);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setForeground(Color.RED);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblLogin.setBounds(10, 11, 169, 27);
		panel.add(lblLogin);

		btnReset.addActionListener(this);
		btnLogin.addActionListener(this);
		txtUser.requestFocus();
		this.addKeyListener(this);
		
	}
	public boolean KiemTraDuLieu() {
		String tenUser = txtUser.getText();
		// ten dang nhap phai la chu hoac so va khong co ki tu dac biet co toi da tu 5-20 ki tu
		boolean match = tenUser.matches("[a-zA-z0-9 ]{3,20}");
		if(match!=true) {
			lblMessLoiUser.setText("Lỗi: Tên đăng Nhập(Không Chứa Ký Tự �?ặt Biệt,Tối Thiểu 3-20 Ký Tự)");
			return false;
		}
		else
			return true;
	}
	public void loadTaiKhoan(String tenDangNhap, String matKhau) {
	    try {
	        Connection con = ConectDatabase.getInstance().getConnection();
	        PreparedStatement stmt = null;
	        String sql = "SELECT t.TenTaiKhoan, MatKhau, LoaiNhanVien " +
	                     "FROM dbo.TaiKhoan t " +
	                     "JOIN dbo.NhanVien n ON t.TenTaiKhoan = n.TenTaiKhoan " +
	                     "WHERE t.TenTaiKhoan = ? AND t.MatKhau = ?";
	        stmt = con.prepareStatement(sql);
	        stmt.setString(1, tenDangNhap);
	        stmt.setString(2, matKhau);
	        ResultSet rs = stmt.executeQuery();

	        // Kiểm tra kết quả truy vấn
	        if (!rs.next()) {
	            System.out.println("Không tìm thấy tài khoản trong cơ sở dữ liệu!");
	            return; // Thoát nếu không có kết quả
	        }

	        // Gán giá trị từ SQL
	        String ten = rs.getString(1).trim();
	        String mk = rs.getString(2).trim();
	        String loaiTk = rs.getString(3).trim();

	        taiKhoan = new TaiKhoan(ten, mk, loaiTk);
	        System.out.println("Tải tài khoản thành công: " + taiKhoan);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	public boolean kiemTraDangNhap(String tenDangNhap, String matKhau) {
	    System.out.println("Kiểm tra tài khoản: " + tenDangNhap + ", Mật khẩu: " + matKhau);

	    if (taiKhoan == null) {
	        System.out.println("Đối tượng tài khoản null.");
	        return false;
	    }

	    if (tenDangNhap.equalsIgnoreCase(tenTaiKhoanAdmin) && matKhau.equalsIgnoreCase(matKhauAdmin)) {
	        TrangThaiDangNhapNhanVien = true;
	        TrangThaiDangNhapQuanLy = true;
	        return true;
	    }

	    System.out.println("Loại tài khoản: " + taiKhoan.getLoaiTaiKhoan());

	    if (taiKhoan.getTenTaiKhoan().equalsIgnoreCase(tenDangNhap) &&
	        taiKhoan.getMatKhau().equalsIgnoreCase(matKhau) &&
	        "NV".equalsIgnoreCase(taiKhoan.getLoaiTaiKhoan())) {
	        TrangThaiDangNhapNhanVien = true;
	        return true;
	    }

	    if (taiKhoan.getTenTaiKhoan().equalsIgnoreCase(tenDangNhap) &&
	        taiKhoan.getMatKhau().equalsIgnoreCase(matKhau) &&
	        "QL".equalsIgnoreCase(taiKhoan.getLoaiTaiKhoan())) {
	        TrangThaiDangNhapQuanLy = true;
	        return true;
	    }

	    return false;
	}


	public boolean kiemTraDangNhapAdmin(String tenDangNhap,String matKhau) {
		if(tenDangNhap.equalsIgnoreCase(tenTaiKhoanAdmin) && matKhau.equalsIgnoreCase(matKhauAdmin)) {
			TrangThaiDangNhapNhanVien = true ;
			TrangThaiDangNhapQuanLy = true;
			return true;
		}
		return false;
	}
	public void logIn() {
	    try {
	        if (KiemTraDuLieu()) {
	            String tenDN = txtUser.getText().trim();
	            String matKhau = txtPass.getText().trim();

	            // Kiểm tra trạng thái nhân viên
	            String trangThaiNhanVien = thongTinCaNhanDAO.getTrangThaiNhanVien(tenDN);
	            System.out.println("Trạng thái nhân viên: " + trangThaiNhanVien);

	            if ("Nghỉ việc".equalsIgnoreCase(trangThaiNhanVien)) {
	                JOptionPane.showMessageDialog(this, "Tài khoản này thuộc về nhân viên đã nghỉ việc. Không thể đăng nhập!", "Thông báo", JOptionPane.WARNING_MESSAGE);
	                return;
	            }

	            // Kiểm tra đăng nhập
	            loadTaiKhoan(tenDN, matKhau);
	            System.out.println("Kiểm tra đăng nhập kết quả: " + kiemTraDangNhap(tenDN, matKhau));

	            if (kiemTraDangNhap(tenDN, matKhau) && TrangThaiDangNhapNhanVien && TrangThaiDangNhapQuanLy) {
	                usernameToGetNhanVien = txtUser.getText();
	                FrmManHinhChinh frmManHinhChinh = new FrmManHinhChinh();
	                frmManHinhChinh.setVisible(true);
	                this.setVisible(false);
	            } else if (kiemTraDangNhap(tenDN, matKhau) && TrangThaiDangNhapNhanVien) {
	                usernameToGetNhanVien = txtUser.getText();
	                FrmManHinhChinh frmManHinhChinh = new FrmManHinhChinh();
	                frmManHinhChinh.mntmQuanLyThuoc.setEnabled(false);
	                frmManHinhChinh.mnNhanVien.setEnabled(false);
	                frmManHinhChinh.mnThongKe.setEnabled(false);
	                frmManHinhChinh.setVisible(true);
	                this.setVisible(false);
	            } else if (kiemTraDangNhap(tenDN, matKhau) && TrangThaiDangNhapQuanLy) {
	                usernameToGetNhanVien = txtUser.getText();
	                FrmManHinhChinh frmManHinhChinh = new FrmManHinhChinh();
	                frmManHinhChinh.mnLapHoaDon.setEnabled(false);
	                frmManHinhChinh.setVisible(true);
	                this.setVisible(false);
	            } else {
	                JOptionPane.showMessageDialog(this, "Tên Đăng Nhập, Hoặc Mật Khẩu Sai.");
	            }
	        }
	    } catch (Exception e2) {
	        e2.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Tên Đăng Nhập, Hoặc Mật Khẩu Sai.");
	    }
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(btnReset)) {
		    String tenTaiKhoan = txtUser.getText().trim();
		    
		    // Kiểm tra nếu username là ADMIN
		    if (tenTaiKhoan.equalsIgnoreCase("ADMIN")) {
		        JOptionPane.showMessageDialog(this, "Tài khoản ADMIN không được phép truy cập chức năng này!", 
		                                      "Cảnh báo", JOptionPane.WARNING_MESSAGE);
		        return;
		    }

		    // Kiểm tra nếu username trống
		    if (tenTaiKhoan.isEmpty()) {
		        JOptionPane.showMessageDialog(this, "Vui lòng nhập Username trước!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    // Kiểm tra Username có tồn tại trong cơ sở dữ liệu không
		    if (!thongTinCaNhanDAO.kiemTraUsernameTonTai(tenTaiKhoan)) {
		        JOptionPane.showMessageDialog(this, "Username không tồn tại trong hệ thống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    // Yêu cầu nhập số điện thoại và CMND
		    String soDienThoai = JOptionPane.showInputDialog(this, "Nhập số điện thoại:");
		    String cmnd = JOptionPane.showInputDialog(this, "Nhập CMND:");

		    // Kiểm tra thông tin
		    if (thongTinCaNhanDAO.kiemTraThongTinResetMatKhau(tenTaiKhoan, soDienThoai, cmnd)) {
		        String matKhauMoi = JOptionPane.showInputDialog(this, "Nhập mật khẩu mới:");
		        if (matKhauMoi != null && !matKhauMoi.trim().isEmpty()) {
		            thongTinCaNhanDAO.capNhatMatKhauMoi(tenTaiKhoan, matKhauMoi);
		            JOptionPane.showMessageDialog(this, "Cập nhật mật khẩu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		        }
		    } else {
		        JOptionPane.showMessageDialog(this, "Thông tin không chính xác!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		    }
		}


		else if(obj.equals(btnLogin)) {
			logIn();
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			logIn();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			logIn();
		}
	}

}
