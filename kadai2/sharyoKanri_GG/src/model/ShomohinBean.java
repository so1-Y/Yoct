package model;

import java.io.Serializable;

public class ShomohinBean implements Serializable {

	private String shabanInfo;
	private int shaban;
	private String oilDay;
	private int oilKm;
	private int oilNowKm;
	private String elementDay;
	private int elementKm;
	private int elementNowKm;
	private String batteryDay;
	private int batteryKm;
	private int batteryNowKm;

	//コンストラクタ
	public ShomohinBean() {}

	public ShomohinBean(String shabanInfo, int shaban, String oilDay, int oilKm, int oilNowKm, String elementDay,
			int elementKm, int elementNowKm, String batteryDay, int batteryKm, int batteryNowKm) {

		this.shabanInfo = shabanInfo;
		this.shaban = shaban;
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



	public ShomohinBean(String shabanInfo, int shaban, String oilDay, int oilKm, String elementDay, int elementKm, String batteryDay, int batteryKm) {
		this.shabanInfo = shabanInfo;
		this.shaban = shaban;
		this.oilDay = oilDay;
		this.oilKm = oilKm;
		this.elementDay = elementDay;
		this.elementKm = elementKm;
		this.batteryDay = batteryDay;
		this.batteryKm = batteryKm;
	}

	//ゲッターセッター
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
