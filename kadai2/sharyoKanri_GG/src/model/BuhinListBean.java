package model;

import java.io.Serializable;

public class BuhinListBean  implements Serializable{

	private String  shabanInfo;//車番　②
	private int  shaban;    //車番　②
	private String carUser; //使用者　③
	private int  mileage;//走行距離　⑨
	private int nTireYear;
	private String nTireDay;
	private int nTireTotalKm;
	private int nTireNowKm;		// 追加
	private int sTireYear;
	private String sTireDay;
	private int sTireTotalKm;
	private int sTireNowKm;		// 追加
	private String oilDay;
	private int oilKm;
	private int oilNowKm;		//　追加
	private String elementDay;
	private int elementKm;
	private int elementNowKm;		//追加
	private String batteryDay;
	private int batteryKm;
	private int batteryNowKm;		//追加


	public BuhinListBean() {}

	public BuhinListBean(String shabanInfo, int shaban, String carUser, int mileage, int nTireYear, String nTireDay,
			int nTireTotalKm, int nTireNowKm, int sTireYear, String sTireDay, int sTireTotalKm, int sTireNowKm,
			String oilDay, int oilKm, int oilNowKm, String elementDay, int elementKm, int elementNowKm,
			String batteryDay, int batteryKm, int batteryNowKm) {

		this.shabanInfo = shabanInfo;
		this.shaban = shaban;
		this.carUser = carUser;
		this.mileage = mileage;
		this.nTireYear = nTireYear;
		this.nTireDay = nTireDay;
		this.nTireTotalKm = nTireTotalKm;
		this.nTireNowKm = nTireNowKm;
		this.sTireYear = sTireYear;
		this.sTireDay = sTireDay;
		this.sTireTotalKm = sTireTotalKm;
		this.sTireNowKm = sTireNowKm;
		this.oilDay = oilDay;
		this.oilKm = oilKm;
		this.oilNowKm = oilNowKm;
		this.elementDay = elementDay;
		this.elementKm = elementKm;
		this.elementNowKm = elementNowKm;
		this.batteryDay = batteryDay;
		this.batteryKm = batteryKm;
		this.batteryNowKm = batteryNowKm;
	}



	public BuhinListBean(String shabanInfo, int shaban, String carUser, int mileage, int nTireYear, String nTireDay,
			int nTireTotalKm, int sTireYear, String sTireDay, int sTireTotalKm, String oilDay, int oilKm,
			String elementDay, int elementKm, String batteryDay, int batteryKm) {
		super();
		this.shabanInfo = shabanInfo;
		this.shaban = shaban;
		this.carUser = carUser;
		this.mileage = mileage;
		this.nTireYear = nTireYear;
		this.nTireDay = nTireDay;
		this.nTireTotalKm = nTireTotalKm;
		this.sTireYear = sTireYear;
		this.sTireDay = sTireDay;
		this.sTireTotalKm = sTireTotalKm;
		this.oilDay = oilDay;
		this.oilKm = oilKm;
		this.elementDay = elementDay;
		this.elementKm = elementKm;
		this.batteryDay = batteryDay;
		this.batteryKm = batteryKm;
	}

	public String getShabanInfo() {
		return shabanInfo;
	}

	public void setShabanInfo(String shabanInfo) {
		this.shabanInfo = shabanInfo;
	}

	public int getShaban() {
		return shaban;
	}

	public void setShaban(int shaban) {
		this.shaban = shaban;
	}

	public String getCarUser() {
		return carUser;
	}

	public void setCarUser(String carUser) {
		this.carUser = carUser;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public int getnTireYear() {
		return nTireYear;
	}

	public void setnTireYear(int nTireYear) {
		this.nTireYear = nTireYear;
	}

	public String getnTireDay() {
		return nTireDay;
	}

	public void setnTireDay(String nTireDay) {
		this.nTireDay = nTireDay;
	}

	public int getnTireTotalKm() {
		return nTireTotalKm;
	}

	public void setnTireTotalKm(int nTireTotalKm) {
		this.nTireTotalKm = nTireTotalKm;
	}

	public int getsTireYear() {
		return sTireYear;
	}

	public void setsTireYear(int sTireYear) {
		this.sTireYear = sTireYear;
	}

	public String getsTireDay() {
		return sTireDay;
	}

	public void setsTireDay(String sTireDay) {
		this.sTireDay = sTireDay;
	}

	public int getsTireTotalKm() {
		return sTireTotalKm;
	}

	public void setsTireTotalKm(int sTireTotalKm) {
		this.sTireTotalKm = sTireTotalKm;
	}

	public String getOilDay() {
		return oilDay;
	}

	public void setOilDay(String oilDay) {
		this.oilDay = oilDay;
	}

	public int getOilKm() {
		return oilKm;
	}

	public void setOilKm(int oilKm) {
		this.oilKm = oilKm;
	}

	public String getElementDay() {
		return elementDay;
	}

	public void setElementDay(String elementDay) {
		this.elementDay = elementDay;
	}

	public int getElementKm() {
		return elementKm;
	}

	public void setElementKm(int elementKm) {
		this.elementKm = elementKm;
	}

	public String getBatteryDay() {
		return batteryDay;
	}

	public void setBatteryDay(String batteryDay) {
		this.batteryDay = batteryDay;
	}

	public int getBatteryKm() {
		return batteryKm;
	}

	public void setBatteryKm(int batteryKm) {
		this.batteryKm = batteryKm;
	}

	public int getnTireNowKm() {
		return nTireNowKm;
	}

	public void setnTireNowKm(int nTireNowKm) {
		this.nTireNowKm = nTireNowKm;
	}

	public int getsTireNowKm() {
		return sTireNowKm;
	}

	public void setsTireNowKm(int sTireNowKm) {
		this.sTireNowKm = sTireNowKm;
	}

	public int getOilNowKm() {
		return oilNowKm;
	}

	public void setOilNowKm(int oilNowKm) {
		this.oilNowKm = oilNowKm;
	}

	public int getElementNowKm() {
		return elementNowKm;
	}

	public void setElementNowKm(int elementNowKm) {
		this.elementNowKm = elementNowKm;
	}

	public int getBatteryNowKm() {
		return batteryNowKm;
	}

	public void setBatteryNowKm(int batteryNowKm) {
		this.batteryNowKm = batteryNowKm;
	}



}
