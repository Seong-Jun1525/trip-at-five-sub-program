package com.sj.room.controller;

import java.util.ArrayList;

import com.sj.room.model.vo.Accom;
import com.sj.room.service.MainService;

public class MainController {
	private MainService mainService = new MainService();
	
	// ���� ��� ��ȸ �� ������Ʈ
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
				System.out.println(accomList.get(i).getAccomSq() + "�� ���ھ��� �����߻�!");
				return;
			}
		}
	}
	
	// ���� ���� ����
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
					System.out.println(accomList.get(i).getAccomSq() + "�� ���ھ��� �����߻�!");
					return;
				}
			}
		}
		
		System.out.println("===============���ǵ����� ���� ��===============");
	}
	
	private int forNumFun(String accomName) {
		int result = 1;
		
		if(accomName.contains("����")) result = 1;
		else if(accomName.contains("ȣ��") || accomName.contains("����Ʈ")) result = 3;
		else if(accomName.contains("���") || accomName.contains("ķ��") || accomName.contains("�ѿ�") || accomName.contains("�Խ�Ʈ�Ͽ콺") || accomName.contains("����")) result = 2;
		
		return result;
	}
}
