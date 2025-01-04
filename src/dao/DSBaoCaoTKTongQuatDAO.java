package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.ConectDatabase;
import entities.NhaCungCap;
import entities.ThongKeBaoCaoTQ;
import entities.Thuoc;

public class DSBaoCaoTKTongQuatDAO {
	Connection con;
	PreparedStatement pre;
	ResultSet rs;

	ArrayList<ThongKeBaoCaoTQ> ds;
	ThongKeBaoCaoTQ tkbc;

	public DSBaoCaoTKTongQuatDAO() {
		ds = new ArrayList<ThongKeBaoCaoTQ>();
	}

	
	public List<String> getAllNgay() throws Exception {
		List<String> result = new ArrayList<String>();
		try {
			Connection con = ConectDatabase.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "select NgayLap from HoaDon";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			String ngay;
			while (rs.next()) {
				ngay = rs.getString(1);
				result.add(ngay);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return result;
	}

	public double tinhTongThuocBanDuocTheoThang(int thang, int nam) throws Exception {
	    double tongTien = 0;
	    try {
	        Connection con = ConectDatabase.getInstance().getConnection();

	        // Câu SQL: lấy tất cả hoặc lọc theo năm và tháng
	        String sql = "SELECT SUM(c.SoLuong * c.DonGia) " +
	                     "FROM HoaDon h JOIN CT_HoaDon c ON h.MaHoaDon = c.MaHoaDon " +
	                     "WHERE (? = 0 OR YEAR(NgayLap) = ?)";
	        
	        // Thêm điều kiện tháng nếu có chọn tháng cụ thể
	        if (thang > 0) {
	            sql += " AND MONTH(NgayLap) = ?";
	        }

	        // Chuẩn bị câu lệnh SQL
	        pre = con.prepareStatement(sql);
	        pre.setInt(1, nam); // Điều kiện "Tất cả năm" (nam = 0)
	        if (nam > 0) pre.setInt(2, nam);
	        if (thang > 0) pre.setInt(3, thang);

	        // Thực thi câu lệnh
	        rs = pre.executeQuery();
	        if (rs.next()) {
	            tongTien = rs.getDouble(1); // Tổng tiền
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (rs != null) rs.close();
	        if (pre != null) pre.close();
	    }
	    return tongTien;
	}


	public double tinhTongTienLoThuocHetHan(int thang, int nam) {
	    double tongTienLo = 0;
	    try {
	        Connection con = ConectDatabase.getInstance().getConnection();
	        String sql = "SELECT SUM(SoLuongNhap * GiaNhap) AS TienLo " +
	                     "FROM Thuoc " +
	                     "WHERE YEAR(HanSuDung) = ? AND MONTH(HanSuDung) <= ? " +
	                     "OR DATEDIFF(DAY, GETDATE(), HanSuDung) <= 30";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, nam);
	        ps.setInt(2, thang == 0 ? 12 : thang); // Nếu "Tất cả", lấy đến tháng 12
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            tongTienLo = rs.getDouble("TienLo");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return tongTienLo;
	}

	
	public double tinhTongTienThuocDaNhapTheoThang(int thang, int nam) throws Exception {
	    double tongTien = 0;
	    try {
	        Connection con = ConectDatabase.getInstance().getConnection();
	        StringBuilder sql = new StringBuilder("SELECT SUM(SoLuongNhap * GiaNhap) FROM Thuoc");
	        
	        if (nam > 0) { 
	            sql.append(" WHERE YEAR(NgaySanXuat) = ?");
	            if (thang > 0) { 
	                sql.append(" AND MONTH(NgaySanXuat) = ?");
	            }
	        }
	       
	        pre = con.prepareStatement(sql.toString());
	        if (nam > 0) {
	            pre.setInt(1, nam);
	            if (thang > 0) pre.setInt(2, thang);
	        }

	        rs = pre.executeQuery();
	        if (rs.next()) {
	            tongTien = rs.getDouble(1);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return tongTien;
	}



	public List<ThongKeBaoCaoTQ> thongKeThuocDaBan_DoanhThu_TheoThang(int thang, int nam) throws Exception {
	    List<ThongKeBaoCaoTQ> dsThuoc = new ArrayList<>();
	    try {
	        Connection con = ConectDatabase.getInstance().getConnection();
	        StringBuilder sql = new StringBuilder(
	            "SELECT t.MaThuoc, t.TenThuoc, t.DonGia, t.GiaNhap, " +
	            "CONVERT(nvarchar(10), h.NgayLap, 103) AS HanSuDung, t.[SoDangKi], " +
	            "SUM(t.SoLuongNhap) AS SoLuongNhap, SUM(ct.SoLuong) AS SoLuongBan, " +
	            "SUM(ct.SoLuong * ct.DonGia) AS TienBan, SUM(ct.SoLuong * t.GiaNhap) AS TienNhap " +
	            "FROM CT_HoaDon ct " +
	            "JOIN Thuoc t ON ct.MaThuoc = t.MaThuoc " +
	            "JOIN HoaDon h ON ct.MaHoaDon = h.MaHoaDon "
	        );

	        if (nam > 0) { 
	            sql.append("WHERE YEAR(h.NgayLap) = ? ");
	            if (thang > 0) { 
	                sql.append("AND MONTH(h.NgayLap) = ? ");
	            }
	        }

	        sql.append("GROUP BY t.MaThuoc, t.TenThuoc, t.DonGia, t.GiaNhap, CONVERT(nvarchar(10), h.NgayLap, 103), t.[SoDangKi]");

	        PreparedStatement pre = con.prepareStatement(sql.toString());
	        if (nam > 0) {
	            pre.setInt(1, nam);
	            if (thang > 0) {
	                pre.setInt(2, thang);
	            }
	        }

	        ResultSet rs = pre.executeQuery();
	        while (rs.next()) {
	            ThongKeBaoCaoTQ thuoc = new ThongKeBaoCaoTQ();
	            thuoc.setMaThuoc(rs.getString(1));
	            thuoc.setTenThuoc(rs.getString(2));
	            thuoc.setDonGia(rs.getDouble(3));
	            thuoc.setDonGiaNhap(rs.getDouble(4));
	            thuoc.setNgay(rs.getString(5));
	            thuoc.setSoDK(rs.getString(6));
	            thuoc.setSoLuongNhap(rs.getInt(7));
	            thuoc.setSoLuongBan(rs.getInt(8));
	            thuoc.setLoiNhuan(rs.getDouble(9));
	            thuoc.setTienThuocNhap(rs.getDouble(10));
	            dsThuoc.add(thuoc);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dsThuoc;
	}



}
