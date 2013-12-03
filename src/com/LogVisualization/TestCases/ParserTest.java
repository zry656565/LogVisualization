package com.LogVisualization.TestCases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.LogVisualization.Parser.ParseException;
import com.LogVisualization.Splunk.LogVisualizationService;

public class ParserTest {
	public static void main(String args[]) throws Exception
	{
		LogVisualizationService service = new LogVisualizationService();
		//service.Import("test","Users/fengyunping/Documents/ASE/Our project/Data/recreate_ip.out");
		String result = service.getSearchResult("select req_time, count(clientip) from test__index group by req_time step=1h");
		System.out.println(result);
		//service.CleanIndex("main");
		/*String all = "search index=main m=2|FIELDS m,n step=1.2h";
		String[] result;
		result = service.getCommandAndStep(all);
		for(int i = 0; i < result.length; ++i){
			System.out.println(result[i]);
		}
		
		SimpleDateFormat myFmt1=new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z");
		TimeZone timezone = TimeZone.getTimeZone("GMT-0:00");
		myFmt1.setTimeZone(timezone);
		Date date = new Date();
		date = myFmt1.parse("30/Apr/1998:22:00:49 +0000");
		Date date2 = myFmt1.parse("30/Apr/1998:22:10:49 +0000");
		System.out.println(date.before(date2));
		String afterFmt = myFmt1.format(date2);
		System.out.println(afterFmt);
		SimpleDateFormat fmt4min = new SimpleDateFormat("dd/MMM/yyyy:HH:00:00 Z");
		fmt4min.setTimeZone(timezone);
		System.out.println(fmt4min.format(date));*/
		
		
 		//service.getSearchResult("select req_time, count(clientip) from main group by req_time step=1h");
		
	}
}
