package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import java.awt.Label;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import com.toedter.components.JSpinField;

import dao.DonDatThuocDAO;
import dao.HoaDonDAO;
import entities.CT_DonDatThuoc;
import dao.KhachHangDAO;
import entities.DonDatThuoc;
import entities.KhachHang;

import javax.swing.SpinnerNumberModel;

public class FrmTimDonDat extends JFrame {

	public static JPanel contentPane;
	private JTextField txtMa;
	private JTextField txtTimKiem;
	private JTextField txtTenKH;
	private JTextField txtNVLap;
	private JTable tblHoaDon;
	private JTextField txtTongTien;
	private JTable tblChiTiet;
	private JButton btnLapHoaDon;
	private DefaultTableModel tblModelHoaDon = new DefaultTableModel();
	private DefaultTableModel tblModelChiTiet = new DefaultTableModel();
	private DonDatThuocDAO dao= new DonDatThuocDAO();
	private KhachHangDAO khdao= new KhachHangDAO();
	private JTextField txtNgayLap;
	private JComboBox cmbTim;
	private JRadioButton radMaHD,radTenKH,radTenNV,radNgayLap;
	private DefaultComboBoxModel cboModetenKH= new DefaultComboBoxModel();
	private List<String> listtenKH = new ArrayList<String>();
	private DefaultComboBoxModel cboModetenNV= new DefaultComboBoxModel();
	private List<String> listtenNV = new ArrayList<String>();
	private DefaultComboBoxModel cboModema= new DefaultComboBoxModel();
	private List<String> listma = new ArrayList<String>();
	private DefaultComboBoxModel cboModeNgayLap= new DefaultComboBoxModel();
	private List<String> listNgayLap = new ArrayList<String>();
	private FrmLapHoaDon frmLapHoaDon;
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrmQuanLyHoaDon frame = new FrmQuanLyHoaDon();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public FrmTimDonDat() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("Hinh\\pm.png"));
		setType(Type.POPUP);
		setResizable(false);
		setForeground(new Color(176, 224, 230));
		setBackground(new Color(176, 224, 230));
		setTitle("Phần mềm quản lý nhà thuốc Tây Nam");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 662);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnlThongTinHD = new JPanel();
		pnlThongTinHD.setBackground(new Color(176, 224, 230));
		pnlThongTinHD.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Th\u00F4ng tin h\u00F3a \u0111\u01A1n",
						TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 0, 0)));
		pnlThongTinHD.setBounds(10, 75, 643, 208);
		contentPane.add(pnlThongTinHD);
		pnlThongTinHD.setLayout(null);

		JLabel lblMHan = new JLabel("Mã đơn đặt:");
		lblMHan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMHan.setBounds(10, 20, 122, 30);
		pnlThongTinHD.add(lblMHan);

		txtMa = new JTextField();
		txtMa.setEnabled(false);
		txtMa.setBounds(155, 22, 484, 30);
		pnlThongTinHD.add(txtMa);
		txtMa.setColumns(10);

		JLabel lblTnKhchHng = new JLabel("Khách Hàng:");
		lblTnKhchHng.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnKhchHng.setBounds(10, 132, 147, 30);
		pnlThongTinHD.add(lblTnKhchHng);

		txtTenKH = new JTextField();
		txtTenKH.setBounds(155, 132, 484, 30);
		pnlThongTinHD.add(txtTenKH);
		txtTenKH.setColumns(10);

		JLabel lblNhanVien = new JLabel("Nhân viên :");
		lblNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNhanVien.setBounds(10, 98, 122, 30);
		pnlThongTinHD.add(lblNhanVien);

		txtNVLap = new JTextField();
		txtNVLap.setBounds(154, 98, 484, 30);
		pnlThongTinHD.add(txtNVLap);
		txtNVLap.setColumns(10);

		JLabel lblNgayLap = new JLabel("Ngày lập:");
		lblNgayLap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNgayLap.setBounds(10, 58, 100, 30);
		pnlThongTinHD.add(lblNgayLap);
		
		JLabel lblTongtien = new JLabel("Tổng Tiền:");
		lblTongtien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTongtien.setBounds(10, 171, 100, 30);
		pnlThongTinHD.add(lblTongtien);
		
		txtTongTien = new JTextField();
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(155, 165, 484, 30);
		pnlThongTinHD.add(txtTongTien);
		
		txtNgayLap = new JTextField();
		txtNgayLap.setColumns(10);
		txtNgayLap.setBounds(154, 58, 484, 30);
		pnlThongTinHD.add(txtNgayLap);

		JPanel pnlChucNang = new JPanel();
		pnlChucNang.setBackground(new Color(176, 224, 230));
		pnlChucNang.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "C\u00E1c ch\u1EE9c n\u0103ng",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		pnlChucNang.setBounds(10, 476, 1270, 150);
		contentPane.add(pnlChucNang);
		pnlChucNang.setLayout(null);
		ImageIcon iconAdd = new ImageIcon("Hinh/add.png");
		ImageIcon iconDelete = new ImageIcon("Hinh/delete.png");
		ImageIcon iconUpdate = new ImageIcon("Hinh/update.png");
		ImageIcon iconSearch = new ImageIcon("Hinh/search.png");
		ImageIcon iconSave = new ImageIcon("Hinh/save.png");
		ImageIcon iconExit = new ImageIcon("Hinh/exit.png");
		ImageIcon iconExcel = new ImageIcon("Hinh/excel.png");

		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setBackground(new Color(176, 224, 230));
		pnlTimKiem.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"T\u00ECm ki\u1EBFm h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 0, 0)));
		pnlTimKiem.setBounds(12, 13, 1265, 96);
		pnlChucNang.add(pnlTimKiem);
		pnlTimKiem.setLayout(null);

		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBounds(979, 21, 136, 30);
		pnlTimKiem.add(btnTimKiem);
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnTimKiem.setIcon(iconSearch);

		JLabel lblNhpThngTin = new JLabel("Nhập thông tin tìm kiếm:");
		lblNhpThngTin.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNhpThngTin.setBounds(20, 18, 162, 30);
		pnlTimKiem.add(lblNhpThngTin);

		JLabel lblTmTheo = new JLabel("Tìm theo:");
		lblTmTheo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTmTheo.setBounds(20, 61, 63, 30);
		pnlTimKiem.add(lblTmTheo);

		radTenKH = new JRadioButton("Tên Khách Hàng ");
		radTenKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radTenKH.setBounds(401, 60, 207, 29);
		pnlTimKiem.add(radTenKH);

		radNgayLap = new JRadioButton("Ngày lập");
		radNgayLap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radNgayLap.setBounds(841, 60, 207, 29);
		pnlTimKiem.add(radNgayLap);
		
				radMaHD = new JRadioButton("Số điện thoại KH");
				radMaHD.setBounds(174, 59, 207, 30);
				pnlTimKiem.add(radMaHD);
				radMaHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
				radMaHD.setSelected(true);
				
				radTenNV = new JRadioButton("Tên nhân viên");
				radTenNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
				radTenNV.setBounds(617, 60, 207, 29);
				pnlTimKiem.add(radTenNV);

		JButton btnThoat = new JButton("Thoát");
		btnThoat.setBackground(Color.LIGHT_GRAY);
		btnThoat.setHorizontalAlignment(SwingConstants.LEFT);
		ImageIcon iconThoat = new ImageIcon("Hinh/exit.png");
		btnThoat.setIcon(new ImageIcon("E:\\Phat Trien Ung Dung\\File_GopCuoi_Quoc\\Nhom16_DeTai01_PTUD_13A_2019\\Hinh\\iconDelete.png"));
		btnThoat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnThoat.setBounds(1124, 112, 136, 30);
		pnlChucNang.add(btnThoat);

		JPanel pnlTitle = new JPanel();
		pnlTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlTitle.setBackground(new Color(176, 224, 230));
		pnlTitle.setBounds(0, 0, 1300, 68);
		contentPane.add(pnlTitle);
		pnlTitle.setLayout(null);

		JLabel lblQunLNhn = new JLabel("TÌM ĐƠN ĐẶT THUỐC");
		lblQunLNhn.setForeground(Color.RED);
		lblQunLNhn.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLNhn.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblQunLNhn.setBounds(10, 11, 1278, 53);
		pnlTitle.add(lblQunLNhn);

		JPanel pnlHoaDon = new JPanel();
		pnlHoaDon.setLayout(null);
		pnlHoaDon.setForeground(Color.BLACK);
		pnlHoaDon.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Danh s\u00E1ch h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		pnlHoaDon.setBackground(new Color(176, 224, 230));
		pnlHoaDon.setBounds(10, 290, 1270, 187);
		contentPane.add(pnlHoaDon);

		JScrollPane scrHoaDon = new JScrollPane();
		scrHoaDon.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrHoaDon.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrHoaDon.setBounds(12, 22, 1250, 154);
		pnlHoaDon.add(scrHoaDon);
		String[] col= {"STT", "Mã đơn đặt", "Ngày lập","Tổng tiền" ,"Tên khách hàng", "Nhân viên lập"};
		tblModelHoaDon = new DefaultTableModel(col,0);
		tblHoaDon = new JTable(tblModelHoaDon);
		
		scrHoaDon.setViewportView(tblHoaDon);

		JPanel pnlChiTiet = new JPanel();
		pnlChiTiet.setBackground(new Color(176, 224, 230));
		pnlChiTiet.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Th\u00F4ng tin chi ti\u1EBFt h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 0, 0)));
		pnlChiTiet.setBounds(664, 75, 618, 208);
		contentPane.add(pnlChiTiet);
		pnlChiTiet.setLayout(null);
		
		JScrollPane scrChiTiet = new JScrollPane();
		scrChiTiet.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrChiTiet.setBounds(10, 21, 596, 176);
		pnlChiTiet.add(scrChiTiet);
		String[] col2= {
				"STT","Tên thuốc","Đơn vị tính","Đơn giá","Số lượng","Giảm giá","Thành tiền"
		};
		tblModelChiTiet = new DefaultTableModel(col2, 0);
		tblChiTiet = new JTable(tblModelChiTiet);
		scrChiTiet.setViewportView(tblChiTiet);
		ButtonGroup group = new ButtonGroup();
		group.add(radMaHD);
		group.add(radNgayLap);
		group.add(radTenNV);
		group.add(radTenKH);
		
