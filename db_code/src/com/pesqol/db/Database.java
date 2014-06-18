package com.pesqol.db;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pesqol.model.Student;

public class Database {

	private static String url = "jdbc:mysql://localhost:3306/";
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String userName = "root";
	private static String password = "moazzam";
	private static String dbName = "pesqol";

	private static Map<String, Boolean> tableMap = new HashMap<String, Boolean>();

	private static Connection dbCon = null;

	/**
	 * get connection to do database operations
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		try {
			Class.forName(driverName);
			dbCon = DriverManager.getConnection(url + dbName, userName,
					password);

		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
			return null;
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dbCon;
	}

	public static void setPacketSize(long size) throws SQLException {
		Connection conn = getConnection();

		/*
		 * 104857600 for 10 MB
		 */
		String querySetLimit = "SET GLOBAL max_allowed_packet=" + size + ";";
		Statement stSetLimit = conn.createStatement();
		stSetLimit.execute(querySetLimit);
	}

	public static boolean doesTableExist(String tableName) throws SQLException {
		Connection connection = getConnection();
		String query = "SHOW TABLES LIKE ?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, tableName);
		ResultSet result = ps.executeQuery();

		return result.next();
	}

	public static void save(Object o) {
		List<String> fields = new ArrayList<String>();
		Method[] methods = o.getClass().getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().startsWith("set")) {
				fields.add(method.getName().substring("set".length()));
				System.out.println(method.getParameterTypes()[0]);
			}
		}
		System.out.println(fields);
	}

	public static void main(String[] args) throws SQLException {
		// System.out.println(doesTableExist("i"));
		save(new Student());
		System.out.println(Double.parseDouble(null));
	}
}