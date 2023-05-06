package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.KhachHang;
import model.KhuyenMai;
import util.JDBCUtil;

public class KhachHangDAO implements DAOInterface<KhachHang> {

	@Override
	public ArrayList<KhachHang> selectAll() {
		ArrayList<KhachHang> ketQua = new ArrayList<KhachHang>();
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "SELECT * FROM user";
			PreparedStatement pst = con.prepareStatement(sql);

			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				int maKhachHang = rs.getInt("makhachhang");
				String tenDangNhap = rs.getString("tendangnhap");
				String password = rs.getString("password");
				String hoVaTen = rs.getString("hovaten");
				String diaChi = rs.getString("diachi");
				String gioiTinh = rs.getString("gioitinh");
				String diaChiNhanHang = rs.getString("diachinhanhang");
				String diaChiMuaHang = rs.getString("diachimuahang");
				Date ngaySinh = rs.getDate("ngaysinh");
				String soDienThoai = rs.getString("sodienthoai");
				String email = rs.getString("email");
				boolean dangKiNhanTin = rs.getBoolean("dangkinhantin");

				KhachHang khachHang = new KhachHang(maKhachHang, tenDangNhap, password, hoVaTen, diaChi, gioiTinh,
						diaChiNhanHang, diaChiMuaHang, ngaySinh, soDienThoai, email, dangKiNhanTin, null);
				ketQua.add(khachHang);
			}

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public KhachHang selectById(KhachHang t) {
		KhachHang ketQua = null;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "SELECT * FROM user WHERE tendangnhap = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getTenDangNhap());
			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				int maKhachHang = rs.getInt("makhachhang");
				String tenDangNhap = rs.getString("tendangnhap");
				String password = rs.getString("matkhau");
				String hoVaTen = rs.getString("hovaten");
				String diaChi = rs.getString("diachi");
				String gioiTinh = rs.getString("gioitinh");
				String diaChiNhanHang = rs.getString("diachinhanhang");
				String diaChiMuaHang = rs.getString("diachimuahang");
				Date ngaySinh = rs.getDate("ngaysinh");
				String soDienThoai = rs.getString("sodienthoai");
				boolean dangKiNhanTin = rs.getBoolean("dangkinhantin");
				String email = rs.getString("email");
				String duongDanAnh = rs.getString("duongdananh");

				ketQua = new KhachHang(maKhachHang, tenDangNhap, password, hoVaTen, diaChi, gioiTinh, diaChiNhanHang,
						diaChiMuaHang, ngaySinh, soDienThoai, email, dangKiNhanTin, duongDanAnh);
				break;
			}

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public KhachHang selectByUserAndPassWord(KhachHang t) {
		KhachHang ketQua = null;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "SELECT * FROM user WHERE tendangnhap = ? and matkhau=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getTenDangNhap());
			pst.setString(2, t.getPassword());

			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				String tenDangNhap = rs.getString("tendangnhap");
				String password = rs.getString("matkhau");
				String hoVaTen = rs.getString("hovaten");
				String soDienThoai = rs.getString("sodienthoai");
				String email = rs.getString("email");
				String duongdanhanh = rs.getString("duongdananh");

				ketQua = new KhachHang(tenDangNhap, password, hoVaTen, null, null, null, null, null, soDienThoai, email,
						false, duongdanhanh);
				break;
			}

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int insert(KhachHang t) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "INSERT INTO daye_shop.user (tendangnhap, matkhau, hovaten, sodienthoai, email) VALUES (?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, t.getTenDangNhap());
			pst.setString(2, t.getPassword());
			pst.setString(3, t.getHoVaTen());
			pst.setString(4, t.getSoDienThoai());
			pst.setString(5, t.getEmail());

			// bước 3: thực thi câu lệnh SQL
			ketQua = pst.executeUpdate();

