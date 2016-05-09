package com.work.webmagic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {
	public static void main(String[] args) {
		String date = "2015-05-04";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date pram = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(pram);
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-1);
			System.out.println(sdf.format(calendar.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
