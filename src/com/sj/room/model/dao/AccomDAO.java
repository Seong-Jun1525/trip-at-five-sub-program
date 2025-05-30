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
	
	// ���� ���� �о����
	public AccomDAO() {
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ���ھ��� �ü����� ������Ʈ
	public int updateAccom(Connection conn, Accom acc) {
		int result = 0;
		
		String[] facArr = setFacInfo(acc.getAccomName());
		int accomTypeNo = setAccomType(acc.getAccomName());
		
		try {
			conn.setAutoCommit(false);
			
			/**
			 * ���� null�̶�� �ؼ� ������ �����͸� �ִ� ���� ���� ����� �ƴϴ�.
			 * 
			 * �ֳ��ϸ� �� ������ ���� � �÷������� ������ ���� �ƴ� �� �ֱ� �����̴�.
			 * 
			 * ���� ���ٴ� ���� �ǹ��ϴ� ���� NULL�̴�. �׷��Ƿ� ���� ���� ��쿡�� NULL�� �δ� ���� �´�.
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

	// ���ھ��� ���� ����
	private int setAccomType(String accomName) {
		int type = 999;

		if(accomName.contains("����")) type = 21;
		else if(accomName.contains("ȣ��")) type = 22; 
		else if(accomName.contains("����Ʈ")) type = 23;
		else if(accomName.contains("���")) type = 24;
		else if(accomName.contains("ķ��")) type = 25;
		else if(accomName.contains("�ѿ�") || accomName.contains("�Խ�Ʈ�Ͽ콺") || accomName.contains("����")) type = 26;
		
		
		return type;
	}

	// ���ھ��� �ü� ���� ����
	private String[] setFacInfo(String accomName) {
		String[] result = new String[3]; 
		String info = "";
		
		if(accomName.contains("����")) info = "���������, ����ȭ���, ���� / �������ͳ�, ������, ������, ��ǿ�ǰ, �����ܼ�Ʈ, �̴Ϲ� / ��������, ���ǳ����, ���ǳ���, ����������";
		else if(accomName.contains("ȣ��")) info = "��쳪, ������, �������, ��Ʈ�Ͻ�, ���������, ���� / �������ͳ�, ������, ������, ��ǿ�ǰ, �̴Ϲ�, ����/��Ǯ, �����ܼ�Ʈ / ��������, ��������, ����������, �Ⱦ�����, ���λ繰��"; 
		else if(accomName.contains("���")) info = "�ٺ�ť, �����̽ü�, ��������� / ���ǳ����, �������ͳ�, ������, ������, ��ǿ�ǰ, �����ܼ�Ʈ / �ݷ��ߵ���, ��������, ķ�����̾�, ��������";
		else if(accomName.contains("ķ��")) info = "�ٺ�ť, �����̽ü�, ���������, ����ȭ���, ���� / �������ͳ�, �����ܼ�Ʈ, ��ǿ�ǰ, ������ / ķ�����̾�, �ݷ��ߵ���, ��������, ���ǳ����, ����������";
		else if(accomName.contains("����Ʈ")) info = "������, �ٺ�ť, �������, ��Ʈ�Ͻ�, �����̽ü� / ���ǽ���, �������ͳ�, ������, ��ǿ�ǰ, ������, �����ܼ�Ʈ, �̴Ϲ� / ��������, ��������, ���ǳ����, ķ�����̾�, �Ⱦ�����, ����������";
		else if(accomName.contains("�ѿ�") || accomName.contains("�Խ�Ʈ�Ͽ콺") || accomName.contains("����")) info = "���������, ����ȭ���, �ٺ�ť, ���� / �������ͳ�, ������, �����ܼ�Ʈ, ��ǿ�ǰ, ������ / ��������, ��������, ���λ繰��, ����������, ���ǳ����";
		
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

	// ���ھ��� ������ �ҷ�����
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
			System.out.println("������ ��ü ��ȸ �� ����");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return accomList;
	}
}
