package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import dao.NhaCungCapDAO;
import dao.ThuocDAO;
import database.ConectDatabase;
import entities.NhaCungCap;
import entities.Thuoc;


import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import java.util.Locale;
import javax.swing.JRadioButton;

public class FrmPhieuNhapThuoc extends JFrame {

	public static JPanel contentPane;
	private JTextField txtThuoc_Ma;
	private JTextField txtThuoc_Ten;
	private JTextField txtThuoc_SDK;
	private JTextField txtThuoc_GiaNhap;
	private JTextField txtThuoc_DonGia;
	private JTextField txtThuoc_SLN;
	private JTextField txtThuoc_QuyCach;
	private JTextField txtThuoc_TieuChuan;
	private JTable tblThuoc;
	private JTable tblGioHang;
	private JTextField txtThuoc_DBC;
	private DefaultTableModel tbModel = new DefaultTableModel();
	private JComboBox cmbDonViTinh,cmbPhanLoai,cmbTrangThai,cmbNhomThuoc,cmbTim;
	public static JComboBox cmbNCC;
	private JDateChooser dateNgaySX,dateHanSD;
	private ThuocDAO dao= new ThuocDAO();
	private NhaCungCapDAO dao_NCC= new NhaCungCapDAO();
	private JTextField txtHoatChat;
	private JTextField txtHamLuong;
	private String filename = null;
	private byte[] imageThuoc;
	private JLabel lblAnhThuoc;
	private JRadioButton radPhanLoai,radMa,radNhomThuoc,radNCC,radTenThuoc;
	private DefaultComboBoxModel cboModeNCC= new DefaultComboBoxModel();
	private DefaultComboBoxModel cboModePhanLoai= new DefaultComboBoxModel();
	private DefaultComboBoxModel cboModeDonViTinh= new DefaultComboBoxModel();
	private DefaultComboBoxModel cboModeTrangThai= new DefaultComboBoxModel();
	private DefaultComboBoxModel cboModeNhomThuoc= new DefaultComboBoxModel();
	
	private boolean isConfirmed = false; // Xác nhận nhập lại
	
	private JButton btnLamMoi;
	private List<String> listPhanLoai= new ArrayList<>();
	private List<String> listDonViTinh= new ArrayList<>();
	private List<String> listNhomThuoc= new ArrayList<>();	
	private List<String> listTrangThai= new ArrayList<>();
	private List<String> listNCC= new ArrayList<>();
	private List<String> listMa= new ArrayList<>();
	private List<String> listTenThuoc= new ArrayList<>();
	private List<String> listTimPhanLoai= new ArrayList<>();
	private List<String> listTimNhomThuoc= new ArrayList<>();
	private List<String> listTimNCC= new ArrayList<>();
	
	///
	public static JButton btnThem;
	public static JPanel pnlChucNang;

	public static JButton btnLuu,btnThemVaoGio;

	private int trangThaiThem;
	private int trangThaiSua;
	private FrmThemNhaCungCap frmthemNCC=new FrmThemNhaCungCap();

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public FrmPhieuNhapThuoc() {
		trangThaiThem=0;
		trangThaiSua=0;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1351,686);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnl_Thuoc = new JPanel();
		pnl_Thuoc.setBackground(new Color(176, 224, 230));
		pnl_Thuoc.setBounds(10, -23, 1323, 684);
		contentPane.add(pnl_Thuoc);
		pnl_Thuoc.setLayout(null);

		JPanel pnlNoiDung = new JPanel();
		pnlNoiDung.setBackground(new Color(173, 216, 230));
		pnlNoiDung.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Nội dung quản lý", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		pnlNoiDung.setBounds(20, 70, 604, 240);
		pnl_Thuoc.add(pnlNoiDung);
		pnlNoiDung.setLayout(null);

		JLabel lblThuoc_Ten = new JLabel("Mã :");
		lblThuoc_Ten.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_Ten.setBounds(10, 23, 53, 30);
		pnlNoiDung.add(lblThuoc_Ten);

		txtThuoc_Ma = new JTextField();
		txtThuoc_Ma.setEditable(false);
		txtThuoc_Ma.setBounds(105, 23, 197, 30);
		pnlNoiDung.add(txtThuoc_Ma);
		txtThuoc_Ma.setColumns(10);

