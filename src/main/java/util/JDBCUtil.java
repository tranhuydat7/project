package util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

import com.mysql.jdbc.Driver;

public class JDBCUtil {

	public static Connection getConnection() {
		Connection connection = null;

		try {
			DriverManager.registerDriver(new Driver());

			String url = "jdbc:mySQL://localhost:3306/daye_shop";
			String username = "root";
			String password = "12345678";
			
			connection=DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void closeConnection(Connection connection) {
		try {
			if (connection!=null) {
				connection.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void printInfo(Connection connection) {
		if (connection!=null) {
			try {
				DatabaseMetaData databaseMetaData=connection.getMetaData();
				
				System.out.println(databaseMetaData.getDatabaseProductName());
				System.out.println(databaseMetaData.getDatabaseProductVersion());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
}
