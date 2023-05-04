package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DanhMuc;
import model.Mau;
import util.JDBCUtil;

public class MauDAO implements DAOInterface<Mau> {

	@Override
	public ArrayList<Mau> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mau selectById(Mau t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Mau t) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "INSERT INTO daye_shop.mausac (mamau, tenmau) VALUES (?,?)";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, t.getMaMau());
			pst.setString(2, t.getTenMau());

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
	public int insertAll(ArrayList<Mau> lists) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Mau t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<Mau> lists) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Mau t) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "UPDATE `daye_shop`.`mausac` SET `tenmau` = ?" + " WHERE (`mamau` = ?" + ")";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getTenMau());
			pst.setString(2, t.getMaMau());

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
			String sql = "select count(*) from daye_shop.mausac";
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

	public ArrayList<Mau> selectListAndPaging(int size, int index) {
		ArrayList<Mau> ketQua = new ArrayList<Mau>();
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "select * from mausac LIMIT ? OFFSET ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, size);
			pst.setInt(2, index);

			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				String maMau = rs.getString("mamau");
				String tenMau = rs.getString("tenmau");

				Mau mau = new Mau();
				mau.setMaMau(maMau);
				mau.setTenMau(tenMau);

				ketQua.add(mau);
			}

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public boolean kiemTraMaMau(String maMau) {
		boolean ketQua = false;
		try {
			// mo ket noi den Database
			Connection connection = JDBCUtil.getConnection();

			// tao doi tuong preparedStatement
			String sql = "SELECT * FROM mausac WHERE mamau = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, maMau);

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

	public Mau getMauSacByMaMauSac(String maMau) {
		Mau ketQua = null;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "SELECT * FROM mausac WHERE mamau = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, maMau);
			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				String maMauSac = rs.getString("mamau");
				String tenMau = rs.getString("tenmau");

				Mau mau = new Mau(maMauSac, tenMau);

				ketQua = mau;
				break;
			}
			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public int deleteMauByMaMau(String maMau) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "DELETE FROM `daye_shop`.`mausac` WHERE (`mamau` =?" + ")";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, maMau);
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

	public ArrayList<Mau> selectListOrByName() {
		ArrayList<Mau> ketQua = new ArrayList<Mau>();
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "SELECT * FROM mausac ORDER BY tenmau";
			PreparedStatement pst = con.prepareStatement(sql);

			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				String maMau = rs.getString("mamau");
				String tenMau = rs.getString("tenmau");

				Mau mau = new Mau();
				mau.setMaMau(maMau);
				mau.setTenMau(tenMau);

				ketQua.add(mau);
			}

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

}
