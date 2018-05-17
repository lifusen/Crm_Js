package cn.itproject.crm.patch.commonpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	static final String URL_A = "jdbc:mysql://localhost:3306/crma?characterEncoding=UTF-8"; //之前的数据库
	static final String URL_B = "jdbc:mysql://localhost:3306/crm?characterEncoding=UTF-8";
	static final String USERNAME = "root";
	static final String PASSWORD = "root";

	static {
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError("加载驱动错误!");
		}
	}

	public static Connection getConnectionA() throws Exception {
		try {
			return DriverManager.getConnection(URL_A, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new Exception("获取A连接失败!");
		}
	}
	
	public static Connection getConnectionB() throws Exception {
		try {
			return DriverManager.getConnection(URL_B, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new Exception("获取B连接失败!");
		}
	}

	public static void close(ResultSet rs, PreparedStatement statement, Connection connection) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
