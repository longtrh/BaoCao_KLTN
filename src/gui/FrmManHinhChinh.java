package gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;

public class FrmManHinhChinh extends JFrame implements ActionListener,MenuListener {

	private JPanel contentPane;
	private FrmLapHoaDon frmLapHoaDon;
	private FrmQuanLyHoaDon frmQuanLyHoaDon = new FrmQuanLyHoaDon();
	private FrmBaoCaoThongKe frmBaoCaoThongKe = new FrmBaoCaoThongKe();
	private FrmQuanLyNhanVien frmQuanLyNhanVien = new FrmQuanLyNhanVien();
	private FrmKhachHang frmKhachHang = new FrmKhachHang();
	private FrmXemThongTinCaNhan frmXemThongTinCaNhan = new FrmXemThongTinCaNhan();
	private FrmTimKiemNV frmTimKiemNV = new FrmTimKiemNV();
	private FrmTimKiemKH frmTimKiemKH = new FrmTimKiemKH();
	private FrmPhieuNhapThuoc frmPhieuNhapThuoc = new FrmPhieuNhapThuoc();
	private FrmLapDonDat frmLapDonDat=new FrmLapDonDat();
	private FrmTimDonDat frmTimDD= new FrmTimDonDat();
	private JMenuItem mntmThemHoaDonMoi;
	public static JTabbedPane tabbedPane;
	private JMenuBar menuBar;
	private JMenu mnAbout;
	private JMenu mnHelp;
	private JMenu mnExit;
	private JMenuItem mntmXemThongTinThuoc;
	public static JMenuItem mntmQuanLyThuoc;
	private JMenuItem mntmThongKeHoaDon;
	private JMenuItem mntmThongKeTinhTrangThuoc;
	private JMenuItem mntmPhieuNhapThuoc;
	private JMenuItem mntmThongKeDoanhThu;
	private JMenuItem mntmThongTinKhachHang;
	private JMenuItem mntmDangXuat;
	private JMenuItem mntmXemHoaDon;
	private JMenuItem mntmLapDonDat;
	private JMenuItem mntmTimKiemDD;
	private JMenuItem mntmThongTinNhanVien;
	private JMenuItem mntmTimKiemNhanVien;
	private JMenuItem mntmTimKiemKhachHang;
	public static JMenu mnNhanVien ;
	public static JMenu mnThongKe;
	public static JMenuBar menuBar_LapHoaDon;
	public static JMenu mnLapHoaDon;
	public static JMenu mnDonDatThuoc;
	
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	private ImageIcon resizeIcon(String path, int width, int height) {
	    ImageIcon icon = new ImageIcon(path);
	    Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    return new ImageIcon(img);
	}
	
