package com.LogVisualization.Splunk;

public class SplunkTest {

	public static LogVisualizationService s;

	public static void main(String[] args) throws Exception {
		s = new LogVisualizationService();
		s.Import("testindex", "E:\\recreate_ip.out");
		// wait for importing job done
		Thread.sleep(500);
		System.out.println(s.getSearchResult("search index=testIndex"));
	}
}