			// bước 4: xử lý kết quả
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("có " + ketQua + " dòng bị thay đổi!");

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ketQua;
	}

	@Override
	public int insertAll(ArrayList<KhachHang> lists) {
		// TODO Auto-generated method stub
		int dem = 0;
		for (KhachHang tacGia : lists) {
			dem += this.insert(tacGia);
		}
		return dem;
	}

	@Override
	public int delete(KhachHang t) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "DELETE FROM `daye_shop`.`user` WHERE (`makhachhang` =?" + ")";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getMaKhachHang());
			// bước 3: thực thi câu lệnh SQL
			ketQua = pst.executeUpdate();

			// bước 4: xử lý kết quả
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("có " + ketQua + " dòng bị thay đổi!");

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ketQua;
	}

	@Override
	public int deleteAll(ArrayList<KhachHang> lists) {
		// TODO Auto-generated method stub
		int dem = 0;
		for (KhachHang tacGia : lists) {
			dem += this.delete(tacGia);
		}
		return dem;
	}

	@Override
	public int update(KhachHang t) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "UPDATE `myweb`.`khachhang` SET `password` = ?" + ", `hovaten` = ?" + ", `diachi` = ?"
					+ ", `gioitinh` = ?" + ", `diachinhanhang` = ?" + ", `diachimuahang` = ?" + ", `ngaysinh` = ?"
					+ ", `sodienthoai` = ?" + ", `email` = ?" + ", `dangkinhantin` = ?" + " WHERE (`makhachhang` = ?"
					+ ")";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getPassword());
			pst.setString(2, t.getHoVaTen());
			pst.setString(3, t.getDiaChi());
			pst.setString(4, t.getGioiTinh());
			pst.setString(5, t.getDiaChiNhanHang());
			pst.setString(6, t.getDiaChiMuaHang());
			pst.setDate(7, t.getNgaySinh());
			pst.setString(8, t.getSoDienThoai());
			pst.setString(9, t.getEmail());
			pst.setBoolean(10, t.isDangKiNhanTin());
			pst.setInt(11, t.getMaKhachHang());

			// bước 3: thực thi câu lệnh SQL
			ketQua = pst.executeUpdate();

			// bước 4: xử lý kết quả
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("có " + ketQua + " dòng bị thay đổi!");

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public int updateInfo(KhachHang t) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "UPDATE `daye_shop`.`user` SET `hovaten` = ?" + ", `sodienthoai` = ?" + ", `email` = ?"
					+ ", `duongdananh` = ?" + " WHERE (`tendangnhap` = ?" + ")";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getHoVaTen());
			pst.setString(2, t.getSoDienThoai());
			pst.setString(3, t.getEmail());
			pst.setString(4, t.getDuongDanAnh());
			pst.setString(5, t.getTenDangNhap());

			// bước 3: thực thi câu lệnh SQL
			ketQua = pst.executeUpdate();

			// bước 4: xử lý kết quả
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("có " + ketQua + " dòng bị thay đổi!");

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public boolean changePassword(KhachHang t) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "UPDATE `daye_shop`.`user` SET `matkhau` = ?" + " WHERE (`tendangnhap` = ?" + ")";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getPassword());
			pst.setString(2, t.getTenDangNhap());

			// bước 3: thực thi câu lệnh SQL
			ketQua = pst.executeUpdate();

			// bước 4: xử lý kết quả
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("có " + ketQua + " dòng bị thay đổi!");

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua > 0;
	}

	public boolean kiemTraTenDangNhap(String tenDangNhap) {
		boolean ketQua = false;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "SELECT * FROM user WHERE tenDangNhap = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, tenDangNhap);
			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				ketQua = true;
			}

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public boolean kiemTraKhoangTrangHaiDauTenDangNhap(String tenDangNhap) {
		String tenDangNhapTrim = tenDangNhap.trim();
		if (!tenDangNhap.equals(tenDangNhapTrim)) {
			return true;
		}
		return false;
	}

	public boolean kiemTraKhoangTrangTrongTenDangNhap(String tenDangNhap) {
		String tenDangNhapTrim = tenDangNhap.trim();
		for (int i = 0; i < tenDangNhapTrim.length(); i++) {
			if (Character.isWhitespace(tenDangNhapTrim.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	// đếm số lượng bản ghi trả về
	public int count1() {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();

			// bước 2: tạo đối tượng statement
			String sql = "select count(*) from daye_shop.user";
			PreparedStatement pst = con.prepareStatement(sql);

			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				ketQua = rs.getInt(1);
				break;
			}

			// bước 5:
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ketQua;
	}

	public ArrayList<KhachHang> selectListAndPaging(int size, int index) {
		ArrayList<KhachHang> ketQua = new ArrayList<KhachHang>();
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "select * from user LIMIT ? OFFSET ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, size);
			pst.setInt(2, index);

			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				int maKhachHang = rs.getInt("makhachhang");
				String tenDangNhap = rs.getString("tendangnhap");
				String hoVaTen = rs.getString("hovaten");
				String diaChi = rs.getString("diachi");
				String gioTinh = rs.getString("gioitinh");
				String diaChiNhanHang = rs.getString("diachinhanhang");
				String diaChiMuaHang = rs.getString("diachimuahang");
				Date ngaySinh = rs.getDate("ngaysinh");
				String soDienThoai = rs.getString("sodienthoai");
				Boolean dangKiNhanTin = rs.getBoolean("dangkinhantin");
				String duongDanAnh = rs.getString("duongdananh");
				String email = rs.getString("email");

				KhachHang khachHang = new KhachHang();
				khachHang.setMaKhachHang(maKhachHang);
				khachHang.setTenDangNhap(tenDangNhap);
				khachHang.setHoVaTen(hoVaTen);
				khachHang.setDiaChi(diaChi);
				khachHang.setDiaChiNhanHang(diaChiNhanHang);
				khachHang.setDiaChiMuaHang(diaChiMuaHang);
				khachHang.setGioiTinh(gioTinh);
				khachHang.setNgaySinh(ngaySinh);
				khachHang.setSoDienThoai(soDienThoai);
				khachHang.setDangKiNhanTin(dangKiNhanTin);
				khachHang.setEmail(email);

				ketQua.add(khachHang);
			}

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public KhachHang selectKhachHangByTenDangNhap(String tenDangNhap) {
		KhachHang ketQua = null;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "SELECT * FROM user WHERE tendangnhap = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, tenDangNhap);

			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				int maKhacHang = rs.getInt("makhachhang");
				String tenDangNhap1 = rs.getString("tendangnhap");
				String password = rs.getString("matkhau");
				String hoVaTen = rs.getString("hovaten");
				String soDienThoai = rs.getString("sodienthoai");
				String email = rs.getString("email");
				String duongDanAnh = rs.getString("duongdananh");

				ketQua = new KhachHang(maKhacHang, tenDangNhap1, password, hoVaTen, null, null, null, null, null,
						soDienThoai, email, false, duongDanAnh);
				break;
			}

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}
}
