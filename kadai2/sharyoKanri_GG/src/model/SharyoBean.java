package model;

import java.io.Serializable;

public class SharyoBean implements Serializable{
	private String  shabanInfo;//車番　②
	private int  shaban;    //車番　②
	private String  shashu; //車種　⑤
	private int yearType; //年式　⑩
	private int carUserNumber;//社員番号
	private String carUser; //使用者　③
	private String  kudo;  //駆動
	private String shakenbi;  // 車検満了日　④
	private String tireType;  //使用中のタイヤ種
	private String snowTire ; //  スレッドタイヤ  (必要/不要)⑥
	private String  lastChkDay;  //最終点検日　⑦
	private String lastChkRslt ;//最終点検結果（OK/NG）⑧
	private String chkInfo;//(拡張用)
	private int  mileage;//走行距離　⑨


	public SharyoBean() {}

	public SharyoBean(String shabanInfo, int shaban, String tireType, int mileage) {
		this.shabanInfo = shabanInfo;
		this.shaban = shaban;
		this.tireType = tireType;
		this.mileage = mileage;
	}

	public SharyoBean(String shabanInfo, int shaban, String lastChkDay, String lastChkRslt, String chkInfo) {
		this.shabanInfo = shabanInfo;
		this.shaban = shaban;
		this.lastChkDay = lastChkDay;
		this.lastChkRslt = lastChkRslt;
		this.chkInfo = chkInfo;
	}

	public SharyoBean(String shabanInfo, int shaban, int carUserNumber, String carUser, String tireType,
			String snowTire, int mileage) {
		this.shabanInfo = shabanInfo;
		this.shaban = shaban;
		this.carUserNumber = carUserNumber;
		this.carUser = carUser;
		this.tireType = tireType;
		this.snowTire = snowTire;
		this.mileage = mileage;
	}


	// AdminCarDispSV,他２箇所用
	public SharyoBean(String shabanInfo, int shaban, String shashu, int yearType, String carUser, String kudo,
			String shakenbi, String tireType, int mileage) {
		this.shabanInfo = shabanInfo;
		this.shaban = shaban;
		this.shashu = shashu;
		this.yearType = yearType;
		this.carUser = carUser;
		this.kudo = kudo;
		this.shakenbi = shakenbi;
		this.tireType = tireType;
		this.mileage = mileage;
	}

	public SharyoBean(String  shabanInfo,int shaban,String  shashu,int yearType,int carUserNumber,
			String carUser,String  kudo,String shakenbi,String tireType,String snowTire ,
			String  lastChkDay,String lastChkRslt,String chkInfo,int  mileage) {

		this.shabanInfo=shabanInfo;
		this.shaban =shaban;
		this.shashu=shashu;
		this.yearType=yearType;
		this.carUserNumber=carUserNumber;
		this.carUser=carUser;
		this.kudo=kudo;
		this.shakenbi=shakenbi;
		this.tireType=tireType;
		this.snowTire=snowTire;
		this.lastChkDay=lastChkDay;
		this.lastChkRslt=lastChkRslt;
		this.chkInfo=chkInfo;
		this.mileage=mileage;


	}public int getCarUserNumber() {
		return carUserNumber;
	}public void setCarUserNumber(int carUserNumber) {
		this.carUserNumber = carUserNumber;
	}public String getChkInfo() {
		return chkInfo;
	}public void setChkInfo(String chkInfo) {
		this.chkInfo = chkInfo;
	}public String getShabanInfo() {
		return shabanInfo;
	}public void setShabanInfo(String shabanInfo) {
		this.shabanInfo = shabanInfo;
	}public int getShaban() {
		return shaban;
	}public void setShaban(int shaban) {
		this.shaban = shaban;
	}public String getShashu() {
		return shashu;
	}public void setShashu(String shashu) {
		this.shashu = shashu;
	}public int getYearType() {
		return yearType;
	}public void setYearType(int yearType) {
		this.yearType = yearType;
	}public String getCarUser() {
		return carUser;
	}public void setCarUser(String carUser) {
		this.carUser = carUser;
	}public String getKudo() {
		return kudo;
	}public void setKudo(String kudo) {
		this.kudo = kudo;
	}public String getShakenbi() {
		return shakenbi;
	}public void setShakenbi(String shakenbi) {
		this.shakenbi = shakenbi;
	}public String getTireType() {
		return tireType;
	}public void setTireType(String tireType) {
		this.tireType = tireType;
	}public String getSnowTire() {
		return snowTire;
	}public void setSnowTire(String snowTire) {
		this.snowTire = snowTire;
	}public String getLastChkDay() {
		return lastChkDay;
	}public void setLastChkDay(String lastChkDay) {
		this.lastChkDay = lastChkDay;
	}public String getLastChkRslt() {
		return lastChkRslt;
	}public void setLastChkRslt(String lastChkRslt) {
		this.lastChkRslt = lastChkRslt;
	}public String getChk_info() {
		return chkInfo;
	}public void setChk_info(String chk_info) {
		this.chkInfo = chk_info;
	}public int getMileage() {
		return mileage;
	}public void setMileage(int mileage) {
		this.mileage = mileage;
	}













}
