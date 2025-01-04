package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import database.ConectDatabase;
import entities.DiaChi;
import entities.NhanVien;
import entities.TaiKhoan;

public class ThongTinCaNhanDAO {
	public ThongTinCaNhanDAO() {
		// TODO Auto-generated constructor stub
		ConectDatabase.getInstance().connect();
	}
	public NhanVien loadNhanVien(String tenDangNhap) {
		NhanVien nhanVien = new  NhanVien();
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select MaNhanVien,Ten,Ho,NgaySinh,GioiTinh,CMND,SoDienThoai,SoNha,TenDuong,Phuong,Quan,ThanhPho,QuocGia,n.TenTaiKhoan,MatKhau\r\n" + 
					"from dbo.NhanVien n join dbo.DiaChi d on n.MaDiaChi = d.MaDiaChi join dbo.TaiKhoan t on n.TenTaiKhoan = t.TenTaiKhoan\r\n" + 
					"where t.TenTaiKhoan = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,tenDangNhap);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				nhanVien.setMaNhanVien(rs.getInt(1));
				nhanVien.setTen(rs.getString(2));
				nhanVien.setHo(rs.getString(3));
				nhanVien.setNgaySinh(rs.getDate(4));
				nhanVien.setGioiTinh(rs.getString(5));
				nhanVien.setCmnd(rs.getString(6));
				nhanVien.setSoDienThoai(rs.getString(7));
				DiaChi diaChi = new DiaChi();
				diaChi.setSoNha(rs.getString(8));
				diaChi.setTenDuong(rs.getString(9));
				diaChi.setPhuong(rs.getString(10));
				diaChi.setQuan(rs.getString(11));
				diaChi.setThanhPho(rs.getString(12));
				diaChi.setQuocGia(rs.getString(13));
				nhanVien.setDiaChi(diaChi);
				TaiKhoan taiKhoan = new TaiKhoan();
				taiKhoan.setTenTaiKhoan(rs.getString(14));
				taiKhoan.setMatKhau(rs.getString(15));
				nhanVien.setTaiKhoan(taiKhoan);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return nhanVien;
	}
	public String getTrangThaiNhanVien(String tenTaiKhoan) {
	    Connection con = null;
	    PreparedStatement preStm = null;
	    ResultSet rs = null;
	    String trangThai = null;

	    try {
	        con = ConectDatabase.getInstance().getConnection();
	        String sql = "SELECT TrangThai FROM NhanVien nv "
	                   + "JOIN TaiKhoan tk ON nv.TenTaiKhoan = tk.TenTaiKhoan "
	                   + "WHERE tk.TenTaiKhoan = ?";
	        preStm = con.prepareStatement(sql);
	        preStm.setString(1, tenTaiKhoan);
	        rs = preStm.executeQuery();

	        if (rs.next()) {
	            trangThai = rs.getString("TrangThai");
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

	    return trangThai;
	}
	
	public NhanVien getNhanVienByUserName(String tenTaiKhoan) {
	    NhanVien nv = null;
	    try {
	        Connection con = ConectDatabase.getInstance().getConnection();
	        String sql = "SELECT n.MaNhanVien, n.Ten, n.Ho, n.SoDienThoai, n.CMND, t.TenTaiKhoan " +
	                     "FROM NhanVien n " +
	                     "JOIN TaiKhoan t ON n.TenTaiKhoan = t.TenTaiKhoan " +
	                     "WHERE t.TenTaiKhoan = ?";
	        
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, tenTaiKhoan);
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            nv = new NhanVien();
	            nv.setMaNhanVien(rs.getInt("MaNhanVien"));
	            nv.setTen(rs.getString("Ten"));
	            nv.setHo(rs.getString("Ho"));
	            nv.setSoDienThoai(rs.getString("SoDienThoai"));
	            nv.setCmnd(rs.getString("CMND"));
	            
	            // Khởi tạo đối tượng TaiKhoan
	            TaiKhoan tk = new TaiKhoan();
	            tk.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
	            nv.setTaiKhoan(tk);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return nv;
	}

	public boolean kiemTraThongTinResetMatKhau(String tenTaiKhoan, String soDienThoai, String cmnd) {
	    boolean isValid = false;
	    try {
	        Connection con = ConectDatabase.getInstance().getConnection();
	        String sql = "SELECT COUNT(*) FROM NhanVien n " +
	                     "JOIN TaiKhoan t ON n.TenTaiKhoan = t.TenTaiKhoan " +
	                     "WHERE t.TenTaiKhoan = ? AND n.SoDienThoai = ? AND n.CMND = ?";
	        
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, tenTaiKhoan);
	        ps.setString(2, soDienThoai);
	        ps.setString(3, cmnd);
	        
	        ResultSet rs = ps.executeQuery();
	        if (rs.next() && rs.getInt(1) > 0) {
	            isValid = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return isValid;
	}


	public void capNhatMatKhauMoi(String tenTaiKhoan, String matKhauMoi) {
	    try {
	        Connection con = ConectDatabase.getInstance().getConnection();
	        String sql = "UPDATE TaiKhoan SET MatKhau = ? WHERE TenTaiKhoan = ?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, matKhauMoi);
	        ps.setString(2, tenTaiKhoan);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	public void updateThongTinCaNhan(NhanVien nhanVien) {
		 
		 Connection con = ConectDatabase.getInstance().getConnection();
			String qrl = "update dbo.NhanVien\r\n" + 
					"set NgaySinh=?,Ten=?,Ho=?,GioiTinh=?,CMND=?,SoDienThoai=?\r\n" + 
					"where MaNhanVien=?";
			PreparedStatement stm;
			try {
				stm = con.prepareStatement(qrl);
				stm.setDate(1, nhanVien.getNgaySinh());
				stm.setString(2, nhanVien.getTen());
				stm.setString(3, nhanVien.getHo());
				stm.setString(4, nhanVien.getGioiTinh());
				stm.setString(5, nhanVien.getCmnd());
				stm.setString(6, nhanVien.getSoDienThoai());
				stm.setInt(7, nhanVien.getMaNhanVien());
				stm.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	 }
	public boolean kiemTraTrungThongTinCaNhan(String soDienThoai, String cmnd, int maNhanVien) {
	    Connection con = null;
	    PreparedStatement preStm = null;
	    ResultSet rs = null;
	    boolean isDuplicate = false;

	    try {
	        con = ConectDatabase.getInstance().getConnection();
	        String sql = "SELECT 1 FROM NhanVien WHERE (SoDienThoai = ? OR CMND = ?) AND MaNhanVien != ?";
	        preStm = con.prepareStatement(sql);
	        preStm.setString(1, soDienThoai);
	        preStm.setString(2, cmnd);
	        preStm.setInt(3, maNhanVien);
	        rs = preStm.executeQuery();
	        if (rs.next()) {
	            isDuplicate = true; // Nếu có kết quả, thông tin đã tồn tại
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

	    return isDuplicate;
	}

	public String getMatKhauHienTai(int maNhanVien) {
	    String matKhauHienTai = "";
	    try {
	        Connection con = ConectDatabase.getInstance().getConnection();
	        String sql = "SELECT MatKhau FROM dbo.TaiKhoan t JOIN dbo.NhanVien n ON t.TenTaiKhoan = n.TenTaiKhoan WHERE n.MaNhanVien = ?";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setInt(1, maNhanVien);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            matKhauHienTai = rs.getString("MatKhau");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return matKhauHienTai;
	}
	
	public boolean kiemTraUsernameTonTai(String tenTaiKhoan) {
	    boolean tonTai = false;
	    try {
	        Connection con = ConectDatabase.getInstance().getConnection();
	        String sql = "SELECT COUNT(*) FROM TaiKhoan WHERE TenTaiKhoan = ?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, tenTaiKhoan);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            tonTai = rs.getInt(1) > 0; // Nếu COUNT > 0 thì username tồn tại
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return tonTai;
	}

	
	public void updateTaiKhoan(NhanVien nhanVien) {
		 Connection con = ConectDatabase.getInstance().getConnection();
		String qrl1 = "update dbo.TaiKhoan\r\n" + 
				"set MatKhau =? \r\n" + 
				"where TenTaiKhoan =?";
		PreparedStatement stmt=null;
		try {
			stmt = con.prepareStatement(qrl1);
			stmt.setString(1, nhanVien.getTaiKhoan().getMatKhau());
			stmt.setString(2, nhanVien.getTaiKhoan().getTenTaiKhoan());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
