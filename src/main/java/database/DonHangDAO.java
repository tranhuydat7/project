package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DanhMuc;
import model.DonHang;
import model.KhachHang;
import model.KhuyenMai;
import model.KichCo;
import model.Mau;
import model.SanPham;
import util.JDBCUtil;

public class DonHangDAO implements DAOInterface<DonHang> {

	@Override
	public ArrayList<DonHang> selectAll() {
		ArrayList<DonHang> ketQua = new ArrayList<DonHang>();
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "SELECT * FROM donhang";
			PreparedStatement pst = con.prepareStatement(sql);

			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				int maDonHang = rs.getInt("madonhang");
				int maKhachHang = rs.getInt("makhachhang");
				String diaChiNhanHang = rs.getString("diachinhanhang");
				String trangThai = rs.getString("trangthai");
				String hinhThucThanhToan = rs.getString("hinhthucthanhtoan");
				String trangThaiThanhToan = rs.getString("trangthaithanhtoan");
				Date ngayDatHang = rs.getDate("ngaydathang");
				Date ngayGiaoHang = rs.getDate("ngaygiaohang");

				KhachHang khachHang = new KhachHang(maKhachHang, null, null, null, null, null, null, null, null, null,
						null, false);

				DonHang donHang = new DonHang(maDonHang, khachHang, diaChiNhanHang, trangThai, hinhThucThanhToan,
						trangThaiThanhToan, ngayDatHang, ngayGiaoHang);
				ketQua.add(donHang);
			}

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public DonHang selectById(DonHang t) {
		DonHang ketQua = null;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "SELECT * FROM donhang WHERE madonhang = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getMaDonHang());
			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				int maDonHang = rs.getInt("madonhang");
				int maKhachHang = rs.getInt("makhachhang");
				String diaChiNhanHang = rs.getString("diachinhanhang");
				String trangThai = rs.getString("trangthai");
				String hinhThucThanhToan = rs.getString("hinhthucthanhtoan");
				String trangThaiThanhToan = rs.getString("trangthaithanhtoan");
				Date ngayDatHang = rs.getDate("ngaydathang");
				Date ngayGiaoHang = rs.getDate("ngaygiaohang");

				KhachHang khachHang = new KhachHang(maKhachHang, null, null, null, null, null, null, null, null, null,
						null, false);

				ketQua = new DonHang(maDonHang, khachHang, diaChiNhanHang, trangThai, hinhThucThanhToan,
						trangThaiThanhToan, ngayDatHang, ngayGiaoHang);
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
	public int insert(DonHang t) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "INSERT INTO daye_shop.donhang (madonhang, makhachhang ,ngaydathang, ngaygiaohang, hinhthucthanhtoan, trangthaithanhtoan, trangthai, diachinhanhang) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getMaDonHang());
			pst.setInt(2, t.getKhachHang().getMaKhachHang());
			pst.setDate(3, t.getNgayDatHang());
			pst.setDate(4, t.getNgayGiaoHang());
			pst.setString(5, t.getHinhThucThanhToan());
			pst.setString(6, t.getTrangThaiThanhToan());
			pst.setString(7, t.getTrangThai());
			pst.setString(8, t.getDiaChiNhanHang());

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
	public int insertAll(ArrayList<DonHang> lists) {
		// TODO Auto-generated method stub
		int dem = 0;
		for (DonHang tacGia : lists) {
			dem += this.insert(tacGia);
		}
		return dem;
	}

	@Override
	public int delete(DonHang t) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "DELETE FROM `myweb`.`donhang` WHERE (`madonhang` =?" + ")";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getMaDonHang());
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
	public int deleteAll(ArrayList<DonHang> lists) {
		// TODO Auto-generated method stub
		int dem = 0;
		for (DonHang tacGia : lists) {
			dem += this.delete(tacGia);
		}
		return dem;
	}

	@Override
	public int update(DonHang t) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "UPDATE `myweb`.`donhang` SET `makhachhang` = ?" + ", `diachinguoimua` = ?"
					+ ", `diachinhanhang` = ?" + ", `trangthai` = ?" + ", `hinhthucthanhtoan` = ?"
					+ ", `trangthaithanhtoan` = ?" + ", `sotiendathanhtoan` = ?" + ", `sotienconthieu` = ?"
					+ ", `ngaydathang` = ?" + ", `ngaygiaohang` = ?" + " WHERE (`madonhang` = ?" + ")";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getKhachHang().getMaKhachHang());
			pst.setString(3, t.getDiaChiNhanHang());
			pst.setString(4, t.getTrangThai());
			pst.setString(5, t.getHinhThucThanhToan());
			pst.setString(6, t.getTrangThaiThanhToan());
			pst.setDate(9, t.getNgayDatHang());
			pst.setDate(10, t.getNgayGiaoHang());
			pst.setInt(11, t.getMaDonHang());

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

	// đếm số lượng bản ghi trả về
	public int count1() {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();

			// bước 2: tạo đối tượng statement
			String sql = "select count(*) from daye_shop.donhang";
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

	public ArrayList<DonHang> selectListAndPaging(int size, int index) {
		ArrayList<DonHang> ketQua = new ArrayList<DonHang>();
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "select * from donhang LIMIT ? OFFSET ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, size);
			pst.setInt(2, index);

			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				int maDonHang = rs.getInt("madonhang");
				int maKhachHang = rs.getInt("makhachhang");
				String maKhuyenMai = rs.getString("makhuyenmai");
				Date ngayDatHang = rs.getDate("ngaydathang");
				Date ngayGiaoHang = rs.getDate("ngaygiaohang");
				String hinhThucThanhToan = rs.getString("hinhthucthanhtoan");
				String trangThaiThanhToan = rs.getString("trangthaithanhtoan");
				String trangThai = rs.getString("trangthai");
				String diaChiNhanHang = rs.getString("diachinhanhang");

				KhachHang khachHang = new KhachHang();
				khachHang.setMaKhachHang(maKhachHang);

				KhuyenMai khuyenMai = new KhuyenMai();
				khuyenMai.setMaKhuyenMai(maKhuyenMai);

				DonHang donHang = new DonHang(maDonHang, khachHang, diaChiNhanHang, trangThai, hinhThucThanhToan,
						trangThaiThanhToan, ngayDatHang, ngayGiaoHang);

				ketQua.add(donHang);
			}

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}
}