	public FrmManHinhChinh() {
		
		setTitle("Quầy Thuốc Bình An");
		setIconImage(Toolkit.getDefaultToolkit().getImage("Hinh\\QLThuoc.png"));
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1361, 720);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);
		
		mnAbout = new JMenu("About");
		mnAbout.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				FrmAbout frmAbout = new FrmAbout();
				frmAbout.setVisible(true);
				frmAbout.setLocationRelativeTo(null);
			}
		});
		mnAbout.setBackground(Color.WHITE);
		menuBar.add(mnAbout);
		
		mnHelp = new JMenu("Help");
		mnHelp.setBackground(Color.WHITE);
		mnHelp.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				FrmHelp frmHelp = new FrmHelp();
				frmHelp.setVisible(true);
			}
		});
		menuBar.add(mnHelp);
		
		mnExit = new JMenu("Exit");
		mnExit.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Object chon = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thoát?");
				if(chon.equals(0)) {
					System.exit(0);
				}
			}
		});
		
		menuBar.add(mnExit);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(Color.WHITE);
		toolBar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		toolBar.setFloatable(false);
		toolBar.setEnabled(false);
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JMenuBar menuNhanVien = new JMenuBar();
		toolBar.add(menuNhanVien);

		mnNhanVien = new JMenu("Nhân Viên");
		mnNhanVien.setIcon(resizeIcon("Hinh\\NhanVien.jpg", 24, 24));
		mnNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mnNhanVien.setEnabled(true);
		menuNhanVien.add(mnNhanVien);

		mntmThongTinNhanVien = new JMenuItem("Cập nhật nhân viên");
		mntmThongTinNhanVien.setIcon(resizeIcon("Hinh\\ListKhachHang.png", 20, 20));
		mntmThongTinNhanVien.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,ActionEvent.ALT_MASK));
		mntmThongTinNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		mnNhanVien.add(mntmThongTinNhanVien);

		mntmTimKiemNhanVien = new JMenuItem("Tìm kiếm nhân viên");
		mntmTimKiemNhanVien.setIcon(resizeIcon("Hinh\\\\search.png", 20, 20));
		mntmTimKiemNhanVien.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,ActionEvent.ALT_MASK));
		mntmTimKiemNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		mnNhanVien.add(mntmTimKiemNhanVien);
		
		JMenuBar menuKhachHang = new JMenuBar();
		toolBar.add(menuKhachHang);

		JMenu mnKhachHang = new JMenu("Khách Hàng");
		mnKhachHang.setIcon(resizeIcon("Hinh\\customer.png", 24, 24));
		mnKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mnKhachHang.setEnabled(true);
		menuKhachHang.add(mnKhachHang);

		mntmThongTinKhachHang = new JMenuItem("Cập nhật khách hàng");
		mntmThongTinKhachHang.setIcon(resizeIcon("Hinh\\ListKhachHang.png", 20, 20));
		mntmThongTinKhachHang.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,ActionEvent.ALT_MASK));
		mntmThongTinKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		mnKhachHang.add(mntmThongTinKhachHang);


		mntmTimKiemKhachHang = new JMenuItem("Tìm kiếm khách hàng");
		mntmTimKiemKhachHang.setIcon(resizeIcon("Hinh\\\\search.png", 20, 20));
		mntmTimKiemKhachHang.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,ActionEvent.ALT_MASK));
		mntmTimKiemKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		mnKhachHang.add(mntmTimKiemKhachHang);
		
		JMenuBar menuThuoc = new JMenuBar();
		menuThuoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		toolBar.add(menuThuoc);

		JMenu mnThuoc = new JMenu("Thuốc");
		mnThuoc.setIcon(resizeIcon("Hinh\\icons8-medicine-48.png", 24, 24));
		mnThuoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		menuThuoc.add(mnThuoc);

		mntmQuanLyThuoc = new JMenuItem("Cập nhật thuốc");
		mntmQuanLyThuoc.setIcon(resizeIcon("Hinh\\IconThuocThongKe.jpg", 20, 20));
		mntmQuanLyThuoc.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		mntmQuanLyThuoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,ActionEvent.ALT_MASK));
		mnThuoc.add(mntmQuanLyThuoc);

		mntmXemThongTinThuoc = new JMenuItem("Tìm kiếm thuốc");
		mntmXemThongTinThuoc.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		mntmXemThongTinThuoc.setIcon(resizeIcon("Hinh\\\\search.png", 20, 20));
		mntmXemThongTinThuoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6,ActionEvent.ALT_MASK));
		mnThuoc.add(mntmXemThongTinThuoc);

		mntmPhieuNhapThuoc = new JMenuItem("Nhập thuốc");
		mntmPhieuNhapThuoc.setIcon(resizeIcon("Hinh\\hoadon.png", 20, 20));
		mntmPhieuNhapThuoc.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		mnThuoc.add(mntmPhieuNhapThuoc);
		
		JMenuBar menuLapHoaDon = new JMenuBar();
		toolBar.add(menuLapHoaDon);

		mnLapHoaDon = new JMenu("Hóa Đơn");
		mnLapHoaDon.setIcon(resizeIcon("Hinh\\ThemHoaDon.png", 24, 24));
		mnLapHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mnLapHoaDon.setMnemonic(KeyEvent.VK_ENTER);
		menuLapHoaDon.add(mnLapHoaDon);

		mntmThemHoaDonMoi = new JMenuItem("Lập hóa đơn");
		mntmThemHoaDonMoi.setIcon(resizeIcon("Hinh\\add-HoaDon.png", 20, 20));
		mntmThemHoaDonMoi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		mntmThemHoaDonMoi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7,ActionEvent.ALT_MASK));
		mnLapHoaDon.add(mntmThemHoaDonMoi);

		mntmXemHoaDon = new JMenuItem("Tìm hóa đơn");
		mntmXemHoaDon.setIcon(resizeIcon("Hinh\\hoadon.png", 20, 20));
		mntmXemHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		mntmXemHoaDon.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, ActionEvent.ALT_MASK));
		mnLapHoaDon.add(mntmXemHoaDon);
		
		JMenuBar menuDonDatThuoc = new JMenuBar();
		toolBar.add(menuDonDatThuoc);
		
		mnDonDatThuoc = new JMenu("Đơn Đặt Thuốc");
		mnDonDatThuoc.setIcon(resizeIcon("Hinh\\order.png", 24, 24));
		mnDonDatThuoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mnDonDatThuoc.setMnemonic(KeyEvent.VK_ENTER);
		menuDonDatThuoc.add(mnDonDatThuoc);

		mntmLapDonDat = new JMenuItem("Lập đơn đặt");
		mntmLapDonDat.setIcon(resizeIcon("Hinh\\add-HoaDon.png", 20, 20));
		mntmLapDonDat.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		mnDonDatThuoc.add(mntmLapDonDat);

		mntmTimKiemDD = new JMenuItem("Tìm đơn đặt");
		mntmTimKiemDD.setIcon(resizeIcon("Hinh\\hoadon.png", 20, 20));
		mntmTimKiemDD.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		mnDonDatThuoc.add(mntmTimKiemDD);
		
		JMenuBar menuThongKe = new JMenuBar();
		toolBar.add(menuThongKe);

		mnThongKe = new JMenu("Thống Kê       ");
		mnThongKe.setIcon(resizeIcon("Hinh\\iconthongke.jpg", 24 ,24));
		mnThongKe.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		menuThongKe.add(mnThongKe);

		mntmThongKeHoaDon = new JMenuItem("Thống kê hóa đơn");
		mntmThongKeHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		mntmThongKeHoaDon.setIcon(resizeIcon("Hinh\\customer.png", 20, 20));
		mntmThongKeHoaDon.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9,ActionEvent.ALT_MASK));
		mnThongKe.add(mntmThongKeHoaDon);

		mntmThongKeTinhTrangThuoc = new JMenuItem("Thống kê tình trạng thuốc");
		mntmThongKeTinhTrangThuoc.setIcon(resizeIcon("Hinh\\IconThuoc.png", 20, 20));
		mntmThongKeTinhTrangThuoc.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		mntmThongKeTinhTrangThuoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10,ActionEvent.ALT_MASK));
		mnThongKe.add(mntmThongKeTinhTrangThuoc);

		mntmThongKeDoanhThu = new JMenuItem("Thống kê doanh thu");
		mntmThongKeDoanhThu.setIcon(resizeIcon("Hinh\\thongke.png", 20, 20));
		mntmThongKeDoanhThu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		mntmThongKeDoanhThu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11,ActionEvent.ALT_MASK));
		mnThongKe.add(mntmThongKeDoanhThu);

		JMenuBar menuThongTinCaNhan = new JMenuBar();
		toolBar.add(menuThongTinCaNhan);

		JMenu mnThongTinCaNhan = new JMenu("Thông Tin Cá Nhân");
		mnThongTinCaNhan.addMenuListener(new MenuListener() {
		    public void menuCanceled(MenuEvent e) {
		    }
		    public void menuDeselected(MenuEvent e) {
		    }
		    public void menuSelected(MenuEvent e) {
		        tabbedPane.remove(tabbedPane.getSelectedComponent());
		        tabbedPane.add(frmXemThongTinCaNhan.pnlTabXemThongTinCaNhan);
		    }
		});
		mnThongTinCaNhan.setIcon(resizeIcon("Hinh\\user.png", 24 ,24));
		mnThongTinCaNhan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		menuThongTinCaNhan.add(mnThongTinCaNhan);
		mnThongTinCaNhan.setMnemonic(KeyEvent.VK_F12);

		JMenuBar menuDangNhap = new JMenuBar();
		toolBar.add(menuDangNhap);

		JMenu mnDangNhap_DangXuat = new JMenu("");
		mnDangNhap_DangXuat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mnDangNhap_DangXuat.setIcon(resizeIcon("Hinh\\system.png", 24, 24));
		menuDangNhap.add(mnDangNhap_DangXuat);

		mntmDangXuat = new JMenuItem("Đăng xuất");
		mntmDangXuat.setIcon(resizeIcon("Hinh\\logout.png", 20, 20));
		mntmDangXuat.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		mnDangNhap_DangXuat.add(mntmDangXuat);
		
		tabbedPane = new JTabbedPane(JTabbedPane.RIGHT);
		tabbedPane.setBackground(Color.WHITE);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		mntmTimKiemKhachHang.addActionListener(this);
		mntmTimKiemNhanVien.addActionListener(this);
		mntmThongTinNhanVien.addActionListener(this);
		mntmThemHoaDonMoi.addActionListener(this);
		mntmXemHoaDon.addActionListener(this);
		mntmXemThongTinThuoc.addActionListener(this);
		mntmQuanLyThuoc.addActionListener(this);
		mntmThongKeHoaDon.addActionListener(this);
		mntmThongKeTinhTrangThuoc.addActionListener(this);
		mntmThongKeDoanhThu.addActionListener(this);
		mntmThongTinKhachHang.addActionListener(this);
		mntmDangXuat.addActionListener(this);
		mntmPhieuNhapThuoc.addActionListener(this);
		mntmLapDonDat.addActionListener(this);
		mntmTimKiemDD.addActionListener(this);
		//contentPane.add(frmLapHoaDon.jp1, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj.equals(mntmThemHoaDonMoi)) {
			try {
				FrmLapHoaDon.cboTimKiemThuoc.removeAllItems();
				
			} catch (Exception e2) {
				// TODO: handle exception
				
			}
			tabbedPane.remove(tabbedPane.getSelectedComponent());
			frmLapHoaDon = new FrmLapHoaDon();
			tabbedPane.add(frmLapHoaDon.jp1);
			
		}
		else if(obj.equals(mntmXemHoaDon)) {
			tabbedPane.remove(tabbedPane.getSelectedComponent());
			tabbedPane.add(frmQuanLyHoaDon.contentPane);
		}
		else if(obj.equals(mntmThongTinNhanVien)) {
			tabbedPane.remove(tabbedPane.getSelectedComponent());
			tabbedPane.add(frmQuanLyNhanVien.pnlTabQLNV);
		}
		else if(obj.equals(mntmTimKiemNhanVien)) {
			tabbedPane.remove(tabbedPane.getSelectedComponent());
			tabbedPane.add(frmTimKiemNV.frmTimKiemNV);
		}
		else if(obj.equals(mntmTimKiemKhachHang)) {
			tabbedPane.remove(tabbedPane.getSelectedComponent());
			tabbedPane.add(frmTimKiemKH.contentPane);
		}
		else if(obj.equals(mntmThongTinNhanVien)) {
			tabbedPane.remove(tabbedPane.getSelectedComponent());
			tabbedPane.add(frmQuanLyNhanVien.pnlTabQLNV);
		}
		else if(obj.equals(mntmXemThongTinThuoc)) {
			tabbedPane.remove(tabbedPane.getSelectedComponent());
			FrmThuoc frmThuoc = new FrmThuoc();
//			frmThuoc.pnlChucNang.remove(frmThuoc.btnThem);
			frmThuoc.pnlChucNang.remove(frmThuoc.btnLuu);
			frmThuoc.lblTitiel.setText("TÌM KIẾM THUỐC");
			frmThuoc.pnlChucNang.remove(frmThuoc.btnXoa);
			frmThuoc.pnlChucNang.remove(frmThuoc.btnSua);
			tabbedPane.add(frmThuoc.contentPane);
			
		}
		else if(obj.equals(mntmQuanLyThuoc)) {
			
			tabbedPane.remove(tabbedPane.getSelectedComponent());
			FrmThuoc frmThuoc = new FrmThuoc();
			tabbedPane.add(frmThuoc.contentPane);
		}
		else if(obj.equals(mntmPhieuNhapThuoc)) {
			tabbedPane.remove(tabbedPane.getSelectedComponent());
			tabbedPane.add(frmPhieuNhapThuoc.contentPane);
		}
		else if(obj.equals(mntmThongKeHoaDon)) {
			tabbedPane.remove(tabbedPane.getSelectedComponent());
			tabbedPane.add(frmBaoCaoThongKe.pnlToanPhan);
		}
		else if(obj.equals(mntmThongKeTinhTrangThuoc)) {
			tabbedPane.remove(tabbedPane.getSelectedComponent());
			tabbedPane.add(frmBaoCaoThongKe.pnlThongkeTTThuoc);
		}
		else if(obj.equals(mntmThongKeDoanhThu)) {
			tabbedPane.remove(tabbedPane.getSelectedComponent());
			tabbedPane.add(frmBaoCaoThongKe.pnlThongKeBaoCao);
		}
		else if(obj.equals(mntmThongTinKhachHang)) {
			tabbedPane.remove(tabbedPane.getSelectedComponent());
			tabbedPane.add(frmKhachHang.contentPane);
		}
		else if(obj.equals(mntmLapDonDat)) {
			try {
				FrmLapDonDat.cboTimKiemThuoc.removeAllItems();
				
			} catch (Exception e2) {
				// TODO: handle exception
				
			}
			tabbedPane.remove(tabbedPane.getSelectedComponent());
			frmLapDonDat = new FrmLapDonDat();
			tabbedPane.add(frmLapDonDat.jp1);
		}
		else if(obj.equals(mntmTimKiemDD)) {
			tabbedPane.remove(tabbedPane.getSelectedComponent());
			tabbedPane.add(frmTimDD.contentPane);
		}
		else if(obj.equals(mntmDangXuat)) {
			this.setVisible(false);
			FrmDangNhap.TrangThaiDangNhapNhanVien = false;
			FrmDangNhap.TrangThaiDangNhapQuanLy = false;
			FrmDangNhap frmDangNhap = new FrmDangNhap();
			frmDangNhap.setVisible(true);
		}
	}

	@Override
	public void menuSelected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}
}