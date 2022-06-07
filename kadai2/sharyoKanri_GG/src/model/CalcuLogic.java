package model;

import java.util.Calendar;
import java.util.Date;

public class CalcuLogic {

	/**
	 * タイヤの製造年週からタイヤの使用年数を算出する
	 * 　作成者：安永
	 */
    public int usedTireYearCalcu(int year) {

		int week = year/100;
		int usedYear = year - week*100+2000;

		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);

		int cYear = c.get(Calendar.YEAR);
		int cMonth = c.get(Calendar.MONTH)+1;
		int cWeek = cMonth*4;

		usedYear = cYear - usedYear;

		if(cWeek-week<0) {
			usedYear = usedYear -1;
		}

		if(usedYear>0) {
			return usedYear;
		}else {
			return 0;
		}

    }

	/**
	 * 「admin_car_disp.jsp」「user_disp」での表示に必要な
	 * ノーマルタイヤの現時点での使用距離を算出する
	 * 　作成者：安永
	 */
    public int nTireKmNowTotal(SharyoBean sb, TireBean tb) {

    	int nTkmTotal;

    	if("不要".equals(sb.getSnowTire())) {
    		System.out.println("s不要");
    		nTkmTotal = sb.getMileage() - tb.getnTireKm();
    	}
		// ノーマル１度も使用無し且つスタッドレス使用中
		// または、ノーマルを初の使用時
    	else if((tb.getnTireKm() == 0) && tb.getsTireKm()==0
    			&& sb.getTireType().trim().equals("スタッドレス")
    			|| tb.getsTireKm() - tb.getnTireKm() + tb.getnTireTotalKm() < 0) {
    		nTkmTotal = 0;
    		System.out.println("★1");
    	}
    	// ノーマル使用中の時
    	else if(sb.getTireType().trim().equals("ノーマル")) {
    		//「現時点での使用距離」＝「総走行距離」－「使用開始時のメーター」＋「総使用距離（累計）」
    		nTkmTotal = sb.getMileage() - tb.getnTireKm() + tb.getnTireTotalKm();
    		System.out.println(sb.getMileage());
    		System.out.println(tb.getnTireKm());
    		System.out.println(tb.getnTireTotalKm());
    		System.out.println("★2："+sb.getMileage()+"-"+tb.getnTireKm()+"+"+tb.getnTireTotalKm());
    	}
    	// スタッドレス使用中の時
    	else {
    		//「現時点での使用距離」＝「使用終了時のメーター」－「使用開始時のメーター」＋「総使用距離（累計）」
    		nTkmTotal = tb.getsTireKm() - tb.getnTireKm() + tb.getnTireTotalKm();
    		System.out.println("★3");
    	}
    	return nTkmTotal;
    }


	/**
	 * 「admin_car_disp.jsp」「user_disp」での表示に必要な
	 * スタッドレスタイヤの現時点での使用距離を算出する
	 *　作成者：安永
	 */
    public int sTireKmNowTotal(SharyoBean sb, TireBean tb) {

    	System.out.println("@snow");

    	int sTkmTotal;

    	if("不要".equals(sb.getSnowTire())) {
    		System.out.println("s不要");
    		sTkmTotal = 0;
    	}
		// スタッドレス１度も使用無し且つノーマル使用中
		// または、スタッドレスを初の使用時
		if((tb.getsTireKm() == 0) && tb.getnTireKm()==0
				&& sb.getTireType().trim().equals("ノーマル")
				|| tb.getnTireKm() - tb.getsTireKm() + tb.getsTireTotalKm() < 0) {
			sTkmTotal = 0;
		}
		// スタッドレス使用中の時
		else if(sb.getTireType().trim().equals("スタッドレス")) {
			//「現時点での使用距離」＝「総走行距離」－「使用開始時のメーター」＋「総使用距離（累計）」
			sTkmTotal = sb.getMileage() - tb.getsTireKm() + tb.getsTireTotalKm();
			System.out.println("@2");
		}
		// ノーマル使用中の時
		else {
			//「現時点での使用距離」＝「使用終了時のメーター」－「使用開始時のメーター」＋「総使用距離（累計）」
			sTkmTotal = tb.getnTireKm() - tb.getsTireKm() + tb.getsTireTotalKm();
			System.out.println(tb.getnTireKm() +"-"+ tb.getsTireKm() +"+"+ tb.getsTireTotalKm());
		}

    	return sTkmTotal;
    }
}
