package com.LogVisualization.Splunk;

public class SplunkTest {
	public static LogVisualizationService s;

	public static void main(String[] args) throws Exception {
		s = new LogVisualizationService();
		s.Import("t2", "E:\\recreate");
		// wait for importing job done
		Thread.sleep(500);
		String result = s.getSearchResult("select req_time, count(clientip) from t2 group by req_time step=1h");
		System.out.println(result);
		//System.out.println(s.getSearchResult("search index=testIndex75"));
	}
}