//		cmbTim = new JComboBox();
//		cmbTim.setBounds(184, 22, 771, 22);
//		pnlTimKiem.add(cmbTim);
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(184, 22, 771, 30);
		pnlTimKiem.add(txtTimKiem);

		btnLapHoaDon = new JButton("Lập Hóa Đơn");
		btnLapHoaDon.setBounds(618, 112, 136, 30);
		pnlChucNang.add(btnLapHoaDon);
		btnLapHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnLapHoaDon.setIcon(new ImageIcon("Hinh/laphoadon.png"));
		btnLapHoaDon.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        lapHoaDon();
		    }
		});

		
		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBackground(Color.LIGHT_GRAY);
		btnLamMoi.setHorizontalAlignment(SwingConstants.LEFT);
		btnLamMoi.setIcon(new ImageIcon("E:\\Phat Trien Ung Dung\\File_GopCuoi_Quoc\\Nhom16_DeTai01_PTUD_13A_2019\\Hinh\\refresh.png"));
		btnLamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnLamMoi.setBounds(472, 112, 136, 30);
		pnlChucNang.add(btnLamMoi);
		
		docDuLieuHD();
		tblHoaDon.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				cilckMouse();
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
		btnThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btnLamMoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lamMoi();
				docDuLieuHD();
			}
		});
		btnTimKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TimKiem();
			}
		});
		btnThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FrmManHinhChinh.tabbedPane.remove(contentPane);
			}
		});
		radMaHD.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmbTim.setModel(cboModema);
			}
		});
		radTenKH.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmbTim.setModel(cboModetenKH);
			}
		});
		radTenNV.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmbTim.setModel(cboModetenNV);
			}
		});
		radNgayLap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmbTim.setModel(cboModeNgayLap);
			}
		});
		docDuLieuCmb();
	}
	public void docDuLieuHD() {
		int d=1;
		List<DonDatThuoc> list = dao.getHoaDons();
		for(DonDatThuoc x : list) {
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			String nhanVien= dao.getTenNV(x.getNhanVien());
			String khachHang= dao.getTenKH(x.getKhachHang());
			tblModelHoaDon.addRow(new Object[] {
					d++,x.getMa(),df.format(x.getNgayLap()),x.getTongTien(),khachHang,nhanVien
			});
			
		}
	}
	/**
	 * Dùng đọc dữ liệu từ cơ sở dữ liệu lên bảng
	 */
	public void docDuLieuCmb() {
		int d=1;
		List<DonDatThuoc> list = dao.getHoaDons();
		for(DonDatThuoc x : list) {
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			String nhanVien= dao.getTenNV(x.getNhanVien());
			String khachHang= dao.getTenKH(x.getKhachHang());
			
			if(listtenKH.contains(khachHang)==false) {
				cboModetenKH.addElement(khachHang);	
				listtenKH.add(khachHang);
			}
			if(listtenNV.contains(nhanVien)==false) {
				cboModetenNV.addElement(nhanVien);	
				listtenNV.add(nhanVien);
			}
			if(listma.contains(x.getMa())==false) {
				cboModema.addElement(x.getMa());	
				listma.add(x.getMa());
			}
			if(listNgayLap.contains(df.format(x.getNgayLap()))==false) {
				cboModeNgayLap.addElement(df.format(x.getNgayLap()));	
				listNgayLap.add(df.format(x.getNgayLap()));
			}
			
		}
	}
	/**
	 * Xóa dữ liêu bảng chi tiết
	 */
	public void xoaTableChiTiet() {
		tblModelChiTiet.addRow(new Object[] {
				
		});
		DefaultTableModel tblModel1 = (DefaultTableModel) tblChiTiet.getModel();
		tblModel1.getDataVector().removeAllElements();	
	}
	/**
	 * Xóa dữ liệu bảng hóa đơn
	 */
	public void xoaTableHoaDon() {
		tblModelHoaDon.addRow(new Object[] {
				
		});
		DefaultTableModel tblModel2 = (DefaultTableModel) tblHoaDon.getModel();
		tblModel2.getDataVector().removeAllElements();	
	}
	/**
	 * làm mởi lại giao diện
	 */
	public void lamMoi() {
		txtMa.setText("");
		txtNgayLap.setText("");
		txtNVLap.setText("");
		txtTenKH.setText("");
		txtTongTien.setText("");
		xoaTableChiTiet();
		xoaTableHoaDon();
		
	}
	/**
	 * bắt sự kiện chuột trong bảng đưa dữ liệu từ bảng lên các text
	 */
	public void cilckMouse() {
		int d=1;
		int row= tblHoaDon.getSelectedRow();
		txtMa.setText(tblHoaDon.getValueAt(row, 1).toString());
		txtNgayLap.setText(tblHoaDon.getValueAt(row, 2).toString());
		txtNVLap.setText(tblHoaDon.getValueAt(row, 5).toString());
		txtTenKH.setText(tblHoaDon.getValueAt(row, 4).toString());
		txtTongTien.setText(tblHoaDon.getValueAt(row, 3).toString());
		List<CT_DonDatThuoc> list = dao.getChiTiets(tblHoaDon.getValueAt(row, 1).toString());
		xoaTableChiTiet();
		for(CT_DonDatThuoc ct : list) {
			String thuoc = dao.gettenThuoc(ct.getMaThuoc());
			double tongTien= ct.getDonGia()*ct.getSoLuong()-ct.getGiamGia();
			tblModelChiTiet.addRow(new Object[] {
					d++,thuoc,ct.getDonViTinh(),ct.getDonGia(),ct.getSoLuong(),ct.getGiamGia(),tongTien
			});
		}
	}
	/**
	 * Dùng để tìm kiếm dữ liệu
	 */

	public void TimKiem() {
	    String keyword = txtTimKiem.getText().trim(); // Lấy giá trị từ ô text
	    int d = 1; // Đếm số thứ tự trong bảng

	    // Nếu tìm theo số điện thoại khách hàng
	    if (radMaHD.isSelected()) {
	        DonDatThuoc hd = dao.getHoaDonBySoDienThoai(keyword); // Lấy đơn đặt hàng theo số điện thoại
	        lamMoi();
	        if (hd != null) {
	            String nhanVien = dao.getTenNV(hd.getNhanVien());
	            String khachHang = dao.getTenKH(hd.getKhachHang());
	            tblModelHoaDon.addRow(new Object[]{
	                d, hd.getMa(), hd.getNgayLap(), hd.getTongTien(), khachHang, nhanVien
	            });
	        } else {
	            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn với số điện thoại: " + keyword);
	        }
	    }

	    // Nếu tìm theo tên khách hàng
	    if (radTenKH.isSelected()) {
	        List<DonDatThuoc> hds = dao.getHoaDonsByKhachHang(keyword); // Lấy danh sách hóa đơn theo tên khách hàng
	        lamMoi();
	        if (!hds.isEmpty()) {
	            for (DonDatThuoc hd : hds) {
	                String nhanVien = dao.getTenNV(hd.getNhanVien());
	                String khachHang = dao.getTenKH(hd.getKhachHang());
	                tblModelHoaDon.addRow(new Object[]{
	                    d++, hd.getMa(), hd.getNgayLap(), hd.getTongTien(), khachHang, nhanVien
	                });
	            }
	        } else {
	            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn với tên khách hàng: " + keyword);
	        }
	    }

	    // Nếu tìm theo tên nhân viên
	    if (radTenNV.isSelected()) {
	        List<DonDatThuoc> hds = dao.getHoaDonsByNhanVien(keyword); // Lấy danh sách hóa đơn theo tên nhân viên
	        lamMoi();
	        if (!hds.isEmpty()) {
	            for (DonDatThuoc hd : hds) {
	                String nhanVien = dao.getTenNV(hd.getNhanVien());
	                String khachHang = dao.getTenKH(hd.getKhachHang());
	                tblModelHoaDon.addRow(new Object[]{
	                    d++, hd.getMa(), hd.getNgayLap(), hd.getTongTien(), khachHang, nhanVien
	                });
	            }
	        } else {
	            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn với tên nhân viên: " + keyword);
	        }
	    }

	    // Nếu tìm theo ngày lập
	    if (radNgayLap.isSelected()) {
	        String[] ngayLapArr = keyword.split("-"); // Kỳ vọng nhập ngày theo định dạng dd-MM-yyyy
	        if (ngayLapArr.length == 3) {
	            try {
	                int ngay = Integer.parseInt(ngayLapArr[0]);
	                int thang = Integer.parseInt(ngayLapArr[1]);
	                int nam = Integer.parseInt(ngayLapArr[2]);

	                List<DonDatThuoc> hds = dao.getHoaDonsByNgayLap(ngay, thang, nam);
	                lamMoi();
	                if (!hds.isEmpty()) {
	                    for (DonDatThuoc hd : hds) {
	                        String nhanVien = dao.getTenNV(hd.getNhanVien());
	                        String khachHang = dao.getTenKH(hd.getKhachHang());
	                        tblModelHoaDon.addRow(new Object[]{
	                            d++, hd.getMa(), hd.getNgayLap(), hd.getTongTien(), khachHang, nhanVien
	                        });
	                    }
	                } else {
	                    JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn với ngày lập: " + keyword);
	                }
	            } catch (NumberFormatException e) {
	                JOptionPane.showMessageDialog(this, "Ngày lập không hợp lệ! Vui lòng nhập theo định dạng dd-MM-yyyy.");
	            }
	        } else {
	            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày lập theo định dạng dd-MM-yyyy.");
	        }
	    }
	}
	public void lapHoaDon() {
	    int selectedRow = tblHoaDon.getSelectedRow();
	    if (selectedRow == -1) {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn một đơn đặt trước khi lập hóa đơn!");
	        return;
	    }

	    // Lấy thông tin khách hàng từ bảng
	    String maDonDat = tblHoaDon.getValueAt(selectedRow, 1).toString(); // Mã đơn đặt
	    int khachHang = dao.getKhachHangId(maDonDat); // Lấy ID khách hàng từ DAO
	    String soDienThoai = dao.getSoDienThoaiByKhachHangId(khachHang); // Lấy số điện thoại khách hàng

	    // Lấy danh sách thuốc trong đơn đặt
	    List<CT_DonDatThuoc> danhSachThuoc = dao.getChiTiets(maDonDat);

	    if (danhSachThuoc.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Không có thông tin thuốc trong đơn đặt!");
	        return;
	    }

	    // Mở FrmLapHoaDon và truyền dữ liệu
	    FrmLapHoaDon frmLapHoaDon = new FrmLapHoaDon();
	    frmLapHoaDon.setVisible(true);
	    frmLapHoaDon.setDataFromTimDonDat(soDienThoai, danhSachThuoc);
	    

	    // Xóa đơn đặt sau khi lập hóa đơn
	    boolean isDeleted = dao.deleteDonDat(maDonDat);
	    if (isDeleted) {
	        JOptionPane.showMessageDialog(this, "Đơn đặt đã được xóa khỏi danh sách!");
	        lamMoi();
	        docDuLieuHD(); // Cập nhật lại danh sách
	    } else {
	        JOptionPane.showMessageDialog(this, "Không thể xóa đơn đặt! Vui lòng kiểm tra lại.");
	    }
	}
	
	

}
