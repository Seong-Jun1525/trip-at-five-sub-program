package com.sj.room.model.vo;

import java.sql.Date;

public class Room {
	private int roomSq;
	private String roomName;
	private int roomPrice;
	private String roomChkIn;
	private String roomChkOut;
	private int roomStdPpl;
	private int roomMaxPpl;
	private int roomCnt;
	private Date roomRegDt;
	private int accomNo;

	public Room() { }

	public Room(int roomSq, String roomName, int roomPrice, String roomChkIn, String roomChkOut, int roomStdPpl,
			int roomMaxPpl, int roomCnt, Date roomRegDt, int accomNo) {
		super();
		this.roomSq = roomSq;
		this.roomName = roomName;
		this.roomPrice = roomPrice;
		this.roomChkIn = roomChkIn;
		this.roomChkOut = roomChkOut;
		this.roomStdPpl = roomStdPpl;
		this.roomMaxPpl = roomMaxPpl;
		this.roomCnt = roomCnt;
		this.roomRegDt = roomRegDt;
		this.accomNo = accomNo;
	}

	public int getRoomSq() {
		return roomSq;
	}

	public void setRoomSq(int roomSq) {
		this.roomSq = roomSq;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(int roomPrice) {
		this.roomPrice = roomPrice;
	}

	public String getRoomChkIn() {
		return roomChkIn;
	}

	public void setRoomChkIn(String roomChkIn) {
		this.roomChkIn = roomChkIn;
	}

	public String getRoomChkOut() {
		return roomChkOut;
	}

	public void setRoomChkOut(String roomChkOut) {
		this.roomChkOut = roomChkOut;
	}

	public int getRoomStdPpl() {
		return roomStdPpl;
	}

	public void setRoomStdPpl(int roomStdPpl) {
		this.roomStdPpl = roomStdPpl;
	}

	public int getRoomMaxPpl() {
		return roomMaxPpl;
	}

	public void setRoomMaxPpl(int roomMaxPpl) {
		this.roomMaxPpl = roomMaxPpl;
	}

	public int getRoomCnt() {
		return roomCnt;
	}

	public void setRoomCnt(int roomCnt) {
		this.roomCnt = roomCnt;
	}

	public Date getRoomRegDt() {
		return roomRegDt;
	}

	public void setRoomRegDt(Date roomRegDt) {
		this.roomRegDt = roomRegDt;
	}

	public int getAccomNo() {
		return accomNo;
	}

	public void setAccomNo(int accomNo) {
		this.accomNo = accomNo;
	}

	@Override
	public String toString() {
		return "Room [roomSq=" + roomSq + ", roomName=" + roomName + ", roomPrice=" + roomPrice + ", roomChkIn="
				+ roomChkIn + ", roomChkOut=" + roomChkOut + ", roomStdPpl=" + roomStdPpl + ", roomMaxPpl=" + roomMaxPpl
				+ ", roomCnt=" + roomCnt + ", roomRegDt=" + roomRegDt + ", accomNo=" + accomNo + "]";
	}

}
