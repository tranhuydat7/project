package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DanhMuc;
import model.KhuyenMai;
import model.KichCo;
import model.Mau;
import model.SanPham;
import util.JDBCUtil;

public class SanPhamDAO implements DAOInterface<SanPham> {

	@Override
	public ArrayList<SanPham> selectAll() {
		ArrayList<SanPham> ketQua = new ArrayList<SanPham>();
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "SELECT * FROM sanpham";
			PreparedStatement pst = con.prepareStatement(sql);

			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				String maSanPham = rs.getString("masanpham");
				String tenSanPham = rs.getString("tensanpham");
				int giaBan = rs.getInt("giaban");
				int giaGoc = rs.getInt("giagoc");
				int soLuong = rs.getInt("soluong");
				String moTa = rs.getString("mota");
				String maDanhMuc = rs.getString("madanhmuc");
				String maMau = rs.getString("mamau");
				String maKichCo = rs.getString("makichco");
				String avatar = rs.getString("avatar");
				String cachSuDung = rs.getString("cachsudung");
				String chatLieu = rs.getString("chatlieu");

				DanhMuc danhMuc = new DanhMuc();
				danhMuc.setMaDanhMuc(maDanhMuc);
				Mau mau = new Mau();
				mau.setMaMau(maMau);
				KichCo kichCo = new KichCo();
				kichCo.setMaKichCo(maKichCo);

				SanPham sanPham = new SanPham(maSanPham, tenSanPham, giaGoc, giaBan, soLuong, moTa, danhMuc, mau,
						kichCo, avatar, cachSuDung, chatLieu);

				ketQua.add(sanPham);
			}

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public SanPham selectById(SanPham t) {
		SanPham ketQua = null;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "SELECT * FROM sanpham WHERE masanpham = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getMaSanPham());
			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				String maSanPham = rs.getString("masanpham");
				String tenSanPham = rs.getString("tensanpham");
				String matacgia = rs.getString("matacgia");
				int namXuatBan = rs.getInt("namxuatban");
				int giaNhap = rs.getInt("gianhap");
				int giaGoc = rs.getInt("giagoc");
				int giaBan = rs.getInt("giaban");
				int soLuong = rs.getInt("soluong");
				String maTheLoai = rs.getString("matheloai");
				String ngonNgu = rs.getString("ngonngu");
				String moTa = rs.getString("mota");

				ketQua = new SanPham(maSanPham, tenSanPham, giaGoc, giaBan, namXuatBan, moTa, null, null, null,
						maTheLoai, ngonNgu, moTa);
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
	public int insert(SanPham t) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "INSERT INTO sanpham (masanpham, tensanpham, giaban, giagoc, soluong, mota, madanhmuc, mamau, makichco, avatar, cachsudung, chatlieu) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, t.getMaSanPham());
			pst.setString(2, t.getTenSanPham());
			pst.setInt(3, t.getGiaBan());
			pst.setInt(4, t.getGiaGoc());
			pst.setInt(5, t.getSoLuong());
			pst.setString(6, t.getMoTa());
			pst.setString(7, t.getDanhMuc().getMaDanhMuc());
			pst.setString(8, t.getMauSac().getMaMau());
			pst.setString(9, t.getKichCo().getMaKichCo());
			pst.setString(10, t.getAvatar());
			pst.setString(11, t.getCachSuDung());
			pst.setString(12, t.getChatLieu());

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
	public int insertAll(ArrayList<SanPham> lists) {
		int dem = 0;
		for (SanPham sP : lists) {
			dem += this.insert(sP);
		}
		return dem;
	}

	@Override
	public int delete(SanPham t) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "DELETE FROM `myweb`.`sanpham` WHERE (`masanpham` =?" + ")";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getMaSanPham());
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
	public int deleteAll(ArrayList<SanPham> lists) {
		// TODO Auto-generated method stub
		int dem = 0;
		for (SanPham sP : lists) {
			dem += this.delete(sP);
		}
		return dem;
	}

