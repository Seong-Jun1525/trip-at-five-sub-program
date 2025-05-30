package com.sj.room.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.sj.room.model.dao.AccomDAO;
import com.sj.room.model.dao.RoomDAO;
import com.sj.room.model.vo.Accom;
import com.sj.room.template.JDBCTemplate;

public class MainService {
	private Connection conn = null;
	
	// ���ھ��� ������ ������Ʈ
	public int updateAccom(Accom acc) {
		
		int result = 0;
		
		conn = JDBCTemplate.createConnection();
		
		result = new AccomDAO().updateAccom(conn, acc);
		
		if(result > 0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	// ��ü ���ڸ�� ��ȸ
	public ArrayList<Accom> selectAllList() {
		conn = JDBCTemplate.createConnection();
		
		ArrayList<Accom> accomList = new AccomDAO().selectAllList(conn);
		
		JDBCTemplate.close(conn);
		
		return accomList;
	}

	
	// �������� ����
	public int insertRoom(int accomSq, char alpha) {
		System.out.println("�������� ���� : " + accomSq + ", " + alpha);
		int result = 0;
		
		conn = JDBCTemplate.createConnection();
		
		result = new RoomDAO().insertRoom(conn, accomSq, alpha);

		if(result > 0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
}
