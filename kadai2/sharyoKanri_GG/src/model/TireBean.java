package model;

import java.io.Serializable;

public class TireBean implements Serializable{
	private String shabanInfo;
	private int shaban;
	private int nTireYear;
	private String nTireDay;
	private int nTireKm;
	private int nTireTotalKm;
	private int nTireTotalNowKm;
	private int sTireYear;
	private String sTireDay;
	private int sTireKm;
	private int sTireTotalKm;
	private int sTireTotalNowKm;

	//コンストラクタ
	public TireBean() {}

	public TireBean(String shabanInfo, int shaban, int nTireYear, String nTireDay, int nTireKm, int nTireTotalKm,
			int nTireTotalNowKm, int sTireYear, String sTireDay, int sTireKm, int sTireTotalKm, int sTireTotalNowKm) {
		super();
		this.shabanInfo = shabanInfo;
		this.shaban = shaban;
		this.nTireYear = nTireYear;
		this.nTireDay = nTireDay;
		this.nTireKm = nTireKm;
		this.nTireTotalKm = nTireTotalKm;
		this.nTireTotalNowKm = nTireTotalNowKm;
		this.sTireYear = sTireYear;
		this.sTireDay = sTireDay;
		this.sTireKm = sTireKm;
		this.sTireTotalKm = sTireTotalKm;
		this.sTireTotalNowKm = sTireTotalNowKm;
	}



	public TireBean(String shabanInfo, int shaban, int nTireYear, String nTireDay, int nTireKm, int nTireTotalKm,
			                                    int sTireYear, String sTireDay, int sTireKm, int sTireTotalKm ) {
		this.shabanInfo = shabanInfo;
		this.shaban = shaban;
		this.nTireYear = nTireYear;
		this.nTireDay = nTireDay;
		this.nTireKm = nTireKm;
		this.nTireTotalKm = nTireTotalKm;
		this.sTireYear = sTireYear;
		this.sTireDay = sTireDay;
		this.sTireKm = sTireKm;
		this.sTireTotalKm = sTireTotalKm;
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
	public int getnTireKm() {
		return nTireKm;
	}
	public void setnTireKm(int nTireKm) {
		this.nTireKm = nTireKm;
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
	public int getsTireKm() {
		return sTireKm;
	}
	public void setsTireKm(int sTireKm) {
		this.sTireKm = sTireKm;
	}
	public int getsTireTotalKm() {
		return sTireTotalKm;
	}
	public void setsTireTotalKm(int sTireTotalKm) {
		this.sTireTotalKm = sTireTotalKm;
	}

	public int getnTireTotalNowKm() {
		return nTireTotalNowKm;
	}

	public void setnTireTotalNowKm(int nTireTotalNowKm) {
		this.nTireTotalNowKm = nTireTotalNowKm;
	}

	public int getsTireTotalNowKm() {
		return sTireTotalNowKm;
	}

	public void setsTireTotalNowKm(int sTireTotalNowKm) {
		this.sTireTotalNowKm = sTireTotalNowKm;
	}



}
