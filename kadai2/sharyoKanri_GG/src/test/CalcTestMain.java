package test;

import java.util.Calendar;
import java.util.Date;

public class CalcTestMain {

	public static void main(String[] args) {

		int nTireYear = 4121;
		//年数の取得 4121

		int week = nTireYear/100;
		int year = nTireYear - week*100+2000;

		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);

		int cYear = c.get(Calendar.YEAR);
		int cMonth = c.get(Calendar.MONTH)+1;
		int cWeek = cMonth*4;

		System.out.println(cYear);
		System.out.println(cMonth);

		year = cYear - year;

		if(cWeek-week<0) {
			year = year -1;
		}


	}

}
