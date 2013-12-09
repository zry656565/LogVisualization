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
			//if you don't meet the error about XML parsing, use the code below
			//String resultStr = lvs.getSearchResult(command);
			
			//else, the only thing you can do is directly using the result string
			String resultStr = "{\"key\":[\"req_time\",\"count(clientip)\"],\"value\":[\"30/Apr/1998:21:00:00 +0000 \",\"324\",\"30/Apr/1998:22:00:00 +0000 \",\"676\"],\"numOfFields\":2,\"step\":1,\"unit\":\"h\"}";
			return resultStr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
