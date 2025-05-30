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
	
	// 쿼리 파일 읽어오기
		public RoomDAO() {
			try {
				prop.loadFromXML(new FileInputStream("resources/query.xml"));
			} catch (IOException e) {
				System.out.println("파일 읽기 오류");
				e.printStackTrace();
			}
		}

	// 객실 정보 저장
	public int insertRoom(Connection conn, int accomSq, char c) {
//		System.out.println(c);
		String roomName = "객실" + c;
		System.out.println(roomName);
		int result = 0;
		// int[] range = {10000, 100000}; // 가격대
		int range = 100; // 결제 API 연동해서 결제 테스트 때문에 최소 결제 금액인 100원대로 설정
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
			System.out.println("정보저장 오류");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

}
