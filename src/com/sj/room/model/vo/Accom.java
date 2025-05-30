package com.sj.room.model.vo;

import java.sql.Date;

public class Accom {
	private int accomSq;
	private String accomName;
	private String accomDesc;
	private int accomLon;
	private int accomLat;
	private String accomZipCode;
	private String accomAddr;
	private String accomPhone;
	private Date accomRegDt;
	private String pubFacInfo;
	private String inRoomFacInfo;
	private String etcFacInfo;
	private int accomTypeNo;
	private int locId;
	
	public Accom() {}

	public Accom(int accomSq, String accomName, String accomDesc, int accomLon, int accomLat, String accomZipCode,
			String accomAddr, String accomPhone, Date accomRegDt, String pubFacInfo, String inRoomFacInfo,
			String etcFacInfo, int accomTypeNo, int locId) {
		super();
		this.accomSq = accomSq;
		this.accomName = accomName;
		this.accomDesc = accomDesc;
		this.accomLon = accomLon;
		this.accomLat = accomLat;
		this.accomZipCode = accomZipCode;
		this.accomAddr = accomAddr;
		this.accomPhone = accomPhone;
		this.accomRegDt = accomRegDt;
		this.pubFacInfo = pubFacInfo;
		this.inRoomFacInfo = inRoomFacInfo;
		this.etcFacInfo = etcFacInfo;
		this.accomTypeNo = accomTypeNo;
		this.locId = locId;
	}

	public int getAccomSq() {
		return accomSq;
	}

	public void setAccomSq(int accomSq) {
		this.accomSq = accomSq;
	}

	public String getAccomName() {
		return accomName;
	}

	public void setAccomName(String accomName) {
		this.accomName = accomName;
	}

	public String getAccomDesc() {
		return accomDesc;
	}

	public void setAccomDesc(String accomDesc) {
		this.accomDesc = accomDesc;
	}

	public int getAccomLon() {
		return accomLon;
	}

	public void setAccomLon(int accomLon) {
		this.accomLon = accomLon;
	}

	public int getAccomLat() {
		return accomLat;
	}

	public void setAccomLat(int accomLat) {
		this.accomLat = accomLat;
	}

	public String getAccomZipCode() {
		return accomZipCode;
	}

	public void setAccomZipCode(String accomZipCode) {
		this.accomZipCode = accomZipCode;
	}

	public String getAccomAddr() {
		return accomAddr;
	}

	public void setAccomAddr(String accomAddr) {
		this.accomAddr = accomAddr;
	}

	public String getAccomPhone() {
		return accomPhone;
	}

	public void setAccomPhone(String accomPhone) {
		this.accomPhone = accomPhone;
	}

	public Date getAccomRegDt() {
		return accomRegDt;
	}

	public void setAccomRegDt(Date accomRegDt) {
		this.accomRegDt = accomRegDt;
	}

	public String getPubFacInfo() {
		return pubFacInfo;
	}

	public void setPubFacInfo(String pubFacInfo) {
		this.pubFacInfo = pubFacInfo;
	}

	public String getInRoomFacInfo() {
		return inRoomFacInfo;
	}

	public void setInRoomFacInfo(String inRoomFacInfo) {
		this.inRoomFacInfo = inRoomFacInfo;
	}

	public String getEtcFacInfo() {
		return etcFacInfo;
	}

	public void setEtcFacInfo(String etcFacInfo) {
		this.etcFacInfo = etcFacInfo;
	}

	public int getAccomTypeNo() {
		return accomTypeNo;
	}

	public void setAccomTypeNo(int accomTypeNo) {
		this.accomTypeNo = accomTypeNo;
	}

	public int getLocId() {
		return locId;
	}

	public void setLocId(int locId) {
		this.locId = locId;
	}

	@Override
	public String toString() {
		return "Accom [accomSq=" + accomSq + ", accomName=" + accomName + ", accomDesc=" + accomDesc + ", accomLon="
				+ accomLon + ", accomLat=" + accomLat + ", accomZipCode=" + accomZipCode + ", accomAddr=" + accomAddr
				+ ", accomPhone=" + accomPhone + ", accomRegDt=" + accomRegDt + ", pubFacInfo=" + pubFacInfo
				+ ", inRoomFacInfo=" + inRoomFacInfo + ", etcFacInfo=" + etcFacInfo + ", accomTypeNo=" + accomTypeNo
				+ ", locId=" + locId + "]";
	}

}
