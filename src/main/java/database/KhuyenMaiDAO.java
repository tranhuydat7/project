package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.KhuyenMai;
import model.KichCo;
import util.JDBCUtil;

public class KhuyenMaiDAO implements DAOInterface<KhuyenMai> {

	@Override
	public ArrayList<KhuyenMai> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KhuyenMai selectById(KhuyenMai t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(KhuyenMai t) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "INSERT INTO daye_shop.khuyenmai (makhuyenmai, tenkhuyenmai, giamgiatu, giamgiamax,giamphantram,ngaybatdau,ngayketthuc) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, t.getMaKhuyenMai());
			pst.setString(2, t.getTenKhuyenMai());
			pst.setInt(3, t.getGiamGiaTu());
			pst.setInt(4, t.getGiamGiaMax());
			pst.setInt(5, t.getGiamPhanTram());
			pst.setDate(6, t.getNgayBatDau());
			pst.setDate(7, t.getNgayKetThuc());

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
	public int insertAll(ArrayList<KhuyenMai> lists) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(KhuyenMai t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<KhuyenMai> lists) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(KhuyenMai t) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "UPDATE `daye_shop`.`khuyenmai` SET `tenkhuyenmai` = ?" + ", `giamgiatu` = ?"
					+ ", `giamgiamax` = ?" + ", `giamphantram` = ?" + ", `ngaybatdau` = ?" + ", `ngayketthuc` = ?"
					+ " WHERE (`makhuyenmai` = ?" + ")";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getTenKhuyenMai());
			pst.setInt(2, t.getGiamGiaTu());
			pst.setInt(3, t.getGiamGiaMax());
			pst.setInt(4, t.getGiamPhanTram());
			pst.setDate(5, t.getNgayBatDau());
			pst.setDate(6, t.getNgayKetThuc());
			pst.setString(7, t.getMaKhuyenMai());

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
			String sql = "select count(*) from daye_shop.khuyenmai";
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

	public ArrayList<KhuyenMai> selectListAndPaging(int size, int index) {
		ArrayList<KhuyenMai> ketQua = new ArrayList<KhuyenMai>();
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "select * from khuyenmai LIMIT ? OFFSET ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, size);
			pst.setInt(2, index);

			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				String maKhuyenMai = rs.getString("makhuyenmai");
				String tenKhuyenMai = rs.getString("tenkhuyenmai");
				int giamGiaTu = rs.getInt("giamgiatu");
				int giamGiaMax = rs.getInt("giamgiamax");
				int giamGiaPhanTram = rs.getInt("giamphantram");
				Date ngayBatDau = rs.getDate("ngaybatdau");
				Date ngayKetThuc = rs.getDate("ngayketthuc");

				KhuyenMai khuyenMai = new KhuyenMai(maKhuyenMai, tenKhuyenMai, giamGiaTu, giamGiaMax, giamGiaPhanTram,
						ngayBatDau, ngayKetThuc);

				ketQua.add(khuyenMai);
			}

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public boolean kiemTraMaKhuyenMai(String maKhuyenMai) {
		boolean ketQua = false;
		try {
			// mo ket noi den Database
			Connection connection = JDBCUtil.getConnection();

			// tao doi tuong preparedStatement
			String sql = "SELECT * FROM khuyenmai WHERE makhuyenmai = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, maKhuyenMai);

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

	public int deleteKhuyenMaiByMaKhuyenMai(String maKhuyenMai) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "DELETE FROM `daye_shop`.`khuyenmai` WHERE (`makhuyenmai` =?" + ")";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, maKhuyenMai);
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

	public KhuyenMai getKhuyenMaiByMaKhuyenMai(String maKhuyenMai) {
		KhuyenMai ketQua = null;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "SELECT * FROM khuyenmai WHERE makhuyenmai = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, maKhuyenMai);
			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				String maKM = rs.getString("makhuyenmai");
				String tenKhuyenMai = rs.getString("tenkhuyenmai");
				int giamGiaTu = rs.getInt("giamgiatu");
				int giamGiaMax = rs.getInt("giamgiamax");
				int giamGiaPhanTram = rs.getInt("giamphantram");
				Date ngayBatDau = rs.getDate("ngaybatdau");
				Date ngayKetThuc = rs.getDate("ngayketthuc");

				KhuyenMai khuyenMai = new KhuyenMai(maKM, tenKhuyenMai, giamGiaTu, giamGiaMax, giamGiaPhanTram,
						ngayBatDau, ngayKetThuc);

				ketQua = khuyenMai;
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
