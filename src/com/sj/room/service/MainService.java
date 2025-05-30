package com.sj.room.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.sj.room.model.dao.AccomDAO;
import com.sj.room.model.dao.RoomDAO;
import com.sj.room.model.vo.Accom;
import com.sj.room.template.JDBCTemplate;

public class MainService {
	private Connection conn = null;
	
	// 숙박업소 데이터 업데이트
	public int updateAccom(Accom acc) {
		
		int result = 0;
		
		conn = JDBCTemplate.createConnection();
		
		result = new AccomDAO().updateAccom(conn, acc);
		
		if(result > 0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	// 전체 숙박목록 조회
	public ArrayList<Accom> selectAllList() {
		conn = JDBCTemplate.createConnection();
		
		ArrayList<Accom> accomList = new AccomDAO().selectAllList(conn);
		
		JDBCTemplate.close(conn);
		
		return accomList;
	}

	
	// 객실정보 저장
	public int insertRoom(int accomSq, char alpha) {
		System.out.println("객실정보 저장 : " + accomSq + ", " + alpha);
		int result = 0;
		
		conn = JDBCTemplate.createConnection();
		
		result = new RoomDAO().insertRoom(conn, accomSq, alpha);

		if(result > 0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
}