	@Override
	public int update(SanPham t) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "UPDATE `daye_shop`.`sanpham` SET `tensanpham` = ?" + ", `giaban` = ?" + ", `giagoc` = ?"
					+ ", `soluong` = ?" + ", `mota` = ?" + ", `madanhmuc` = ?" + ", `mamau` = ?" + ", `makichco` = ?"
					+ ", `avatar` = ?" + ", `cachsudung` = ?" + ", `chatlieu` = ?" + " WHERE (`masanpham` = ?" + ")";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getTenSanPham());
			pst.setInt(2, t.getGiaBan());
			pst.setInt(3, t.getGiaGoc());
			pst.setInt(4, t.getSoLuong());
			pst.setString(5, t.getMoTa());
			pst.setString(6, t.getDanhMuc().getMaDanhMuc());
			pst.setString(7, t.getMauSac().getMaMau());
			pst.setString(8, t.getKichCo().getMaKichCo());
			pst.setString(9, t.getAvatar());
			pst.setString(10, t.getCachSuDung());
			pst.setString(11, t.getChatLieu());
			pst.setString(12, t.getMaSanPham());

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
			String sql = "select count(*) from daye_shop.sanpham";
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

	public ArrayList<SanPham> selectListAndPaging(int size, int index) {
		ArrayList<SanPham> ketQua = new ArrayList<SanPham>();
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "select * from sanpham LIMIT ? OFFSET ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, size);
			pst.setInt(2, index);

			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				String maSanPham = rs.getString("masanpham");
				String tenSanPham = rs.getString("tensanpham");
				int giaBan = rs.getInt("giaban");
				int giaGoc = rs.getInt("giagoc");
				int soLuong = rs.getInt("soluong");
				String moTa = rs.getString("mota");
				String maDanhMuc = rs.getString("madanhmuc");
				String maMau = rs.getString("mamau");
				String maKichCo = rs.getString("makichco");
				String avatar = rs.getString("avatar");
				String cachSuDung = rs.getString("cachsudung");
				String chatLieu = rs.getString("chatlieu");

				DanhMuc danhMuc = new DanhMuc();
				danhMuc.setMaDanhMuc(maDanhMuc);
				Mau mau = new Mau();
				mau.setMaMau(maMau);
				KichCo kichCo = new KichCo();
				kichCo.setMaKichCo(maKichCo);

				SanPham sanPham = new SanPham(maSanPham, tenSanPham, giaGoc, giaBan, soLuong, moTa, danhMuc, mau,
						kichCo, avatar, cachSuDung, chatLieu);

				ketQua.add(sanPham);
			}

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public boolean kiemTraMaSanPham(String maSanPham) {
		boolean ketQua = false;
		try {
			// mo ket noi den Database
			Connection connection = JDBCUtil.getConnection();

			// tao doi tuong preparedStatement
			String sql = "SELECT * FROM sanpham WHERE masanpham = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, maSanPham);

			// thuc thi cau lenh SQL
			ResultSet resultset = preparedStatement.executeQuery();

			// xu ly ket qua
			while (resultset.next()) {
				ketQua = true;
			}

			// dong ket noi
			JDBCUtil.closeConnection(connection);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public SanPham getSanPhamByMaSanPham(String maSP) {
		SanPham ketQua = null;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "SELECT * FROM sanpham WHERE masanpham = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, maSP);
			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				String maSanPham = rs.getString("masanpham");
				String tenSanPham = rs.getString("tensanpham");
				int giaBan = rs.getInt("giaban");
				int giaGoc = rs.getInt("giagoc");
				int soLuong = rs.getInt("soluong");
				String moTa = rs.getString("mota");
				String maDanhMuc = rs.getString("madanhmuc");
				String maMau = rs.getString("mamau");
				String maKichCo = rs.getString("makichco");
				String avatar = rs.getString("avatar");
				String cachSuDung = rs.getString("cachsudung");
				String chatLieu = rs.getString("chatlieu");

				DanhMuc danhMuc = new DanhMuc();
				danhMuc.setMaDanhMuc(maDanhMuc);
				Mau mau = new Mau();
				mau.setMaMau(maMau);
				KichCo kichCo = new KichCo();
				kichCo.setMaKichCo(maKichCo);

				SanPham sanPham = new SanPham(maSanPham, tenSanPham, giaGoc, giaBan, soLuong, moTa, danhMuc, mau,
						kichCo, avatar, cachSuDung, chatLieu);

				ketQua = sanPham;
				break;
			}
			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public int deleteSanPhamByMaSanPham(String maSanPham) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "DELETE FROM `daye_shop`.`sanpham` WHERE (`masanpham` =?" + ")";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, maSanPham);
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

	// đếm số lượng bản ghi trả về theo keywork
	public int count(String key) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();

			// bước 2: tạo đối tượng statement
			String sql = "select count(*) from daye_shop.sanpham where tensanpham like ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, '%' + key + '%');

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

	public ArrayList<SanPham> searchAndPaging(String key, int size, int index) {
		ArrayList<SanPham> ketQua = new ArrayList<SanPham>();
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "select * from sanpham where tensanpham like ? LIMIT ? OFFSET ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, '%' + key + '%');
			pst.setInt(2, size);
			pst.setInt(3, index);

			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				String maSanPham = rs.getString("masanpham");
				String tenSanPham = rs.getString("tensanpham");
				int giaBan = rs.getInt("giaban");
				int giaGoc = rs.getInt("giagoc");
				int soLuong = rs.getInt("soluong");
				String moTa = rs.getString("mota");
				String maDanhMuc = rs.getString("madanhmuc");
				String maMau = rs.getString("mamau");
				String maKichCo = rs.getString("makichco");
				String avatar = rs.getString("avatar");
				String cachSuDung = rs.getString("cachsudung");
				String chatLieu = rs.getString("chatlieu");

				DanhMuc danhMuc = new DanhMuc();
				danhMuc.setMaDanhMuc(maDanhMuc);
				Mau mau = new Mau();
				mau.setMaMau(maMau);
				KichCo kichCo = new KichCo();
				kichCo.setMaKichCo(maKichCo);

				SanPham sanPham = new SanPham(maSanPham, tenSanPham, giaGoc, giaBan, soLuong, moTa, danhMuc, mau,
						kichCo, avatar, cachSuDung, chatLieu);

				ketQua.add(sanPham);
			}

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}
}
