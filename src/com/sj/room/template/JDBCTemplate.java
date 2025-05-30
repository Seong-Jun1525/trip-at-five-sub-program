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
	// ��ü�� �Ź� �������� �ʰ� �޼��带 �ٷ� ȣ���ϱ� ���� static���� ����
	
	/**
	 * Connection ���� ��ü
	 */
	public static Connection createConnection() {
		Connection conn = null;
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream("resources/settings.properties"));
		} catch (IOException e) {
			System.out.println("resources/settings.properties ����");
			e.printStackTrace();
		}
		
		try {
			Class.forName(prop.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("Driver ����");
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
		} catch (SQLException e) {
			System.out.println("Connection ����");
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * Connection ��ü �ݳ�
	 */
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.close();
		} catch (SQLException e) {
			System.out.println("Connection close ����");
			e.printStackTrace();
		}
	}
	
	/**
	 * Statement ��ü �ݳ�
	 * 		PreparedStatement ��ü�� Statement��ü�� ��ӹް� �����Ƿ�
	 * 		Statement ��ü�� �޾Ƽ� �ݳ��ϸ� �ȴ�.
	 */
	public static void close(Statement stat) {
		try {
			if(stat != null && !stat.isClosed()) stat.close();
		} catch (SQLException e) {
			System.out.println("Statement/PreparedStatement close ����");
			e.printStackTrace();
		}
	}
	
	/**
	 * ResultSet ��ü �ݳ�
	 */
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) rset.close();
		} catch (SQLException e) {
			System.out.println("ResultSet close ����");
			e.printStackTrace();
		}
	}
	
	/**
	 * commit ó��
	 */
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.commit();
		} catch (SQLException e) {
			System.out.println("Connection commit ����");
			e.printStackTrace();
		}
	}
	
	/**
	 * rollback ó��
	 */
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.rollback();
		} catch (SQLException e) {
			System.out.println("Connection rollback ����");
			e.printStackTrace();
		}
	}
}