		JLabel lblTen = new JLabel("Tên:\r\n");
		lblTen.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblTen.setBounds(307, 23, 53, 30);
		pnlNoiDung.add(lblTen);

		txtThuoc_Ten = new JTextField();
		txtThuoc_Ten.setEditable(false);
		txtThuoc_Ten.setColumns(10);
		txtThuoc_Ten.setBounds(403, 23, 197, 30);
		pnlNoiDung.add(txtThuoc_Ten);

		JLabel lblThuoc_SDK = new JLabel("Số đăng ký:\r\n");
		lblThuoc_SDK.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_SDK.setBounds(10, 56, 71, 30);
		pnlNoiDung.add(lblThuoc_SDK);

		txtThuoc_SDK = new JTextField();
		txtThuoc_SDK.setEditable(false);
		txtThuoc_SDK.setColumns(10);
		txtThuoc_SDK.setBounds(105, 56, 197, 30);
		pnlNoiDung.add(txtThuoc_SDK);

		JLabel lblThuoc_NCC = new JLabel("Nhà Cung Cấp:");
		lblThuoc_NCC.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_NCC.setBounds(307, 56, 86, 30);
		pnlNoiDung.add(lblThuoc_NCC);

		JLabel lblThuoc_NgSX = new JLabel("Ngày sản xuất :");
		lblThuoc_NgSX.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_NgSX.setBounds(10, 90, 99, 30);
		pnlNoiDung.add(lblThuoc_NgSX);

		JLabel lblThuoc_HSD = new JLabel("Hạn sự dụng:\r\n");
		lblThuoc_HSD.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_HSD.setBounds(307, 89, 86, 30);
		pnlNoiDung.add(lblThuoc_HSD);

		JLabel lblThuoc_DVT = new JLabel("Đơn vị tính:");
		lblThuoc_DVT.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_DVT.setBounds(10, 125, 86, 30);
		pnlNoiDung.add(lblThuoc_DVT);

		JLabel lblThuoc_GiaNhap = new JLabel("Giá nhập:\r\n");
		lblThuoc_GiaNhap.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_GiaNhap.setBounds(307, 125, 76, 30);
		pnlNoiDung.add(lblThuoc_GiaNhap);

		txtThuoc_GiaNhap = new JTextField();
		txtThuoc_GiaNhap.setEditable(false);
		txtThuoc_GiaNhap.setColumns(10);
		txtThuoc_GiaNhap.setBounds(403, 125, 197, 30);
		pnlNoiDung.add(txtThuoc_GiaNhap);

		JLabel lblThuoc_DonGia = new JLabel("Đơn giá:");
		lblThuoc_DonGia.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_DonGia.setBounds(10, 160, 71, 30);
		pnlNoiDung.add(lblThuoc_DonGia);

		txtThuoc_DonGia = new JTextField();
		txtThuoc_DonGia.setEditable(false);
		txtThuoc_DonGia.setColumns(10);
		txtThuoc_DonGia.setBounds(105, 160, 197, 30);
		pnlNoiDung.add(txtThuoc_DonGia);

		JLabel lblThuoc_SLN = new JLabel("Số lượng nhập:\r\n");
		lblThuoc_SLN.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_SLN.setBounds(307, 160, 99, 30);
		pnlNoiDung.add(lblThuoc_SLN);

		txtThuoc_SLN = new JTextField();
		txtThuoc_SLN.setEditable(false);
		txtThuoc_SLN.setColumns(10);
		txtThuoc_SLN.setBounds(403, 160, 197, 30);
		pnlNoiDung.add(txtThuoc_SLN);

		cmbNCC = new JComboBox();
		cmbNCC.setBounds(403, 55, 197, 30);
		pnlNoiDung.add(cmbNCC);

