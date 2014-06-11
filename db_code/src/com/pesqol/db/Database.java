package com.pesqol.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private static String url = "jdbc:mysql://localhost:3306/";
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String userName = "root";
	private static String password = "moazzam";
	private static String dbName = "pesqol";

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
}