package com.sj.room.template;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	// 객체를 매번 생성하지 않고 메서드를 바로 호출하기 위해 static으로 선언
	
	/**
	 * Connection 생성 객체
	 */
	public static Connection createConnection() {
		Connection conn = null;
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream("resources/settings.properties"));
		} catch (IOException e) {
			System.out.println("resources/settings.properties 오류");
			e.printStackTrace();
		}
		
		try {
			Class.forName(prop.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("Driver 오류");
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
		} catch (SQLException e) {
			System.out.println("Connection 오류");
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * Connection 객체 반납
	 */
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.close();
		} catch (SQLException e) {
			System.out.println("Connection close 오류");
			e.printStackTrace();
		}
	}
	
	/**
	 * Statement 객체 반납
	 * 		PreparedStatement 객체는 Statement객체를 상속받고 있으므로
	 * 		Statement 객체로 받아서 반납하면 된다.
	 */
	public static void close(Statement stat) {
		try {
			if(stat != null && !stat.isClosed()) stat.close();
		} catch (SQLException e) {
			System.out.println("Statement/PreparedStatement close 오류");
			e.printStackTrace();
		}
	}
	
	/**
	 * ResultSet 객체 반납
	 */
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) rset.close();
		} catch (SQLException e) {
			System.out.println("ResultSet close 오류");
			e.printStackTrace();
		}
	}
	
	/**
	 * commit 처리
	 */
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.commit();
		} catch (SQLException e) {
			System.out.println("Connection commit 오류");
			e.printStackTrace();
		}
	}
	
	/**
	 * rollback 처리
	 */
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.rollback();
		} catch (SQLException e) {
			System.out.println("Connection rollback 오류");
			e.printStackTrace();
		}
	}
}
