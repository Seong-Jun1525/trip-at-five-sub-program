package com.sj.room.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.sj.room.model.vo.Accom;
import com.sj.room.template.JDBCTemplate;

public class AccomDAO {
	
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	private Properties prop = new Properties();
	
	// 쿼리 파일 읽어오기
	public AccomDAO() {
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 숙박업소 시설정보 업데이트
	public int updateAccom(Connection conn, Accom acc) {
		int result = 0;
		
		String[] facArr = setFacInfo(acc.getAccomName());
		int accomTypeNo = setAccomType(acc.getAccomName());
		
		try {
			conn.setAutoCommit(false);
			
			/**
			 * 값이 null이라고 해서 임의의 데이터를 넣는 것은 좋은 방법이 아니다.
			 * 
			 * 왜냐하면 그 임의의 값이 어떤 컬럼에서는 임의의 값이 아닐 수 있기 때문이다.
			 * 
			 * 값이 없다는 것을 의미하는 것은 NULL이다. 그러므로 값이 없는 경우에는 NULL로 두는 것이 맞다.
			 */
			
			pstmt = conn.prepareStatement(prop.getProperty("updateAccom"));
			pstmt.setString(1, acc.getAccomDesc());
			pstmt.setString(2, acc.getAccomPhone());
			pstmt.setString(3, facArr[0]);
			pstmt.setString(4, facArr[1]);
			pstmt.setString(5, facArr[2]);
			pstmt.setInt(6, accomTypeNo);
			pstmt.setInt(7, acc.getAccomSq());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}

	// 숙박업소 유형 설정
	private int setAccomType(String accomName) {
		int type = 999;

		if(accomName.contains("모텔")) type = 21;
		else if(accomName.contains("호텔")) type = 22; 
		else if(accomName.contains("리조트")) type = 23;
		else if(accomName.contains("펜션")) type = 24;
		else if(accomName.contains("캠핑")) type = 25;
		else if(accomName.contains("한옥") || accomName.contains("게스트하우스") || accomName.contains("게하")) type = 26;
		
		
		return type;
	}

	// 숙박업소 시설 정보 설정
	private String[] setFacInfo(String accomName) {
		String[] result = new String[3]; 
		String info = "";
		
		if(accomName.contains("모텔")) info = "공용샤워실, 공용화장실, 매점 / 무선인터넷, 에어컨, 샤워실, 욕실용품, 개인콘센트, 미니바 / 무료주차, 객실내취사, 객실내흡연, 짐보관가능";
		else if(accomName.contains("호텔")) info = "사우나, 수영장, 레스토랑, 피트니스, 공용샤워실, 매점 / 무선인터넷, 에어컨, 샤워실, 욕실용품, 미니바, 스파/월풀, 개인콘센트 / 조식제공, 무료주차, 짐보관가능, 픽업서비스, 개인사물함"; 
		else if(accomName.contains("펜션")) info = "바베큐, 물놀이시설, 공용샤워실 / 객실내취사, 무선인터넷, 에어컨, 샤워실, 욕실용품, 개인콘센트 / 반려견동반, 무료주차, 캠프파이어, 조식제공";
		else if(accomName.contains("캠핑")) info = "바베큐, 물놀이시설, 공용샤워실, 공용화장실, 매점 / 무선인터넷, 개인콘센트, 욕실용품, 에어컨 / 캠프파이어, 반려견동반, 무료주차, 객실내취사, 짐보관가능";
		else if(accomName.contains("리조트")) info = "수영장, 바베큐, 레스토랑, 피트니스, 물놀이시설 / 객실스파, 무선인터넷, 에어컨, 욕실용품, 샤워실, 개인콘센트, 미니바 / 조식제공, 무료주차, 객실내취사, 캠프파이어, 픽업서비스, 짐보관가능";
		else if(accomName.contains("한옥") || accomName.contains("게스트하우스") || accomName.contains("게하")) info = "공용샤워실, 공용화장실, 바베큐, 매점 / 무선인터넷, 에어컨, 개인콘센트, 욕실용품, 샤워실 / 조식제공, 무료주차, 개인사물함, 짐보관가능, 객실내취사";
		
		return facFuc(result, info);
	}

	private String[] facFuc(String[] arr, String info) {
		if (info == null || info.trim().isEmpty()) return arr;
		String[] infoArr = info.split("/");
//		System.out.println(infoArr[0] + " " + infoArr[1] + " " + infoArr[2]);
		for(int i = 0; i < 3; i++) {
			arr[i] = infoArr[i].trim();
		}
		
		return arr;
	}

	// 숙박업소 데이터 불러오기
	public ArrayList<Accom> selectAllList(Connection conn) {
		ArrayList<Accom> accomList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectAllList"));
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				accomList.add(new Accom(
						rset.getInt("ACCOM_SQ"),
						rset.getString("ACCOM_NAME"),
						rset.getString("ACCOM_DESC"),
						rset.getInt("ACCOM_LON"),
						rset.getInt("ACCOM_LAT"),
						rset.getString("ACCOM_ZIP_CODE"),
						rset.getString("ACCOM_ADDR"),
						rset.getString("ACCOM_PHONE"),
						rset.getDate("ACCOM_REG_DT"),
						rset.getString("PUB_FAC_INFO"),
						rset.getString("IN_ROOM_FAC_INFO"),
						rset.getString("ETC_FAC_INFO"),
						rset.getInt("ACCOM_TYPE_NO"),
						rset.getInt("LOC_ID")
					));
			}
		} catch (SQLException e) {
			System.out.println("데이터 전체 조회 중 오류");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return accomList;
	}
}
