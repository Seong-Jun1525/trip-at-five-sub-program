package com.sj.room.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.sj.room.template.JDBCTemplate;

public class RoomDAO {

	private PreparedStatement pstmt = null;
	private Properties prop = new Properties();
	
	// ���� ���� �о����
		public RoomDAO() {
			try {
				prop.loadFromXML(new FileInputStream("resources/query.xml"));
			} catch (IOException e) {
				System.out.println("���� �б� ����");
				e.printStackTrace();
			}
		}

	// ���� ���� ����
	public int insertRoom(Connection conn, int accomSq, char c) {
//		System.out.println(c);
		String roomName = "����" + c;
		System.out.println(roomName);
		int result = 0;
		// int[] range = {10000, 100000}; // ���ݴ�
		int range = 100; // ���� API �����ؼ� ���� �׽�Ʈ ������ �ּ� ���� �ݾ��� 100����� ����
		// int idx = (int)(Math.random() * 2);
		int x = (int)(Math.random() * 9 + 4);
		
		int price = range * x;
		System.out.println(price);
		
		try {
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(prop.getProperty("insertRoom"));
			pstmt.setString(1, roomName);
			pstmt.setInt(2, price);
			pstmt.setInt(3, accomSq);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("�������� ����");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

}
