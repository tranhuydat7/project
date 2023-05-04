package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DanhMuc;
import util.JDBCUtil;

public class DanhMucDAO implements DAOInterface<DanhMuc> {

	@Override
	public ArrayList<DanhMuc> selectAll() {
		ArrayList<DanhMuc> ketQua = new ArrayList<DanhMuc>();
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "SELECT * FROM danhmuc";
			PreparedStatement pst = con.prepareStatement(sql);

			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				String maDanhMuc = rs.getString("madanhmuc");
				String tenDanhMuc = rs.getString("tendanhmuc");
				String danhMucCha = rs.getString("danhmucchaid");

				DanhMuc dmcha = new DanhMuc();
				dmcha.setMaDanhMuc(danhMucCha);

				DanhMuc danhMuc = new DanhMuc();
				danhMuc.setMaDanhMuc(maDanhMuc);
				danhMuc.setTenDanhMuc(tenDanhMuc);
				danhMuc.setDanhMucCha(dmcha);

				ketQua.add(danhMuc);
			}

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public DanhMuc selectById(DanhMuc t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(DanhMuc t) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "INSERT INTO daye_shop.danhmuc (madanhmuc, tendanhmuc, danhmucchaid) VALUES (?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, t.getMaDanhMuc());
			pst.setString(2, t.getTenDanhMuc());
			pst.setString(3, t.getDanhMucCha().getMaDanhMuc());

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
	public int insertAll(ArrayList<DanhMuc> lists) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(DanhMuc t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<DanhMuc> lists) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(DanhMuc t) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "UPDATE `daye_shop`.`danhmuc` SET `tendanhmuc` = ?" + " WHERE (`madanhmuc` = ?" + ")";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getTenDanhMuc());
			pst.setString(2, t.getMaDanhMuc());

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

	public boolean kiemTraMaDanhMuc(String maDanhMuc) {
		boolean ketQua = false;
		try {
			// mo ket noi den Database
			Connection connection = JDBCUtil.getConnection();

			// tao doi tuong preparedStatement
			String sql = "SELECT * FROM danhmuc WHERE madanhmuc = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, maDanhMuc);

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

	public DanhMuc getDanhMucByMaDanhMuc(String maDanhMuc) {
		DanhMuc ketQua = null;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "SELECT * FROM danhmuc WHERE madanhmuc = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, maDanhMuc);
			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				String maDanhmuc = rs.getString("madanhmuc");
				String tenDanhMuc = rs.getString("tendanhmuc");
				String danhMucChaid = rs.getString("danhmucchaid");

				DanhMuc dmcha = new DanhMuc();
				dmcha.setMaDanhMuc(danhMucChaid);

				DanhMuc danhMuc = new DanhMuc();
				danhMuc.setMaDanhMuc(maDanhmuc);
				danhMuc.setTenDanhMuc(tenDanhMuc);
				danhMuc.setDanhMucCha(dmcha);

				ketQua = danhMuc;

				break;
			}

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public int deleteDanhMucByMaDanhMuc(String maDanhMuc) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "DELETE FROM `daye_shop`.`danhmuc` WHERE (`madanhmuc` =?" + ")";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, maDanhMuc);
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

	public ArrayList<DanhMuc> selectDanhMucByName(String key) {
		ArrayList<DanhMuc> ketQua = new ArrayList<DanhMuc>();
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "select * from daye_shop.danhmuc where tendanhmuc like ? or madanhmuc like ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, '%' + key + '%');
			pst.setString(2, '%' + key + '%');

			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				String maDanhMuc = rs.getString("madanhmuc");
				String tenDanhMuc = rs.getString("tendanhmuc");
				String danhMucCha = rs.getString("danhmucchaid");

				DanhMuc dmcha = new DanhMuc();
				dmcha.setMaDanhMuc(danhMucCha);

				DanhMuc danhMuc = new DanhMuc();
				danhMuc.setMaDanhMuc(maDanhMuc);
				danhMuc.setTenDanhMuc(tenDanhMuc);
				danhMuc.setDanhMucCha(dmcha);

				ketQua.add(danhMuc);
			}

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
			String sql = "select count(*) from daye_shop.danhmuc where tendanhmuc like ? or madanhmuc like ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, '%' + key + '%');
			pst.setString(2, '%' + key + '%');

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

	// đếm số lượng bản ghi trả về
	public int count1() {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();

			// bước 2: tạo đối tượng statement
			String sql = "select count(*) from daye_shop.danhmuc";
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

	public ArrayList<DanhMuc> selectListAndPaging(int size, int index) {
		ArrayList<DanhMuc> ketQua = new ArrayList<DanhMuc>();
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "select * from danhmuc LIMIT ? OFFSET ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, size);
			pst.setInt(2, index);

			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				String maDanhMuc = rs.getString("madanhmuc");
				String tenDanhMuc = rs.getString("tendanhmuc");
				String danhMucCha = rs.getString("danhmucchaid");

				DanhMuc dmcha = new DanhMuc();
				dmcha.setMaDanhMuc(danhMucCha);

				DanhMuc danhMuc = new DanhMuc();
				danhMuc.setMaDanhMuc(maDanhMuc);
				danhMuc.setTenDanhMuc(tenDanhMuc);
				danhMuc.setDanhMucCha(dmcha);

				ketQua.add(danhMuc);
			}

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public ArrayList<DanhMuc> searchAndPaging(String key, int size, int index) {
		ArrayList<DanhMuc> ketQua = new ArrayList<DanhMuc>();
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "select * from danhmuc where tendanhmuc like ? LIMIT ? OFFSET ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, '%' + key + '%');
			pst.setInt(2, size);
			pst.setInt(3, index);

			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				String maDanhMuc = rs.getString("madanhmuc");
				String tenDanhMuc = rs.getString("tendanhmuc");
				String danhMucCha = rs.getString("danhmucchaid");

				DanhMuc dmcha = new DanhMuc();
				dmcha.setMaDanhMuc(danhMucCha);

				DanhMuc danhMuc = new DanhMuc();
				danhMuc.setMaDanhMuc(maDanhMuc);
				danhMuc.setTenDanhMuc(tenDanhMuc);
				danhMuc.setDanhMucCha(dmcha);

				ketQua.add(danhMuc);
			}

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}
	
	public ArrayList<DanhMuc> selectListOrByName() {
		ArrayList<DanhMuc> ketQua = new ArrayList<DanhMuc>();
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "SELECT * FROM danhmuc ORDER BY tendanhmuc";
			PreparedStatement pst = con.prepareStatement(sql);

			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				String maDanhMuc = rs.getString("madanhmuc");
				String tenDanhMuc = rs.getString("tendanhmuc");
				String danhMucCha = rs.getString("danhmucchaid");

				DanhMuc dmcha = new DanhMuc();
				dmcha.setMaDanhMuc(danhMucCha);

				DanhMuc danhMuc = new DanhMuc();
				danhMuc.setMaDanhMuc(maDanhMuc);
				danhMuc.setTenDanhMuc(tenDanhMuc);
				danhMuc.setDanhMucCha(dmcha);

				ketQua.add(danhMuc);
			}

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}
}
