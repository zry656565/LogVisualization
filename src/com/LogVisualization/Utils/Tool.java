package com.LogVisualization.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Tool {
	public static void main(String args[]) throws Exception
	{
		System.out.println(Tool.nextLabel("30/Apr/1998:21:00:00 +0000", "1h"));
		System.out.println(Tool.nextLabel("30/Apr/1998:21:00:00 +0000", "1min"));
		System.out.println(Tool.nextLabel("30/Apr/1998:21:00:00 +0000", "1sec"));
		System.out.println(Tool.nextLabel("2.213", "1.2"));
		System.out.println(Tool.nextLabel("2", "1"));
	}
	
	public static String nextLabel(String label, String step){
		label = label.trim();
		String nextLabel = "";
		int unitIndex = 0;
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z",Locale.US);
		TimeZone timezone = TimeZone.getTimeZone("GMT-0:00");
		if (step.indexOf('h') != -1){
			SimpleDateFormat reqfmt = new SimpleDateFormat("dd/MMM/yyyy:HH:00:00 Z",Locale.US);
			reqfmt.setTimeZone(timezone);
			try {
				Date d = reqfmt.parse(label);
				Calendar c = Calendar.getInstance();
				c.setTime(d);
				c.add(Calendar.HOUR_OF_DAY, 1);
				d = c.getTime();
				nextLabel = reqfmt.format(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		else if (step.indexOf("min") != -1){
			SimpleDateFormat reqfmt = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:00 Z",Locale.US);
			reqfmt.setTimeZone(timezone);
			try {
				Date d = reqfmt.parse(label);
				Calendar c = Calendar.getInstance();
				c.setTime(d);
				c.add(Calendar.MINUTE, 1);
				d = c.getTime();
				nextLabel = reqfmt.format(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		else if (step.indexOf("sec") != -1){
			SimpleDateFormat reqfmt = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z",Locale.US);
			reqfmt.setTimeZone(timezone);
			try {
				Date d = reqfmt.parse(label);
				Calendar c = Calendar.getInstance();
				c.setTime(d);
				c.add(Calendar.SECOND, 1);
				d = c.getTime();
				nextLabel = reqfmt.format(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		else {
			if(isInteger(label)){
				Integer i  = Integer.parseInt(label);
				i += Integer.parseInt(step);
				nextLabel = i.toString();
			}
			else if(isDouble(label)){
				Double d = Double.parseDouble(label);
				d += Double.parseDouble(step);
				nextLabel = d.toString();
			}
			
		}
		return nextLabel;
	}
	private static boolean isDouble(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	}  
	private static boolean isInteger(String s) {  
	    return s.matches("[-+]?\\d*\\.?");  
	}
}
