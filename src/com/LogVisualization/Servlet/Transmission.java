package com.LogVisualization.Servlet;

import com.LogVisualization.Splunk.LogVisualizationService;

public class Transmission {
	public String[] sendKey(String[] key) {
		return key; //For Debug
	}
	public int[] sendValue(int[] value) {
		return value; //For Debug
	}
	public String sendCommand(String command){
		LogVisualizationService lvs = new LogVisualizationService();
		try {
			//String resultStr = lvs.getSearchResult(command);
			String resultStr = "OK, this message came from a Java Function.";
			return resultStr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
