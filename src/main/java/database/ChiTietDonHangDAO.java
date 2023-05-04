package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ChiTietDonHang;
import model.DonHang;
import model.PhuongThucVanChuyen;
import model.SanPham;
import util.JDBCUtil;

public class ChiTietDonHangDAO implements DAOInterface<ChiTietDonHang> {

	@Override
	public ArrayList<ChiTietDonHang> selectAll() {
		ArrayList<ChiTietDonHang> ketQua = new ArrayList<ChiTietDonHang>();
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "SELECT * FROM chitietdonhang";
			PreparedStatement pst = con.prepareStatement(sql);

			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				String maChiTietDonHang = rs.getString("machitietdonhang");
				int maDonHang = rs.getInt("madonhang");
				String maSanPham = rs.getString("masanpham");
				int soLuong = rs.getInt("soluong");
				double tongTien = rs.getDouble("tongtien");

				DonHang donHang = new DonHang();
				donHang.setMaDonHang(maDonHang);

				SanPham sanPham = new SanPham();
				sanPham.setMaSanPham(maSanPham);

				ChiTietDonHang chiTietDonHang = new ChiTietDonHang(maChiTietDonHang, donHang, sanPham, soLuong,
						tongTien, null);
				ketQua.add(chiTietDonHang);
			}

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public ChiTietDonHang selectById(ChiTietDonHang t) {
		ChiTietDonHang ketQua = null;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "SELECT * FROM chitietdonhang WHERE machitietdonhang = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getMaChiTietDonHang());
			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				String maChiTietDonHang = rs.getString("machitietdonhang");
				int maDonHang = rs.getInt("madonhang");
				String maSanPham = rs.getString("masanpham");
				int soLuong = rs.getInt("soluong");
				double tongTien = rs.getDouble("tongtien");

				DonHang donHang = new DonHang();
				donHang.setMaDonHang(maDonHang);

				SanPham sanPham = new SanPham();
				sanPham.setMaSanPham(maSanPham);

				ketQua = new ChiTietDonHang(maChiTietDonHang, donHang, sanPham, soLuong, tongTien, null);
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
	public int insert(ChiTietDonHang t) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "INSERT INTO daye_shop.chitietdonhang (madonhang, masanpham, soluong, tongtien) VALUES (?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setInt(1, t.getDonHang().getMaDonHang());
			pst.setString(2, t.getSanPham().getMaSanPham());
			pst.setInt(3, t.getSoLuong());
			pst.setDouble(4, t.getTongTien());

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
	public int insertAll(ArrayList<ChiTietDonHang> lists) {
		int dem = 0;
		for (ChiTietDonHang tacGia : lists) {
			dem += this.insert(tacGia);
		}
		return dem;
	}

	@Override
	public int delete(ChiTietDonHang t) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "DELETE FROM `myweb`.`chitietdonhang` WHERE (`machitietdonhang` =?" + ")";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getMaChiTietDonHang());
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
	public int deleteAll(ArrayList<ChiTietDonHang> lists) {
		// TODO Auto-generated method stub
		int dem = 0;
		for (ChiTietDonHang tacGia : lists) {
			dem += this.delete(tacGia);
		}
		return dem;
	}

	// xoa tat ca nhung chi tiet don hang cua don hang do
	public int deleteAll(DonHang donHang) {
		int dem = 0;
		ArrayList<ChiTietDonHang> lists = this.selectAll();
		for (ChiTietDonHang chiTietDonHang : lists) {
			if (chiTietDonHang.getDonHang().equals(donHang)) {
				dem += this.delete(chiTietDonHang);
			}
		}
		return dem;
	}

	@Override
	public int update(ChiTietDonHang t) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "UPDATE `myweb`.`chitietdonhang` SET `madonhang` = ?" + ", `masanpham` = ?" + ", `soluong` = ?"
					+ ", `giabia` = ?" + ", `giamgia` = ?" + ", `giaban` = ?" + ", `thuevat` = ?" + ", `tongtien` = ?"
					+ " WHERE (`machitietdonhang` = ?" + ")";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getDonHang().getMaDonHang());
			pst.setString(2, t.getSanPham().getMaSanPham());
			pst.setInt(3, t.getSoLuong());
			pst.setDouble(8, t.getTongTien());
			pst.setString(9, t.getMaChiTietDonHang());

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

	public ChiTietDonHang selectChiTietDonHangByMaDonHang(String maDH) {
		ChiTietDonHang ketQua = new ChiTietDonHang();
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "SELECT * FROM chitietdonhang WHERE madonhang = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, maDH);
			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				int maDonHang = rs.getInt("madonhang");
				String maSanPham = rs.getString("masanpham");
				int soLuong = rs.getInt("soluong");
				double tongTien = rs.getDouble("tongtien");
				String maPhuongThuc = rs.getString("maphuongthuc");

				DonHang donHang = new DonHang();
				donHang.setMaDonHang(maDonHang);

				SanPham sanPham = new SanPham();
				sanPham.setMaSanPham(maSanPham);

				PhuongThucVanChuyen phuongThucVanChuyen = new PhuongThucVanChuyen();
				phuongThucVanChuyen.setMaPhuongThuc(maPhuongThuc);

				ketQua.setDonHang(donHang);
				ketQua.setSanPham(sanPham);
				ketQua.setPhuongThucVanChuyen(phuongThucVanChuyen);
				ketQua.setSoLuong(soLuong);
				ketQua.setTongTien(tongTien);
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
