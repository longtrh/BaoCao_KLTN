package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import dao.NhanVienDAO;
import entities.DiaChi;
import entities.NhanVien;
import entities.TaiKhoan;

import java.awt.SystemColor;

public class FrmTimKiemNV extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5201032984984305728L;
	private JTextField txtTimKiem;
	private JTextField txtMaNV;
	private static JTextField txtHo;
	private static JTextField txtTen;
	private static JTextField txtSoDT;
	public static JTextField txtMaDiaChi;
	private static JTextField txtCMND;
	public static JTextField txtTenTK;
	private static DefaultTableModel model;
	private static JTable table;
	private JButton btnTimKiem;
	public static JPanel frmTimKiemNV;
	private JRadioButton radMa;
	private JRadioButton radTen;
	private JRadioButton radSoDT;
	private JRadioButton radCaLamViec;
	public static JButton btnThem;
	private JButton btnThoat;
	private JButton btnLamMoi;
	@SuppressWarnings("rawtypes")
	private static JComboBox cboGioiTinh;
	@SuppressWarnings("rawtypes")
	private static JComboBox cboCaLamViec;
	@SuppressWarnings("rawtypes")
	private JComboBox cboTrangThai;
	@SuppressWarnings("rawtypes")
	private static JComboBox cboLoaiNV;
	private static JDateChooser dateChooser;
	public static boolean check = false;
	static NhanVienDAO nvDao = new NhanVienDAO();
	FrmThemTaiKhoan frmThemTaiKhoan = new FrmThemTaiKhoan();
	FrmThemDiaChi frmThemDiaChi = new FrmThemDiaChi();
	public static JTextField txtDiaChi;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FrmTimKiemNV() {
		frmTimKiemNV = new JPanel();
		frmTimKiemNV.setLayout(null);
		ButtonGroup buttonGroup = new ButtonGroup();

		/**
		 * Thoat tab quan ly nhan vien
		 */

		String[] tb = new String[] { "STT", "Mã NV", "Họ", "Tên", "Ngày sinh", "Giới tính", "CMND", "Số ĐT",
				"Ca làm việc", "Mã địa chỉ", "Tên TK", "Loại NV", "Trạng thái" };
		model = new DefaultTableModel(tb, 0);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 1317, 642);
		frmTimKiemNV.add(panel);
		panel.setLayout(null);

		JLabel lblMaNV = new JLabel("Mã NV:");
		lblMaNV.setBounds(25, 73, 50, 25);
		panel.add(lblMaNV);
		lblMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtMaNV.setBounds(103, 73, 185, 25);
		panel.add(txtMaNV);
		txtMaNV.setToolTipText("Nhập mã nhân viên");
		txtMaNV.setColumns(10);

		txtMaNV.setEditable(false);

		txtCMND = new JTextField();
		txtCMND.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtCMND.setBounds(452, 109, 185, 25);
		panel.add(txtCMND);
		txtCMND.setEditable(false);
		txtCMND.setToolTipText("Nhập chứng minh nhân dân");
		txtCMND.setColumns(10);
		txtCMND.setEditable(true);
		txtCMND.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!((e.getKeyChar() >= 48 && e.getKeyChar() <= 57) || e.getKeyChar() == 8)) {
					e.consume();
				}
			}
		});

		JLabel lblCMND = new JLabel("CMND:");
		lblCMND.setBounds(375, 109, 50, 25);
		panel.add(lblCMND);
		lblCMND.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		JLabel lblTenTK = new JLabel("Tên TK:");
		lblTenTK.setBounds(682, 147, 84, 25);
		panel.add(lblTenTK);
		lblTenTK.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		JLabel lblHo = new JLabel("Họ:");
		lblHo.setBounds(375, 73, 24, 25);
		panel.add(lblHo);
		lblHo.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		txtHo = new JTextField();
		txtHo.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtHo.setBounds(452, 73, 185, 25);
		panel.add(txtHo);
		txtHo.setEditable(false);
		txtHo.setToolTipText("Nhập họ");
		txtHo.setColumns(10);

		txtHo.setEditable(true);

		txtTenTK = new JTextField();
		txtTenTK.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtTenTK.setBounds(775, 147, 185, 25);
		panel.add(txtTenTK);
		txtTenTK.setEditable(false);
		txtTenTK.setColumns(10);

		JLabel lblLoaiNV = new JLabel("Loại NV:");
		lblLoaiNV.setBounds(1000, 109, 73, 25);
		panel.add(lblLoaiNV);
		lblLoaiNV.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		JLabel lblTen = new JLabel("Tên:");
		lblTen.setBounds(682, 75, 53, 25);
		panel.add(lblTen);
		lblTen.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		txtTen = new JTextField();
		txtTen.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtTen.setBounds(775, 75, 185, 25);
		panel.add(txtTen);
		txtTen.setEditable(false);
		txtTen.setToolTipText("Nhập tên");
		txtTen.setColumns(10);
		txtTen.setEditable(true);

		cboLoaiNV = new JComboBox<String>();
		cboLoaiNV.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		cboLoaiNV.setBounds(1101, 109, 185, 25);
		panel.add(cboLoaiNV);
		cboLoaiNV.setToolTipText("Chọn loại nhân viên");
		cboLoaiNV.addItem("Nhân viên");
		cboLoaiNV.addItem("Quản lý");

		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setBounds(1000, 73, 73, 25);
		panel.add(lblNgaySinh);
		lblNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		dateChooser = new JDateChooser();
		dateChooser.setBounds(1101, 73, 185, 25);
		panel.add(dateChooser);
		dateChooser.getCalendarButton().setEnabled(false);
		dateChooser.setLocale(Locale.forLanguageTag("vi-VN"));
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.getCalendarButton().setEnabled(true);

		txtMaDiaChi = new JTextField();
		txtMaDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtMaDiaChi.setBounds(1101, 147, 185, 25);
		panel.add(txtMaDiaChi);
		txtMaDiaChi.setEditable(false);
		txtMaDiaChi.setToolTipText("Nhập địa chỉ");
		txtMaDiaChi.setColumns(10);

		JLabel lblMaDiaChi = new JLabel("Mã địa chỉ:");
		lblMaDiaChi.setBounds(1000, 147, 80, 25);
		panel.add(lblMaDiaChi);
		lblMaDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setBounds(682, 111, 84, 25);
		panel.add(lblGioiTinh);
		lblGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setBounds(375, 145, 73, 25);
		panel.add(lblTrangThai);
		lblTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		cboTrangThai = new JComboBox();
		cboTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		cboTrangThai.setBounds(452, 145, 185, 25);
		panel.add(cboTrangThai);
		cboTrangThai.addItem("Đang làm việc");
		cboTrangThai.addItem("Nghỉ việc");
		cboTrangThai.addItem("Tạm nghỉ việc");

		cboGioiTinh = new JComboBox<String>();
		cboGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		cboGioiTinh.setBounds(775, 111, 185, 25);
		panel.add(cboGioiTinh);
		cboGioiTinh.setToolTipText("Chọn giới tính");
		cboGioiTinh.addItem("Nam");
		cboGioiTinh.addItem("Nữ");
		cboGioiTinh.addItem("Khác");

		JLabel lblSoDT = new JLabel("Số ĐT:");
		lblSoDT.setBounds(25, 109, 50, 25);
		panel.add(lblSoDT);
		lblSoDT.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		txtSoDT = new JTextField();
		txtSoDT.setBounds(103, 109, 185, 25);
		panel.add(txtSoDT);
		txtSoDT.setEditable(false);
		txtSoDT.setToolTipText("Nhập số điện thoại");
		txtSoDT.setColumns(10);
		txtSoDT.setEditable(true);
		txtSoDT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!((e.getKeyChar() >= 48 && e.getKeyChar() <= 57) || e.getKeyChar() == 8)) {
					e.consume();
				}
			}
		});

		JLabel lblCaLamViec = new JLabel("Ca làm việc:");
		lblCaLamViec.setBounds(25, 147, 84, 25);
		panel.add(lblCaLamViec);
		lblCaLamViec.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		cboCaLamViec = new JComboBox<String>();
		cboCaLamViec.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		cboCaLamViec.setBounds(104, 147, 185, 25);
		panel.add(cboCaLamViec);
		cboCaLamViec.setToolTipText("Chọn ca làm việc");
		cboCaLamViec.addItem("1");
		cboCaLamViec.addItem("2");

		JLabel lblTitle = new JLabel("TÌM KIẾM NHÂN VIÊN");
		lblTitle.setBounds(512, 11, 278, 38);
		panel.add(lblTitle);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTitle.setForeground(Color.RED);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel pnlDanhSachNhanVien = new JPanel();
		pnlDanhSachNhanVien.setBounds(10, 237, 1297, 257);
		panel.add(pnlDanhSachNhanVien);
		pnlDanhSachNhanVien.setLayout(null);
		pnlDanhSachNhanVien.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Danh s\u00E1ch nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDanhSachNhanVien.setBackground(SystemColor.control);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 20, 1277, 226);
		pnlDanhSachNhanVien.add(scrollPane);
		table = new JTable(model);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane.setViewportView(table);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblDiaChi.setBounds(682, 181, 72, 25);
		panel.add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtDiaChi.setEditable(false);
		txtDiaChi.setBounds(775, 182, 509, 25);
		panel.add(txtDiaChi);
		txtDiaChi.setColumns(10);

		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 505, 1297, 114);
		panel.add(panel_3);
		panel_3.setBorder(
				new TitledBorder(null, "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setLayout(null);

		JLabel lblTimKiem = new JLabel("Nhập thông tin tìm kiếm:");
		lblTimKiem.setBounds(10, 11, 161, 30);
		panel_3.add(lblTimKiem);
		lblTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JLabel lblTimTheo = new JLabel("Tìm theo:");
		lblTimTheo.setBounds(10, 56, 63, 30);
		panel_3.add(lblTimTheo);
		lblTimTheo.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		radMa = new JRadioButton("Mã");
		radMa.setBounds(79, 48, 63, 23);
		panel_3.add(radMa);
		radMa.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		buttonGroup.add(radMa);

		radTen = new JRadioButton("Tên");
		radTen.setBounds(79, 74, 63, 23);
		panel_3.add(radTen);
		radTen.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		buttonGroup.add(radTen);

		radCaLamViec = new JRadioButton("Ca làm việc");
		radCaLamViec.setBounds(144, 76, 94, 23);
		panel_3.add(radCaLamViec);
		radCaLamViec.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		buttonGroup.add(radCaLamViec);

		radSoDT = new JRadioButton("Số ĐT");
		radSoDT.setBounds(144, 48, 94, 23);
		panel_3.add(radSoDT);
		radSoDT.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		buttonGroup.add(radSoDT);

		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(165, 15, 232, 25);
		panel_3.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBounds(277, 56, 120, 30);
		panel_3.add(btnTimKiem);
		btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnTimKiem.setIcon(new ImageIcon("Hinh\\search.png"));

		btnLamMoi = new JButton("Làm Mới");
		btnLamMoi.setBounds(407, 56, 120, 30);
		panel_3.add(btnLamMoi);
		btnLamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnLamMoi.setIcon(new ImageIcon("Hinh\\refresh.png"));
		btnThoat = new JButton("Thoát");
		btnThoat.setBounds(537, 56, 120, 30);
		panel_3.add(btnThoat);
		btnThoat.setForeground(Color.BLACK);
		btnThoat.setBackground(UIManager.getColor("InternalFrame.inactiveTitleGradient"));
		btnThoat.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnThoat.setIcon(new ImageIcon("Hinh\\close.png"));

		JLabel lblXinChao = new JLabel("Xin chào !");
		lblXinChao.setForeground(Color.RED);
		lblXinChao.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblXinChao.setBounds(1028, 44, 84, 22);
		panel.add(lblXinChao);

		JLabel lblTenNV = new JLabel("");
		lblTenNV.setForeground(Color.RED);
		lblTenNV.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenNV.setBounds(1122, 44, 185, 23);
		panel.add(lblTenNV);

		String tenNhanVienHienTai = nvDao.getTenNhanVienByTenTaiKhoan(FrmDangNhap.taiKhoan.getTenTaiKhoan());
		lblTenNV.setText(tenNhanVienHienTai);

		btnThoat.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnTimKiem.addActionListener(this);
		table.addMouseListener(this);

		docDuLieu();
	}

	public static void themNhanVien() {
		NhanVienDAO nhanVienDAO = new NhanVienDAO();
		NhanVien nhanVien = new NhanVien();
		nhanVien.setCaLamViec(cboCaLamViec.getSelectedItem().toString());
		nhanVien.setTen(txtTen.getText().trim());
		nhanVien.setHo(txtHo.getText().trim());

		Date ngaySinhsql = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String ngaySinhtxt = dateFormat.format(dateChooser.getDate());
			java.util.Date ngaySinh = dateFormat.parse(ngaySinhtxt);
			ngaySinhsql = new Date(ngaySinh.getTime());
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		nhanVien.setNgaySinh(ngaySinhsql);
		nhanVien.setGioiTinh(cboGioiTinh.getSelectedItem().toString());
		nhanVien.setCmnd(txtCMND.getText().trim());
		nhanVien.setSoDienThoai(txtSoDT.getText().trim());
		DiaChi diaChi = new DiaChi();
		diaChi.setMaDiaChi(Integer.parseInt(txtMaDiaChi.getText()));

		nhanVien.setDiaChi(diaChi);
		TaiKhoan taiKhoan = new TaiKhoan();
		taiKhoan.setTenTaiKhoan(txtTenTK.getText());
		nhanVien.setTaiKhoan(taiKhoan);
		String loaiNhanVien = cboLoaiNV.getSelectedItem().toString().trim();
		if (loaiNhanVien.equalsIgnoreCase("Nhân viên")) {
			nhanVien.setLoaiNhanVien("NV");

		} else {
			nhanVien.setLoaiNhanVien("QL");
		}
		nhanVien.setTrangThai("Đang làm việc");
		try {
			nhanVienDAO.themNhanVien(nhanVien);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		/**
		 * add employee
		 */
		if (obj.equals(btnThoat)) {
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát !") == JOptionPane.YES_OPTION) {
				FrmManHinhChinh.tabbedPane.remove(frmTimKiemNV);
			}
		}
		
		/**
		 * staff search
		 */
		else if (obj.equals(btnTimKiem)) {
			if (radMa.isSelected() == false && radTen.isSelected() == false && radSoDT.isSelected() == false
					&& radCaLamViec.isSelected() == false && txtTimKiem.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin và chọn loại tìm kiếm !", "Thông báo",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("Hinh\\warning.png"));
			} else if (txtTimKiem.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm !", "Thông báo",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("Hinh\\warning.png"));
			} else if (radMa.isSelected() == false && radTen.isSelected() == false && radSoDT.isSelected() == false
					&& radCaLamViec.isSelected() == false) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn loại tìm kiếm !", "Thông báo",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("Hinh\\warning.png"));
			} else {
				if (radMa.isSelected()) {
					try {
						int ma = Integer.parseInt(txtTimKiem.getText().trim());
						getTimKiemNhanVienById(ma);
					} catch (Exception e2) {
						if (!txtMaNV.getText().equalsIgnoreCase("")) {
							JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên !", "Thông báo",
									JOptionPane.ERROR_MESSAGE, new ImageIcon("Hinh\\warning.png"));
							txtTimKiem.requestFocus();
							txtTimKiem.selectAll();
						} else {
							JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi !", "Thông báo",
									JOptionPane.ERROR_MESSAGE, new ImageIcon("Hinh\\warning.png"));
							txtTimKiem.requestFocus();
							txtTimKiem.selectAll();
						}
					}
				} else if (radTen.isSelected()) {
					String ten = txtTimKiem.getText().trim();
					getTimKiemNhanVienByTen(ten);
				} else if (radSoDT.isSelected()) {
					String sdt = txtTimKiem.getText().trim();
					getTimKiemNhanVienBySDT(sdt);
				} else if (radCaLamViec.isSelected()) {
					String caLamViec = txtTimKiem.getText().trim();
					getTimKiemNhanVienByCaLamViec(caLamViec);
				}
			}
		} else if (obj.equals(btnLamMoi)) {
			xoaAllDataTable();
			docDuLieu();
			txtTimKiem.requestFocus();
			txtTimKiem.selectAll();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		int maNV = Integer.parseInt(model.getValueAt(row, 1).toString());
		try {
			NhanVien nv = nvDao.layThongTinNhanVien(maNV);
			txtMaNV.setText(maNV + "");
			txtHo.setText(nv.getHo());
			txtTen.setText(nv.getTen());
			dateChooser.setDate(nv.getNgaySinh());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String ngaySinhtxt = dateFormat.format(dateChooser.getDate());
			Date ngaySinhsql = null;
			try {
				java.util.Date ngaySinh = dateFormat.parse(ngaySinhtxt);
				ngaySinhsql = new Date(ngaySinh.getTime());
				dateChooser.setDate(ngaySinhsql);
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			cboGioiTinh.getModel().setSelectedItem(nv.getGioiTinh());
			txtSoDT.setText(nv.getSoDienThoai());
			txtCMND.setText(nv.getCmnd());
			txtTenTK.setText(nv.getTaiKhoan().getTenTaiKhoan());
			String loaiNV = nv.getLoaiNhanVien().trim();
			if (loaiNV.equalsIgnoreCase("NV")) {
				cboLoaiNV.getModel().setSelectedItem("Nhân viên");
			} else {
				cboLoaiNV.getModel().setSelectedItem("Quản lý");
			}
			txtMaDiaChi.setText(nv.getDiaChi().getMaDiaChi() + "");
			cboTrangThai.getModel().setSelectedItem(nv.getTrangThai());
			cboCaLamViec.getModel().setSelectedItem(nv.getCaLamViec());

			String diaChi = "";
			int maDC = Integer.parseInt(model.getValueAt(row, 9).toString());
			DiaChi dc = nvDao.layThongTinDiaChi(maDC);
			String soNha = dc.getSoNha();
			String tenDuong = dc.getTenDuong().trim();
			String phuong = dc.getPhuong().trim();
			String quan = dc.getQuan().trim();
			String thanhPho = dc.getThanhPho().trim();
			String quocGia = dc.getQuocGia().trim();
			if (!soNha.equalsIgnoreCase("") && !tenDuong.equalsIgnoreCase("")) {
				diaChi = soNha + ", " + tenDuong + ", " + phuong + ", " + quan + ", " + thanhPho + ", " + quocGia;
			} else {
				diaChi = phuong + ", " + quan + ", " + thanhPho + ", " + quocGia;
			}
			txtDiaChi.setText(diaChi);
		} catch (Exception e2) {
			//			e2.printStackTrace();
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public void getTimKiemNhanVienById(int id) {
		NhanVienDAO dao = new NhanVienDAO();
		ArrayList<NhanVien> list = null;
		try {
			list = (ArrayList<NhanVien>) dao.timkiemNhanVienByMa(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for (NhanVien nhanVien : list) {
			model.addRow(nhanVien.toVector()); /// ??????
		}
		for (int i = 0; i < model.getRowCount(); i++) {
			model.setValueAt(i + 1, i, 0);
		}
	}

	public void getTimKiemNhanVienBySDT(String sdt) {
		NhanVienDAO dao = new NhanVienDAO();
		ArrayList<NhanVien> list = null;
		try {
			list = (ArrayList<NhanVien>) dao.timkiemNhanVienBySDT(sdt);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for (NhanVien nhanVien : list) {
			model.addRow(nhanVien.toVector());
		}
		for (int i = 0; i < model.getRowCount(); i++) {
			model.setValueAt(i + 1, i, 0);
		}
	}

	public void getTimKiemNhanVienByTen(String ten) {
		NhanVienDAO dao = new NhanVienDAO();
		ArrayList<NhanVien> list = null;
		try {
			list = (ArrayList<NhanVien>) dao.timkiemNhanVienByTen(ten);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for (NhanVien nhanVien : list) {
			model.addRow(nhanVien.toVector());
		}
		for (int i = 0; i < model.getRowCount(); i++) {
			model.setValueAt(i + 1, i, 0);
		}
	}

	public void getTimKiemNhanVienByCaLamViec(String caLamViec) {
		NhanVienDAO dao = new NhanVienDAO();
		ArrayList<NhanVien> list = null;
		try {
			list = (ArrayList<NhanVien>) dao.timkiemNhanVienByCalamViec(caLamViec);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for (NhanVien nhanVien : list) {
			model.addRow(nhanVien.toVector());
		}
		for (int i = 0; i < model.getRowCount(); i++) {
			model.setValueAt(i + 1, i, 0);
		}
	}

	

	public static void docDuLieu() {
		try {
			List<NhanVien> list = nvDao.getAllNhanVien();
			int i = 0;
			for (NhanVien nhanVien : list) {
				i++;
				model.addRow(new Object[] { i + "", nhanVien.getMaNhanVien() + "", nhanVien.getHo().trim(),
						nhanVien.getTen().trim(), nhanVien.getNgaySinh(), nhanVien.getGioiTinh().trim(),
						nhanVien.getCmnd().trim(), nhanVien.getSoDienThoai().trim(), nhanVien.getCaLamViec(),
						nhanVien.getDiaChi().getMaDiaChi() + "".trim(), nhanVien.getTaiKhoan().getTenTaiKhoan(),
						nhanVien.getLoaiNhanVien(), nhanVien.getTrangThai().trim() });
			}
			table.setModel(model);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public boolean kiemTraDuLieu() {
		try {
			String ho = txtHo.getText().trim();
			if (ho.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập họ !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("Hinh\\warning.png"));
				txtHo.requestFocus();
				txtHo.selectAll();
				return false;
			}
			//			else if (ho.matches("") == false) {
			//				JOptionPane.showMessageDialog(this, "Họ gồm các ký tự chữ, ký tự đầu mỗi từ viết hoa !");
			//				txtHo.requestFocus();
			//				txtHo.selectAll();
			//				return false;
			//			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "Họ không hợp lệ !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
					new ImageIcon("Hinh\\warning.png"));
			return false;
		}

		try {
			String ten = txtTen.getText().trim();
			if (ten.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập tên !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("Hinh\\warning.png"));
				txtTen.requestFocus();
				txtTen.selectAll();
				return false;
			}
			//			else if (ten.matches("/^[A-Z][a-z]*$/") == false) {
			//				JOptionPane.showMessageDialog(this, "Tên gồm 1 chữ, ký tự đầu viết hoa !");
			//				txtTen.requestFocus();
			//				txtTen.selectAll();
			//				return false;
			//			}
		} catch (Exception e) {
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String ngaySinhtxt = dateFormat.format(dateChooser.getDate());
			Date ngaySinhsql = null;
			String year = "";
			int yearsql = 0;
			try {
				java.util.Date ngaySinh = dateFormat.parse(ngaySinhtxt);
				ngaySinhsql = new Date(ngaySinh.getTime());
				//				System.out.println(ngaySinhsql);
				year = ngaySinhsql.toString().substring(0, 4);
				yearsql = Integer.parseInt(year);
				//				System.out.print("ngaySinhsql:" + ngaySinhsql);
				//				System.out.print("ngaySinhtxt:"+ngaySinhtxt);
				//				System.out.print("ngaySinh:"+ngaySinh);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			if (ngaySinhsql.toString().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày sinh !", "Thông báo !",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("Hinh\\warning.png"));
				dateChooser.requestFocus();
				return false;
			} else if (LocalDate.now().getYear() - yearsql < 18) {
				JOptionPane.showMessageDialog(this, "Tuổi của bạn phải >= 18 !", "Thông báo !",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("Hinh\\warning.png"));
				dateChooser.requestFocus();
				return false;
			} else if (LocalDate.now().getYear() - yearsql > 60) {
				JOptionPane.showMessageDialog(this, "Tuổi của bạn phải <= 60 !", "Thông báo !",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("Hinh\\warning.png"));
				dateChooser.requestFocus();
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ !", "Thông báo !", JOptionPane.ERROR_MESSAGE,
					new ImageIcon("Hinh\\warning.png"));
			return false;
		}
		//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//		String ngaySinhtxt = dateFormat.format(dateChooser.getDate());
		//		Date ngaySinhsql = null;
		//		try {
		//			java.util.Date ngaySinh = dateFormat.parse(ngaySinhtxt);
		//			ngaySinhsql = new Date(ngaySinh.getTime());
		//		} catch (Exception e2) {
		//			e2.printStackTrace();
		//		}

		//		String gioiTinh = cboGioiTinh.getSelectedItem().toString();

		String soDT = txtSoDT.getText().trim();
		try {
			if (soDT.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại !", "Thông báo !",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("Hinh\\warning.png"));
				txtSoDT.requestFocus();
				txtSoDT.selectAll();
				return false;
			} else if (soDT.matches("^[0]\\d{9}$") == false) {
				JOptionPane.showMessageDialog(this, "Số điện thoại gồm 10 số, bắt đầu bằng số 0 !", "Thông báo !",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("Hinh\\warning.png"));
				txtSoDT.requestFocus();
				txtSoDT.selectAll();
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ !", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("Hinh\\warning.png"));
			txtSoDT.requestFocus();
			txtSoDT.selectAll();
			return false;
		}
		String cmnd = txtCMND.getText().trim();
		try {
			if (cmnd.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập chứng minh nhân dân !", "Thông báo !",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("Hinh\\warning.png"));
				txtCMND.requestFocus();
				txtCMND.selectAll();
				return false;
			} else if (cmnd.matches("^[0-9]{9}$") == false) {
				JOptionPane.showMessageDialog(this, "Chứng minh nhân dân gồm 8 chữ số !", "Thông báo !",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("Hinh\\warning.png"));
				txtCMND.requestFocus();
				txtCMND.selectAll();
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Chứng minh nhân dân không hợp lệ !", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("Hinh\\warning.png"));
			txtCMND.requestFocus();
			txtCMND.selectAll();
			return false;
		}
		//		String tenTK = FrmThemTaiKhoan.txtTenTaiKhoan.getText().trim();
		txtTenTK.setText(null);

		//		int maDiaChi = nvDao.getMaDiaChiMax();
		txtMaDiaChi.setText(null);
		//		String trangThai = cboTrangThai.getSelectedItem().toString();

		//		String caLamViec = cboCaLamViec.getSelectedItem().toString();
		String trangThai = cboTrangThai.getSelectedItem().toString().trim();
		if (trangThai.equalsIgnoreCase("Nghỉ việc") || trangThai.equalsIgnoreCase("Tạm nghỉ việc")) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn trạng thái là \"Đang làm việc\" !", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("Hinh\\warning.png"));
			return false;
		} else {

			check = true;
		}
		return true;
	}

	public static void xoaAllDataTable() {
		model = (DefaultTableModel) table.getModel();
		model.getDataVector().removeAllElements();

	}


}