		cmbNCC.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String tenNCC = (String) cmbNCC.getSelectedItem(); // Lấy tên nhà cung cấp
		        docDuLieu(tenNCC); // Gọi hàm lọc thuốc theo NCC   
		    }
		});


		cmbDonViTinh = new JComboBox();
		cmbDonViTinh.setForeground(new Color(0, 0, 0));
		cmbDonViTinh.setBackground(new Color(255, 255, 255));
		cmbDonViTinh.setBounds(105, 125, 197, 30);
		pnlNoiDung.add(cmbDonViTinh);

		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTrangThai.setBounds(10, 197, 71, 30);
		pnlNoiDung.add(lblTrangThai);

		cmbTrangThai = new JComboBox();
		cmbTrangThai.setBounds(105, 197, 197, 30);
		pnlNoiDung.add(cmbTrangThai);

		JLabel lblThuoc_PhanLoai = new JLabel("Phân loại:");
		lblThuoc_PhanLoai.setBounds(307, 197, 61, 30);
		pnlNoiDung.add(lblThuoc_PhanLoai);
		lblThuoc_PhanLoai.setFont(new Font("Tahoma", Font.BOLD, 11));

		cmbPhanLoai = new JComboBox();
		cmbPhanLoai.setBounds(403, 196, 197, 30);
		pnlNoiDung.add(cmbPhanLoai);
		
		dateNgaySX = new JDateChooser();
		dateNgaySX.setLocale(new Locale("vi", "VN"));
		dateNgaySX.setDateFormatString("dd-MM-yyyy");
		dateNgaySX.setBounds(105, 90, 197, 30);
		JTextFieldDateEditor editor = (JTextFieldDateEditor) dateNgaySX.getDateEditor();
		editor.setEditable(false);
		pnlNoiDung.add(dateNgaySX);
		
		dateHanSD = new JDateChooser();
		dateHanSD.setLocale(new Locale("vi", "VN"));
		dateHanSD.setDateFormatString("dd-MM-yyyy");
		dateHanSD.setBounds(403, 90, 197, 30);
		JTextFieldDateEditor editor2 = (JTextFieldDateEditor) dateHanSD.getDateEditor();
		editor2.setEditable(false);
		pnlNoiDung.add(dateHanSD);

		JPanel pnlTTThuoc = new JPanel();
		pnlTTThuoc.setBackground(new Color(173, 216, 230));
		pnlTTThuoc.setBorder(new TitledBorder(null, "Thông tin thuốc", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTTThuoc.setBounds(634, 70, 679, 240);
		pnl_Thuoc.add(pnlTTThuoc);
		pnlTTThuoc.setLayout(null);

		JLabel lblThuoc_QuyCach = new JLabel("Quy cách đóng gói :");
		lblThuoc_QuyCach.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_QuyCach.setBounds(314, 28, 120, 30);
		pnlTTThuoc.add(lblThuoc_QuyCach);

		txtThuoc_QuyCach = new JTextField();
		txtThuoc_QuyCach.setEditable(false);
		txtThuoc_QuyCach.setColumns(10);
		txtThuoc_QuyCach.setBounds(434, 28, 198, 30);
		pnlTTThuoc.add(txtThuoc_QuyCach);

		JLabel lblThuoc_TieuChuan = new JLabel("Tiêu chuẩn:");
		lblThuoc_TieuChuan.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_TieuChuan.setBounds(314, 62, 81, 30);
		pnlTTThuoc.add(lblThuoc_TieuChuan);

		txtThuoc_TieuChuan = new JTextField();
		txtThuoc_TieuChuan.setEditable(false);
		txtThuoc_TieuChuan.setColumns(10);
		txtThuoc_TieuChuan.setBounds(434, 62, 198, 30);
		pnlTTThuoc.add(txtThuoc_TieuChuan);

		JLabel lblThuoc_HamLuong = new JLabel("Nồng Độ- hàm lượng:\r\n");
		lblThuoc_HamLuong.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_HamLuong.setBounds(314, 97, 131, 30);
		pnlTTThuoc.add(lblThuoc_HamLuong);


		JLabel lblThuoc_DBC = new JLabel("Thành phần:");
		lblThuoc_DBC.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThuoc_DBC.setBounds(10, 62, 89, 30);
		pnlTTThuoc.add(lblThuoc_DBC);

		txtThuoc_DBC = new JTextField();
		txtThuoc_DBC.setEditable(false);
		txtThuoc_DBC.setColumns(10);
		txtThuoc_DBC.setBounds(98, 62, 208, 30);
		pnlTTThuoc.add(txtThuoc_DBC);

		JLabel lblHoatChat = new JLabel("Xuất xứ:");
		lblHoatChat.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHoatChat.setBounds(10, 97, 89, 30);
		pnlTTThuoc.add(lblHoatChat);

		txtHoatChat = new JTextField();
		txtHoatChat.setEditable(false);
		txtHoatChat.setColumns(10);
		txtHoatChat.setBounds(98, 97, 208, 30);
		pnlTTThuoc.add(txtHoatChat);

		txtHamLuong = new JTextField();
		txtHamLuong.setEditable(false);
		txtHamLuong.setBounds(434, 97, 198, 30);
		pnlTTThuoc.add(txtHamLuong);
		txtHamLuong.setColumns(10);

		lblAnhThuoc = new JLabel("");
		lblAnhThuoc.setToolTipText("ảnh thuốc.");
		lblAnhThuoc.setForeground(Color.WHITE);
		lblAnhThuoc.setBackground(Color.WHITE);
		lblAnhThuoc.setBounds(98, 139, 297, 88);
		pnlTTThuoc.add(lblAnhThuoc);

		JLabel lblHinhAnh = new JLabel("Hình Ảnh:\r\n");
		lblHinhAnh.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHinhAnh.setBounds(10, 160, 89, 30);
		pnlTTThuoc.add(lblHinhAnh);

		JLabel lblNhomThuoc = new JLabel("Nhóm thuốc:");
		lblNhomThuoc.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNhomThuoc.setBounds(10, 23, 89, 30);
		pnlTTThuoc.add(lblNhomThuoc);
		
		cmbNhomThuoc = new JComboBox();
		cmbNhomThuoc.setBounds(98, 23, 206, 30);
		pnlTTThuoc.add(cmbNhomThuoc);

		JPanel pnlDsthuoc = new JPanel();
		pnlDsthuoc.setBackground(new Color(176, 224, 230));
		pnlDsthuoc.setBounds(10, 313, 1254, 206);
		pnl_Thuoc.add(pnlDsthuoc);
		pnlDsthuoc.setLayout(null);

		JPanel pnl_4_Thuoc = new JPanel();

		pnl_4_Thuoc.setBounds(10, 11, 603, 191);
		pnlDsthuoc.add(pnl_4_Thuoc);
		pnl_4_Thuoc.setLayout(null);
		/*
		 * Tạo Table
		 */

		JScrollPane scrollPaneThuoc = new JScrollPane();
		scrollPaneThuoc.setToolTipText("qq\r\n");
		scrollPaneThuoc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneThuoc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneThuoc.setBounds(10, 11, 583, 168);
		pnl_4_Thuoc.add(scrollPaneThuoc);
		String[] colsname= {"STT","Mã Thuốc","Số Đăng Ký","Tên Thuốc","Phân Loại","Nhóm Thuốc","Nồng Độ/Hàm Lượng","Xuất Xứ","Thành Phần","Quy Cách","Tiêu Chuẩn","Nhà Cung Cấp","Ngày Sản Xuất"
				,"Hạn Sử Dụng","Đơn Vị Tính","Giá Nhập","Đơn Giá","Số Lượng Nhập","Trạng Thái"};

		tbModel=new DefaultTableModel(colsname,0);
		tblThuoc = new JTable(tbModel);
		tblThuoc.setDefaultEditor(Object.class, null);
		scrollPaneThuoc.setViewportView(tblThuoc);
		tblThuoc.getColumnModel().getColumn(0).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(1).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(2).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(3).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(4).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(5).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(6).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(7).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(8).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(9).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(10).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(11).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(12).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(13).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(14).setPreferredWidth(120);
		tblThuoc.getColumnModel().getColumn(15).setPreferredWidth(120);

		tblThuoc.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tblThuoc.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				try {
					hienTable();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		
		// Panel chứa giỏ hàng
		JPanel pnlGioHang = new JPanel();
		pnlGioHang.setBorder(new TitledBorder(new EtchedBorder(), "Giỏ Hàng", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		pnlGioHang.setBounds(636, 11, 608, 191); // Điều chỉnh tọa độ phù hợp
		pnlDsthuoc.add(pnlGioHang);
		pnlGioHang.setLayout(null);

		// Tạo Table giỏ hàng
		JScrollPane scrollPaneGioHang = new JScrollPane();
		scrollPaneGioHang.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneGioHang.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneGioHang.setBounds(10, 21, 588, 159);
		pnlGioHang.add(scrollPaneGioHang);

		// Tạo cột cho bảng giỏ hàng
		String[] gioHangCols = {"STT", "Mã Thuốc", "Tên Thuốc", "Số Lượng", "Giá Nhập", "Ngày Sản Xuất", "Hạn Sử Dụng"};
		DefaultTableModel gioHangModel = new DefaultTableModel(gioHangCols, 0);
		tblGioHang = new JTable(gioHangModel);

		// Điều chỉnh bảng
		tblGioHang.getColumnModel().getColumn(0).setPreferredWidth(50);  // STT
		tblGioHang.getColumnModel().getColumn(1).setPreferredWidth(120); // Mã Thuốc
		tblGioHang.getColumnModel().getColumn(2).setPreferredWidth(150); // Tên Thuốc
		tblGioHang.getColumnModel().getColumn(3).setPreferredWidth(100); // Số Lượng
		tblGioHang.getColumnModel().getColumn(4).setPreferredWidth(100); // Giá Nhập
		tblGioHang.getColumnModel().getColumn(5).setPreferredWidth(150); // Ngày Sản Xuất
		tblGioHang.getColumnModel().getColumn(6).setPreferredWidth(150); // Hạn Sử Dụng
		tblGioHang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		scrollPaneGioHang.setViewportView(tblGioHang);

		
		JPanel pnlTitile = new JPanel();
		pnlTitile.setBackground(new Color(176, 224, 230));
		pnlTitile.setBounds(18, 11, 1295, 48);
		pnl_Thuoc.add(pnlTitile);
		pnlTitile.setLayout(null);

		JLabel lblTitiel = new JLabel("NHẬP THUỐC");
		lblTitiel.setBounds(0, 13, 1285, 28);
		pnlTitile.add(lblTitiel);
		lblTitiel.setForeground(Color.RED);
		lblTitiel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitiel.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitiel.setBackground(Color.RED);

		pnlChucNang = new JPanel();
		pnlChucNang.setBackground(new Color(176, 224, 230));
		pnlChucNang.setBorder(new TitledBorder(null, "Ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlChucNang.setBounds(10, 521, 1303, 110);
		pnl_Thuoc.add(pnlChucNang);
		pnlChucNang.setLayout(null);

		btnThem = new JButton("Nhập");
		btnThem.setBackground(Color.LIGHT_GRAY);
		btnThem.setHorizontalAlignment(SwingConstants.LEFT);
		btnThem.setIcon(new ImageIcon("Hinh\\add.png"));
		btnThem.setBounds(10, 43, 102, 30);
		pnlChucNang.add(btnThem);
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 12));

		btnThemVaoGio = new JButton("Thêm vào giỏ hàng");
		btnThemVaoGio.setBounds(521, 43, 200, 30); // Tùy chỉnh tọa độ
		pnlChucNang.add(btnThemVaoGio);
		
		JButton btnThoat = new JButton("Thoát");
		btnThoat.setBackground(Color.LIGHT_GRAY);
		btnThoat.setIcon(new ImageIcon("Hinh\\exit.png"));
		btnThoat.setHorizontalAlignment(SwingConstants.LEFT);
		btnThoat.setBounds(1175, 42, 118, 30);
		pnlChucNang.add(btnThoat);
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThoat.setFont(new Font("Times New Roman", Font.BOLD, 15));

		btnLamMoi = new JButton("LÀM MỚI");
		btnLamMoi.setBackground(Color.LIGHT_GRAY);
		btnLamMoi.setIcon(new ImageIcon("Hinh\\refresh.png"));
		btnLamMoi.setHorizontalAlignment(SwingConstants.LEFT);
		btnLamMoi.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnLamMoi.setBounds(238, 42, 132, 30);
		pnlChucNang.add(btnLamMoi);
		
		btnLamMoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lamMoi();
			}
		});
		btnThem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            int rowCount = gioHangModel.getRowCount();
		            if (rowCount == 0) {
		                JOptionPane.showMessageDialog(null, "Giỏ hàng trống! Vui lòng thêm thuốc vào giỏ hàng.");
		                return;
		            }

		            for (int i = 0; i < rowCount; i++) {
		                int maThuoc = Integer.parseInt(gioHangModel.getValueAt(i, 1).toString());
		                String tenThuoc = gioHangModel.getValueAt(i, 2).toString();
		                int soLuongNhap = Integer.parseInt(gioHangModel.getValueAt(i, 3).toString());
		                double giaNhap = Double.parseDouble(gioHangModel.getValueAt(i, 4).toString());
		                java.util.Date ngaySX = new SimpleDateFormat("dd-MM-yyyy").parse(gioHangModel.getValueAt(i, 5).toString());
		                java.util.Date hanSD = new SimpleDateFormat("dd-MM-yyyy").parse(gioHangModel.getValueAt(i, 6).toString());

		 
		                Thuoc thuoc = dao.getThuocbyMa(maThuoc);
		                if (thuoc != null) {
		                    java.util.Date ngayHienTai = new java.util.Date();
		                    long chenhLechNgay = (thuoc.getHanSuDung().getTime() - ngayHienTai.getTime()) / (1000 * 60 * 60 * 24);

		                    if (chenhLechNgay < 30) {
		                        double chiPhiLo = thuoc.getGiaNhap() * thuoc.getSoLuongNhap();
		                        JOptionPane.showMessageDialog(null, "Thuốc '" + tenThuoc + "' đã hết hạn. Tính lỗ: " + chiPhiLo);

		                        thuoc.setNgaySanXuat(ngaySX);
		                        thuoc.setHanSuDung(hanSD);
		                        thuoc.setSoLuongNhap(soLuongNhap);
		                    } else {
		                        int soLuongHienTai = thuoc.getSoLuongNhap();
		                        thuoc.setSoLuongNhap(soLuongHienTai + soLuongNhap);
		                    }

		                    dao.updateThuoc(thuoc);
		                }
		            }

		            docDuLieu("");
		            JOptionPane.showMessageDialog(null, "Nhập thuốc thành công!");
		            gioHangModel.setRowCount(0);
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
		        }
		    }
		});


		btnThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FrmManHinhChinh.tabbedPane.remove(contentPane);
			}
		});
		
		btnThemVaoGio.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Lấy dữ liệu từ ô text
		            int maThuoc = Integer.parseInt(txtThuoc_Ma.getText());
		            String tenThuoc = txtThuoc_Ten.getText();
		            String nhaCungCap = cmbNCC.getSelectedItem().toString();
		            double giaNhap = Double.parseDouble(txtThuoc_GiaNhap.getText());

		            java.util.Date ngayHSD = dateHanSD.getDate();
		            java.util.Date ngayHienTai = new java.util.Date();

		            long chenhLechNgay = (ngayHSD.getTime() - ngayHienTai.getTime()) / (1000 * 60 * 60 * 24);

		            if (chenhLechNgay < 30) { // Thuốc gần hết hạn hoặc đã hết hạn
		                JOptionPane.showMessageDialog(null, "Thuốc đã hết hạn hoặc gần hết hạn!\nNhập lại thông tin mới.");

		                // Mở giao diện nhập lại thông tin
		                FrmNhapLaiThongTin frmNhapLai = new FrmNhapLaiThongTin(null, tenThuoc, nhaCungCap);
		                frmNhapLai.setVisible(true);

		                if (frmNhapLai.isConfirmed()) {
		                    // Lấy dữ liệu nhập lại
		                    int soLuongMoi = Integer.parseInt(frmNhapLai.getSoLuong());
		                    if (soLuongMoi < 50) {
		                        JOptionPane.showMessageDialog(null, "Số lượng nhập phải lớn hơn hoặc bằng 50!");
		                        return; // Dừng lại nếu số lượng không đủ
		                    }

		                    java.util.Date ngaySX = frmNhapLai.getNgaySX();
		                    java.util.Date hanSD = frmNhapLai.getHanSD();

		                    gioHangModel.addRow(new Object[] {
		                        gioHangModel.getRowCount() + 1, maThuoc, tenThuoc, soLuongMoi, giaNhap,
		                        new SimpleDateFormat("dd-MM-yyyy").format(ngaySX),
		                        new SimpleDateFormat("dd-MM-yyyy").format(hanSD)
		                    });

		                    JOptionPane.showMessageDialog(null, "Thêm vào giỏ hàng thành công!");
		                }
		            } else {
		                // Trường hợp thuốc còn hạn sử dụng
		                String soLuongStr = JOptionPane.showInputDialog(null, "Nhập số lượng:");
		                if (soLuongStr != null && !soLuongStr.isEmpty()) {
		                    int soLuong = Integer.parseInt(soLuongStr);
		                    if (soLuong < 50) {
		                        JOptionPane.showMessageDialog(null, "Số lượng nhập phải lớn hơn hoặc bằng 50!");
		                        return; // Dừng lại nếu số lượng không đủ
		                    }

		                    gioHangModel.addRow(new Object[] {
		                        gioHangModel.getRowCount() + 1, maThuoc, tenThuoc, soLuong, giaNhap,
		                        new SimpleDateFormat("dd-MM-yyyy").format(dateNgaySX.getDate()),
		                        new SimpleDateFormat("dd-MM-yyyy").format(ngayHSD)
		                    });

		                    JOptionPane.showMessageDialog(null, "Thêm vào giỏ hàng thành công!");
		                }
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Lỗi: Vui lòng nhập số lượng hợp lệ!");
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
		        }
		    }
		});		
		docDuLieu("");
	}
	/** Dùng để lấy dự liệu từ sql lên bảng
	 * 
	 */
	public void docDuLieu(String tenNCC) {
	    int d = 1;
	    tbModel.setRowCount(0); // Xóa dữ liệu cũ trên bảng
		dao.getTenCungCaps().forEach(x->{
		if(listNCC.contains(x)==false) {
			cboModeNCC.addElement(x);
			cmbNCC.setModel(cboModeNCC);	
			listNCC.add(x);
		}
		if(listTimNCC.contains(x)==false) {
			listTimNCC.add(x);
		}
		});
	    // Lấy danh sách thuốc từ DAO (theo nhà cung cấp hoặc tất cả)
	    List<Thuoc> list;
	    list = dao.getThuocbynhaCungCap(tenNCC);
		for(Thuoc x:list) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		tbModel.addRow(new Object[] {
				d++,x.getMaThuoc(),x.getSoDangky(),x.getTenThuoc(),x.getPhanLoai(),x.getNhomThuoc(),x.getHamLuong(),x.getHoatChat(),x.getDangBaoChe(),
				x.getQuyCach(),x.getTieuChuan(),x.getNhaCungcap().getTenNCC(),df.format(x.getNgaySanXuat()) ,df.format(x.getHanSuDung() ),x.getDonViTinh(),x.getGiaNhap(),
				x.getDonGia(),x.getSoLuongNhap(),x.getTrangThai()
		});
		if(listPhanLoai.contains(x.getPhanLoai())==false) {
			cboModePhanLoai.addElement(x.getPhanLoai());
			cmbPhanLoai.setModel(cboModePhanLoai);	
			listPhanLoai.add(x.getPhanLoai());
		}
		if(listDonViTinh.contains(x.getDonViTinh())==false) {
			cboModeDonViTinh.addElement(x.getDonViTinh());
			cmbDonViTinh.setModel(cboModeDonViTinh);	
			listDonViTinh.add(x.getDonViTinh());
		}
		if(listNhomThuoc.contains(x.getNhomThuoc())==false) {
			cboModeNhomThuoc.addElement(x.getNhomThuoc());
			cmbNhomThuoc.setModel(cboModeNhomThuoc);
			listNhomThuoc.add(x.getNhomThuoc());
		}
		if(listTrangThai.contains(x.getTrangThai())==false) {
			cboModeTrangThai.addElement(x.getTrangThai());
			cmbTrangThai.setModel(cboModeTrangThai);	
			listTrangThai.add(x.getTrangThai());
			}
		}
	}

	/**
	 * Dùng để hiện thị các text và combobox từ bảng lên
	 */
	public void hienTable() {
	    int row = tblThuoc.getSelectedRow(); // Lấy chỉ số dòng được chọn
	    if (row >= 0) {
	        // Lấy mã thuốc từ bảng
	        int maThuoc = Integer.parseInt(tbModel.getValueAt(row, 1).toString());

	        // Tìm thuốc theo mã thuốc trong danh sách hiện tại
	        Thuoc thuoc = dao.getThuocbyMa(maThuoc);

	        if (thuoc != null) {
	            // Hiển thị thông tin thuốc lên các ô text và combobox
	            txtThuoc_Ma.setText(String.valueOf(thuoc.getMaThuoc()));
	            txtThuoc_SDK.setText(thuoc.getSoDangky());
	            txtThuoc_Ten.setText(thuoc.getTenThuoc());
	            cmbPhanLoai.setSelectedItem(thuoc.getPhanLoai());
	            cmbNhomThuoc.setSelectedItem(thuoc.getNhomThuoc());
	            txtHamLuong.setText(thuoc.getHamLuong());
	            txtHoatChat.setText(thuoc.getHoatChat());
	            txtThuoc_DBC.setText(thuoc.getDangBaoChe());
	            txtThuoc_QuyCach.setText(thuoc.getQuyCach());
	            txtThuoc_TieuChuan.setText(thuoc.getTieuChuan());
	            cmbNCC.setSelectedItem(thuoc.getNhaCungcap().getTenNCC());
	            dateNgaySX.setDate(thuoc.getNgaySanXuat());
	            dateHanSD.setDate(thuoc.getHanSuDung());
	            cmbDonViTinh.setSelectedItem(thuoc.getDonViTinh());
	            txtThuoc_GiaNhap.setText(String.valueOf(thuoc.getGiaNhap()));
	            txtThuoc_DonGia.setText(String.valueOf(thuoc.getDonGia()));
	            txtThuoc_SLN.setText(String.valueOf(thuoc.getSoLuongNhap()));
	            cmbTrangThai.setSelectedItem(thuoc.getTrangThai());

	            // Hiển thị hình ảnh
	            try {
	                byte[] img = thuoc.getHinhAnh();
	                if (img != null) {
	                    ImageIcon ima = new ImageIcon(new ImageIcon(img).getImage()
	                            .getScaledInstance(lblAnhThuoc.getWidth(), lblAnhThuoc.getHeight(), Image.SCALE_SMOOTH));
	                    lblAnhThuoc.setIcon(ima);
	                } else {
	                    lblAnhThuoc.setIcon(null); // Xóa hình nếu không có
	                }
	            } catch (Exception e) {
	                lblAnhThuoc.setIcon(null);
	                e.printStackTrace();
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Không tìm thấy thuốc có mã: " + maThuoc);
	        }
	    }
	}

	/**
	 * Dùng để xóa dữ liệu bảng
	 */
	public void xoaTable() {
		tbModel.addRow(new Object[] {

		});
		DefaultTableModel tbl = (DefaultTableModel) tblThuoc.getModel();
		tbl.getDataVector().removeAllElements();	
	}
	/**
	 * Dùng để làm mới giao diện
	 */
	public void lamMoi() {
		txtThuoc_Ma.setText("");
		txtThuoc_SDK.setText("");
		txtThuoc_Ten.setText("");
		cmbPhanLoai.setSelectedItem("");
		txtHoatChat.setText("");
		txtHamLuong.setText("");	
		txtThuoc_DBC.setText("");
		txtThuoc_QuyCach.setText("");
		txtThuoc_TieuChuan.setText("");
		cmbNCC.setSelectedItem("");
		cmbDonViTinh.setSelectedItem("");
		txtThuoc_GiaNhap.setText("");
		txtThuoc_DonGia.setText("");
		txtThuoc_SLN.setText("");
		cmbTrangThai.setSelectedItem("");
		cmbNCC.setSelectedItem("");
		cmbDonViTinh.setSelectedItem("");
		cmbNhomThuoc.setSelectedItem("");
		cmbTrangThai.setSelectedItem("");
		txtHamLuong.setEditable(false);
		txtHoatChat.setEditable(false);
		txtThuoc_DBC.setEditable(false);
		txtThuoc_DonGia.setEditable(false);
		txtThuoc_GiaNhap.setEditable(false);
		txtThuoc_Ma.setEditable(false);
		txtThuoc_QuyCach.setEditable(false);
		txtThuoc_SDK.setEditable(false);
		txtThuoc_SLN.setEditable(false);
		txtThuoc_Ten.setEditable(false);
		txtThuoc_TieuChuan.setEditable(false);
		xoaTable();
		docDuLieu("");
		lblAnhThuoc.setIcon(null);
		trangThaiSua=0;
		trangThaiThem=0;
		btnThem.setEnabled(true);
	
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConectDatabase.getInstance().connect();
					FrmThuoc frame = new FrmThuoc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
