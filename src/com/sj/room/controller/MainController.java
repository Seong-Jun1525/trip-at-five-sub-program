package com.sj.room.controller;

import java.util.ArrayList;

import com.sj.room.model.vo.Accom;
import com.sj.room.service.MainService;

public class MainController {
	private MainService mainService = new MainService();
	
	// 숙박 목록 조회 후 업데이트
	public void updateAccom() {
		ArrayList<Accom> accomList = mainService.selectAllList();
		int result = 0;
		
		for(int i = 0; i < accomList.size(); i++) {
			System.out.println(i + " : " + accomList.get(i));
			
			result = mainService.updateAccom(new Accom(
						accomList.get(i).getAccomSq(),
						accomList.get(i).getAccomName(),
						accomList.get(i).getAccomDesc(),
						accomList.get(i).getAccomLon(),
						accomList.get(i).getAccomLat(),
						accomList.get(i).getAccomZipCode(),
						accomList.get(i).getAccomAddr(),
						accomList.get(i).getAccomPhone(),
						accomList.get(i).getAccomRegDt(),
						accomList.get(i).getPubFacInfo(),
						accomList.get(i).getInRoomFacInfo(),
						accomList.get(i).getEtcFacInfo(),
						accomList.get(i).getAccomTypeNo(),
						accomList.get(i).getLocId()
					));
			
			if(result == 0) {
				System.out.println(accomList.get(i).getAccomSq() + "번 숙박업소 오류발생!");
				return;
			}
		}
	}
	
	// 객실 정보 저장
	public void insertRoom() {
		ArrayList<Accom> accomList = mainService.selectAllList();
		int result = 0;
		int forNum = 1;
		for(int i = 0; i < accomList.size(); i++) {
			char alpha = 'A';
			System.out.println(i + " : " + accomList.get(i));

			forNum = forNumFun(accomList.get(i).getAccomName());
			
			for(int j = 0; j < forNum; j++) {
				System.out.println(accomList.get(i).getAccomSq());
				result = mainService.insertRoom(accomList.get(i).getAccomSq(), (char)(alpha + j));
				
				if(result == 0) {
					System.out.println(accomList.get(i).getAccomSq() + "번 숙박업소 오류발생!");
					return;
				}
			}
		}
		
		System.out.println("===============객실데이터 저장 끝===============");
	}
	
	private int forNumFun(String accomName) {
		int result = 1;
		
		if(accomName.contains("모텔")) result = 1;
		else if(accomName.contains("호텔") || accomName.contains("리조트")) result = 3;
		else if(accomName.contains("펜션") || accomName.contains("캠핑") || accomName.contains("한옥") || accomName.contains("게스트하우스") || accomName.contains("게하")) result = 2;
		
		return result;
	}
}
