package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.ConectDatabase;
import entities.DiaChi;
import entities.KhachHang;
import entities.TaiKhoan;

public class KhachHangDAO {
	Connection con;
	PreparedStatement preStm;
	ResultSet rs;

	public KhachHangDAO() {
	}

	@SuppressWarnings("unused")
	private void closeConnection() throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (preStm != null) {
			preStm.close();
		}
		if (con != null) {
			con.close();
		}
	}


	@SuppressWarnings("static-access")
	public boolean themKhachHang(KhachHang kh) throws SQLException {
		con = ConectDatabase.getInstance().getConnection();
		int n = 0;
		try {
			String sql = "insert into KhachHang values(?,?,?,?,?,?,?)";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, kh.getTen());
			preStm.setString(2, kh.getHo());
			preStm.setDate(3, kh.getNgaySinh());
			preStm.setString(4, kh.getGioiTinh());
			preStm.setString(5, kh.getCmnd());
			preStm.setString(6, kh.getSoDienThoai());
			preStm.setInt(7, kh.getDiaChi().getMaDiaChi());
			n = preStm.executeUpdate();
		} catch (Exception e) {
			System.out.println("Loi add KhachHang sql");
			e.printStackTrace();
		} finally {
		}
		return n > 0;
	}


	@SuppressWarnings("static-access")
	public boolean xoaKhachHang(int maKH) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "delete KhachHang where MaKhachHang=?";
			preStm = con.prepareStatement(sql);
			preStm.setInt(1, maKH);
			check = preStm.executeUpdate() > 0;
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return check;
	}



	@SuppressWarnings("static-access")
	public boolean capnhatKhachHang(int ma, String ten, String ho, Date ngaySinh, String gioiTinh, String cmnd,
			String sdt) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "Update KhachHang set Ten = ?, Ho = ?, NgaySinh = ?, GioiTinh = ?, "
					+ "CMND = ?, SoDienThoai = ? where MaKhachHang = ?";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, ten);
			preStm.setString(2, ho);
			preStm.setDate(3, ngaySinh);
			preStm.setString(4, gioiTinh);
			preStm.setString(5, cmnd);
			preStm.setString(6, sdt);
			preStm.setInt(7, ma);
			check = preStm.executeUpdate() > 0;
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
		}
		return check;
	}

	public boolean kiemTraKhachHangTonTai(String soDienThoai, String cmnd) {
	    Connection con = null;
	    PreparedStatement preStm = null;
	    ResultSet rs = null;
	    boolean tonTai = false;

	    try {
	        con = ConectDatabase.getInstance().getConnection();
	        String sql = "SELECT 1 FROM KhachHang WHERE SoDienThoai = ? OR CMND = ?";
	        preStm = con.prepareStatement(sql);
	        preStm.setString(1, soDienThoai);
	        preStm.setString(2, cmnd);
	        rs = preStm.executeQuery();
	        if (rs.next()) {
	            tonTai = true; // Nếu có kết quả, nghĩa là đã tồn tại
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (preStm != null) preStm.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return tonTai;
	}

	
	/**
	 * search KhachHang by MaKhachHang
	 * 
	 * @param ma
	 * @return List<KhachHang>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public List<KhachHang> timkiemKhachHangByMa(int ma) throws ClassNotFoundException, SQLException {
		List<KhachHang> result = null;
		KhachHang dto;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "select * from KhachHang where MaKhachHang like ?";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, "%" + ma + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				int maKH = rs.getInt("MaKhachHang");
				String ten = rs.getString("Ten");
				String ho = rs.getString("Ho");
				Date ngaySinh = rs.getDate("NgaySinh");
				String gioiTinh = rs.getString("GioiTinh");
				String CMND = rs.getString("CMND");
				String soDienThoai = rs.getString("SoDienThoai");
				int maDiaChi = rs.getInt("MaDiaChi");
				DiaChi diaChi = new DiaChi();
				diaChi.setMaDiaChi(maDiaChi);
				dto = new KhachHang(maKH, ten, ho, ngaySinh, gioiTinh, CMND, soDienThoai, diaChi);
				result.add(dto);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
		}
		return result;
	}

	/**
	 * search KhachHang by SoDienThoai
	 * 
	 * @param sdt
	 * @return List<KhachHang>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public List<KhachHang> timkiemKhachHangBySDT(String sdt) throws ClassNotFoundException, SQLException {
		List<KhachHang> result = null;
		KhachHang dto;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "select * from KhachHang where SoDienThoai like ?";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, "%" + sdt + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				int maKH = rs.getInt("MaKhachHang");
				String ten = rs.getString("Ten");
				String ho = rs.getString("Ho");
				Date ngaySinh = rs.getDate("NgaySinh");
				String gioiTinh = rs.getString("GioiTinh");
				String CMND = rs.getString("CMND");
				String soDienThoai = rs.getString("SoDienThoai");
				int maDiaChi = rs.getInt("MaDiaChi");
				DiaChi diaChi = new DiaChi();
				diaChi.setMaDiaChi(maDiaChi);
				dto = new KhachHang(maKH, ten, ho, ngaySinh, gioiTinh, CMND, soDienThoai, diaChi);
				result.add(dto);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			System.out.println("Loi tim khachhang theo sdt sql");
		} finally {
		}
		return result;
	}

	/**
	 * search KhachHang by TenKhachHang
	 * 
	 * @param tenn
	 * @return List<KhachHang>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public List<KhachHang> timkiemKhachHangByTen(String tenn) throws ClassNotFoundException, SQLException {
		List<KhachHang> result = null;
		KhachHang dto;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "select * from KhachHang where Ten like ?";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, "%" + tenn + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				int maKH = rs.getInt("MaKhachHang");
				String ten = rs.getString("Ten");
				String ho = rs.getString("Ho");
				Date ngaySinh = rs.getDate("NgaySinh");
				String gioiTinh = rs.getString("GioiTinh");
				String CMND = rs.getString("CMND");
				String soDienThoai = rs.getString("SoDienThoai");
				int maDiaChi = rs.getInt("MaDiaChi");
				DiaChi diaChi = new DiaChi();
				diaChi.setMaDiaChi(maDiaChi);
				dto = new KhachHang(maKH, ten, ho, ngaySinh, gioiTinh, CMND, soDienThoai, diaChi);
				result.add(dto);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
		}
		return result;
	}
	/**
	 * search KhachHang by SoCMND
	 * 
	 * @param cmnd
	 * @return List<KhachHang>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public List<KhachHang> timkiemKhachHangByCMND(String cmnd) throws ClassNotFoundException, SQLException {
		List<KhachHang> result = null;
		KhachHang dto;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "select * from KhachHang where CMND like ?";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, "%" + cmnd + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				int maKH = rs.getInt("MaKhachHang");
				String ten = rs.getString("Ten");
				String ho = rs.getString("Ho");
				Date ngaySinh = rs.getDate("NgaySinh");
				String gioiTinh = rs.getString("GioiTinh");
				String CMND = rs.getString("CMND");
				String soDienThoai = rs.getString("SoDienThoai");
				int maDiaChi = rs.getInt("MaDiaChi");
				DiaChi diaChi = new DiaChi();
				diaChi.setMaDiaChi(maDiaChi);
				dto = new KhachHang(maKH, ten, ho, ngaySinh, gioiTinh, cmnd, soDienThoai, diaChi);
				result.add(dto);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			System.out.println("Loi tim khachhang theo cmnd sql");
		} finally {
		}
		return result;
	}

	@SuppressWarnings("static-access")
	public KhachHang layThongTinKhachHang(int maKH) throws ClassNotFoundException, SQLException {
		KhachHang dto = null;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "Select * from KhachHang where MaKhachHang = ?";
			preStm = con.prepareStatement(sql);
			preStm.setInt(1, maKH);
			rs = preStm.executeQuery();
			if (rs.next()) {
				int maKhachHang = rs.getInt("MaKhachHang");
				String ten = rs.getString("Ten");
				String ho = rs.getString("Ho");
				Date ngaySinh = rs.getDate("NgaySinh");
				String gioiTinh = rs.getString("GioiTinh");
				String CMND = rs.getString("CMND");
				String soDT = rs.getString("SoDienThoai");
				int maDiaChi = rs.getInt("MaDiaChi");
				DiaChi diaChi = new DiaChi();
				diaChi.setMaDiaChi(maDiaChi);
				dto = new KhachHang(maKH, ten, ho, ngaySinh, gioiTinh, CMND, soDT, diaChi);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@SuppressWarnings("static-access")
	public List<KhachHang> getAllKhachHang() throws Exception {
		List<KhachHang> result = null;
		KhachHang dto;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "select * from KhachHang";
			preStm = con.prepareStatement(sql);
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				int maKH = rs.getInt("MaKhachHang");
				String ten = rs.getString("Ten");
				String ho = rs.getString("Ho");
				Date ngaySinh = rs.getDate("NgaySinh");
				String gioiTinh = rs.getString("GioiTinh");
				String cmnd = rs.getString("CMND");
				String soDT = rs.getString("SoDienThoai");
				int maDiaChi = rs.getInt("MaDiaChi");
				DiaChi diaChi = new DiaChi();
				diaChi.setMaDiaChi(maDiaChi);
				dto = new KhachHang(maKH, ten, ho, ngaySinh, gioiTinh, cmnd, soDT, diaChi);
				result.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * add TaiKhoan
	 * 
	 * @param tk
	 * @return true/false
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public boolean addTaiKhoan(TaiKhoan tk) throws ClassNotFoundException, SQLException {
		con = ConectDatabase.getInstance().getConnection();
		int n = 0;
		try {
			preStm = con.prepareStatement("insert into TaiKhoan values\r\n" + "(?,?)");
			preStm.setString(1, tk.getTenTaiKhoan());
			preStm.setString(2, tk.getMatKhau());
			n = preStm.executeUpdate();
		} catch (Exception e3) {
			e3.printStackTrace();
		} finally {
		}
		return n > 0;
	}

	/**
	 * get all TenTaiKhoan
	 * 
	 * @return List<String>
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public List<String> getAllTenTaiKhoan() throws Exception {
		List<String> result = null;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "select [TenTaiKhoan] from [dbo].[TaiKhoan]";
			preStm = con.prepareStatement(sql);
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String taiKhoan = rs.getString(1);
				result.add(taiKhoan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return result;
	}

	/**
	 * add DiaChi
	 * 
	 * @param dc
	 * @return true/false
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public boolean addDiaChi(DiaChi dc) throws SQLException {
		con = ConectDatabase.getInstance().getConnection();
		boolean check = false;
		try {
			preStm = con.prepareStatement("insert into dbo.DiaChi values\r\n" + "(?,?,?,?,?,?)");
			preStm.setString(1, dc.getSoNha());
			preStm.setString(2, dc.getTenDuong());
			preStm.setString(3, dc.getPhuong());
			preStm.setString(4, dc.getQuan());
			preStm.setString(5, dc.getThanhPho());
			preStm.setString(6, dc.getQuocGia());
			check = preStm.executeUpdate() > 0;
		} catch (Exception e3) {
			System.out.println("loi sql them dia chi");
			e3.printStackTrace();
		} finally {
		}
		return check;
	}

	/**
	 * get all MaDiaChi
	 * 
	 * @return List<Integer>
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public List<Integer> getAllMaDiaChi() throws Exception {
		List<Integer> result = new ArrayList<Integer>();
		con = ConectDatabase.getInstance().getConnection();
		try {
			String sql = "select [MaDiaChi] from [dbo].[DiaChi]";
			preStm = con.prepareStatement(sql);
			ResultSet rs = preStm.executeQuery();
			while (rs.next()) {
				int maDC = rs.getInt(1);
				result.add(maDC);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Lỗi lấy all MaDiaChi");
		} finally {
		}
		return result;
	}
	
	@SuppressWarnings("static-access")
	public List<String> getAllSoDienThoai() throws Exception {
	    List<String> result = new ArrayList<>();
	    Connection con = ConectDatabase.getInstance().getConnection();
	    PreparedStatement preStm = null;

	    try {
	        // Câu lệnh SQL để lấy danh sách số điện thoại
	        String sql = "SELECT [SoDienThoai] FROM [dbo].[KhachHang]";
	        preStm = con.prepareStatement(sql);

	        // Thực thi truy vấn
	        ResultSet rs = preStm.executeQuery();
	        while (rs.next()) {
	            // Lấy giá trị SoDienThoai từ cột đầu tiên
	            String soDienThoai = rs.getString(1);
	            result.add(soDienThoai);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Lỗi lấy danh sách số điện thoại");
	    } finally {
	        if (preStm != null) {
	            try {
	                preStm.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return result;
	}

	/**
	 * get MaDiaChiMax
	 * 
	 * @param maDiaChiMax
	 * @return maDiaChiMax
	 */
	@SuppressWarnings("static-access")
	public int getMaDiaChiMax() {
		int maDiaChiMax = 0;
		con = ConectDatabase.getInstance().getConnection();
		try {
			String sql = "select MAX(MaDiaChi)\r\n" + "from dbo.DiaChi";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				maDiaChiMax = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maDiaChiMax;
	}

	/**
	 * get maNhanVienMax
	 * 
	 * @return maNhanVienMax
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public int getMaNhanVienMax() throws SQLException {
		int maNhanVienMax = 0;
		con = ConectDatabase.getInstance().getConnection();
		try {
			String sql = "select MAX(MaNhanVien)\r\n" + "from dbo.NhanVien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				maNhanVienMax = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return maNhanVienMax;
	}

	/**
	 * get all maNhanVien
	 * 
	 * @return List<Integer>
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public List<Integer> getAllMaNV() throws Exception {
		List<Integer> result = new ArrayList<Integer>();
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select MaNhanVien from NhanVien";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			int maNV;
			while (rs.next()) {
				maNV = rs.getInt(1);
				result.add(maNV);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return result;
	}

	/**
	 * get information DiaChi
	 * 
	 * @param maDC
	 * @return DiaChi
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public DiaChi layThongTinDiaChi(int maDC) throws ClassNotFoundException, SQLException {
		DiaChi dto = null;
		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "Select * from DiaChi where MaDiaChi = ?";
			preStm = con.prepareStatement(sql);
			preStm.setInt(1, maDC);
			rs = preStm.executeQuery();
			if (rs.next()) {
				String soNha = rs.getString("SoNha");
				String tenDuong = rs.getString("TenDuong");
				String phuong = rs.getString("Phuong");
				String quan = rs.getString("Quan");
				String thanhPho = rs.getString("ThanhPho");
				String quocGia = rs.getString("QuocGia");
				int maDiaChi = rs.getInt("MaDiaChi");
				dto = new DiaChi(maDiaChi, soNha, tenDuong, phuong, quan, thanhPho, quocGia);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	/**
	 * get full name NhanVien by tenTaiKhoan
	 * 
	 * @param tenTaiKhoan
	 * @return String
	 */
	@SuppressWarnings("static-access")
	public String getTenNhanVienByTenTaiKhoan(String tenTaiKhoan) {
		String tenTaiKhoan1 = null;

		try {
			con = ConectDatabase.getInstance().getConnection();
			String sql = "select Ho + ' '+Ten\r\n"
					+ "from dbo.NhanVien n join dbo.TaiKhoan t on n.TenTaiKhoan = t.TenTaiKhoan\r\n"
					+ "where t.TenTaiKhoan = ?";
			preStm = con.prepareStatement(sql);
			preStm.setString(1, tenTaiKhoan);
			rs = preStm.executeQuery();
			while (rs.next()) {
				tenTaiKhoan1 = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error getTenNhanVienByTenTaiKhoan SQL !");
		}
		return tenTaiKhoan1;
	}
}
