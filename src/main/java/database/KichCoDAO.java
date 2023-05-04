package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.KichCo;
import model.Mau;
import util.JDBCUtil;

public class KichCoDAO implements DAOInterface<KichCo> {

	@Override
	public ArrayList<KichCo> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KichCo selectById(KichCo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(KichCo t) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "INSERT INTO daye_shop.kichco (makichco, tenkichco) VALUES (?,?)";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, t.getMaKichCo());
			pst.setString(2, t.getTenKichCo());

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
	public int insertAll(ArrayList<KichCo> lists) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(KichCo t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<KichCo> lists) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(KichCo t) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "UPDATE `daye_shop`.`kichco` SET `tenkichco` = ?" + " WHERE (`makichco` = ?" + ")";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getTenKichCo());
			pst.setString(2, t.getMaKichCo());

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
			String sql = "select count(*) from daye_shop.kichco";
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

	public ArrayList<KichCo> selectListAndPaging(int size, int index) {
		ArrayList<KichCo> ketQua = new ArrayList<KichCo>();
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "select * from kichco LIMIT ? OFFSET ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, size);
			pst.setInt(2, index);

			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				String maKichCo = rs.getString("makichco");
				String tenKichCo = rs.getString("tenkichco");

				KichCo kichCo = new KichCo();
				kichCo.setMaKichCo(maKichCo);
				kichCo.setTenKichCo(tenKichCo);

				ketQua.add(kichCo);
			}

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public boolean kiemTraMaKichCo(String maKichCo) {
		boolean ketQua = false;
		try {
			// mo ket noi den Database
			Connection connection = JDBCUtil.getConnection();

			// tao doi tuong preparedStatement
			String sql = "SELECT * FROM kichco WHERE makichco = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, maKichCo);

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

	public int deleteKichCoByMaKichCo(String maKichCo) {
		int ketQua = 0;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "DELETE FROM `daye_shop`.`kichco` WHERE (`makichco` =?" + ")";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, maKichCo);
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

	public KichCo getKichCoByMaKichCo(String maKichCo) {
		KichCo ketQua = null;
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "SELECT * FROM kichco WHERE makichco = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, maKichCo);
			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				String maKC = rs.getString("makichco");
				String tenKichCo = rs.getString("tenkichco");

				KichCo kichCo = new KichCo(maKC, tenKichCo);

				ketQua = kichCo;
				break;
			}
			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public ArrayList<KichCo> selectListOrByName() {
		ArrayList<KichCo> ketQua = new ArrayList<KichCo>();
		try {
			// bước 1: kết nối đến csdl
			Connection con = JDBCUtil.getConnection();
			// bước 2: tạo đối tượng statement
			String sql = "SELECT * FROM kichco ORDER BY tenkichco";
			PreparedStatement pst = con.prepareStatement(sql);

			// bước 3: thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// bước 4: xử lý kết quả
			while (rs.next()) {
				String maKichCo = rs.getString("makichco");
				String tenKichCo = rs.getString("tenkichco");

				KichCo kichCo = new KichCo();
				kichCo.setMaKichCo(maKichCo);
				kichCo.setTenKichCo(tenKichCo);

				ketQua.add(kichCo);
			}

			// bước 5:
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}
}
