package com.LogVisualization.Splunk;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import com.LogVisualization.Bean.GraphFields;
import com.LogVisualization.Parser.ParseException;
import com.LogVisualization.Parser.SQLParser;
import com.LogVisualization.Utils.SV;
import com.google.gson.Gson;
import com.splunk.Entity;
import com.splunk.Index;
import com.splunk.IndexCollection;
import com.splunk.Job;
import com.splunk.JobResultsArgs;
import com.splunk.ResultsReaderXml;
import com.splunk.Service;
import com.splunk.ServiceArgs;

public class LogVisualizationService {
	public Service service;

	public LogVisualizationService() {
		// Create a map of arguments and add login parameters
		ServiceArgs loginArgs = new ServiceArgs();
		loginArgs.setUsername(SV.SPLUNK_USERNAME);
		loginArgs.setPassword(SV.SPLUNK_PASSWORD);
		loginArgs.setHost("localhost");
		loginArgs.setPort(8089);

		// Create a Service instance and log in with the argument map
		service = Service.connect(loginArgs);
	}

	public int Import(String iName, String filePath) {
		// Retrieve the collection of indexes, sorted by number of events
		IndexCollection myIndexes = service.getIndexes();

		// Iterate the collection of indexs and find if iName is included
		// If iName doesn't exist, then create it.
		boolean index_exist = false;
		for (Index entity : myIndexes.values()) {
			//System.out.println(entity.getName()); //DEBUG
			if (entity.getName().equals(iName)) {
				index_exist = true;
				break;
			}
		}
		if (!index_exist) {
			myIndexes.create(iName);
		}

		// Okay, link the monitor to the index, and return with 0;
		service.getIndexes().get(iName).upload(filePath);
		return 0;
	}

	public void CleanIndex(String iName) {
		// Retrieve the index that was created earlier
		Index myIndex = service.getIndexes().get(iName);

		// Clean events from the index, printing the before-and-after size
		System.out.println("Current DB size:     "
				+ myIndex.getCurrentDBSizeMB() + "MB");
		myIndex.clean(180);
		System.out.println("Current DB size:     "
				+ myIndex.getCurrentDBSizeMB() + "MB");
	}

	public String Translate(String originalCommand) {
		// //Here translate our own command to splunk command.
		InputStream input = new ByteArrayInputStream(originalCommand.getBytes());
		SQLParser parser = new SQLParser(input);
		String result = "helloworld";
		try {
			result = parser.parseOneLine();
			System.out.println(result);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public String[] getCommandAndStep(String str) throws Exception {
		String[] result = new String[2];
		int stepIndex = str.indexOf("step");
		if (stepIndex != -1){
			String former = str.substring(0,(stepIndex-1));
			String latter = str.substring(stepIndex);
			result[0] = former;
			result[1] = latter;
		}
		else {
			result[0] = str;
			result[1] = "";
		}
		return result;
	}
	public String[] getFields(String str) throws Exception{
		String[] result = {};
		ArrayList<String> arr = new ArrayList<String>();
		String FIELDS = "FIELDS";
		int begin = str.indexOf(FIELDS);
		str = str.substring(begin+FIELDS.length()+1);
		while(str.indexOf(',')!=-1){
			if (str.indexOf(',') != -1){
				arr.add( str.substring(0,str.indexOf(',')) );
				str = str.substring(str.indexOf(',')+1);
			}
		}
		arr.add(str);
		result = arr.toArray(result);
		return result;
	}
	
	public String[] getStep(String str) throws Exception{
		String[] result = new String[2];
		int eqlIndex = str.indexOf('=');
		int h = str.indexOf('h');
		int min = str.indexOf("min");
		int sec = str.indexOf("sec");
		String unit = "";
		int unitIndex = 0;
		if (h != -1){
			unit = "h";
			unitIndex = h;
		}
		else if (min != -1){
			unit = "min";
			unitIndex = min;
		}
		else if (sec != -1){
			unit ="sec";
			unitIndex = sec;
		}
		else {
			unit = "";
			unitIndex = -1;
		}
		String stepVal = str.substring(eqlIndex+1,unitIndex);
		result[0]=stepVal;
		result[1]=unit;
		return result;
	}

	public String getSearchResult(String originalCommand) throws Exception {
		String command = Translate(originalCommand);
		String[] splitCommd = getCommandAndStep(command);
		String[] fields = getFields(splitCommd[0]);
		String[] step = getStep(splitCommd[1]);

		// Create a simple search job
		String mySearch = splitCommd[0];
		Job job = service.getJobs().create(mySearch);
		// Wait for the job to finish
		while (!job.isDone()) {
			try
	        {
	            Thread.sleep(2000);
	        }
	        catch (InterruptedException e){}
	        job.refresh();
		}
		
		Entity restApi = service.getConfs().get("limits").get("restapi");
        int maxresults = Integer.parseInt((String)restApi.get("maxresultrows"));
        int eventCount = job.getEventCount();
        int getOffset = 0;
        ArrayList<ArrayList<String>> tempstore = new ArrayList<ArrayList<String>>();
		
		GraphFields graph = new GraphFields();
		graph.setKey(fields);
		graph.setStep(Integer.valueOf(step[0]));
		graph.setUnit(step[1]);
		graph.setNumOfFields(fields.length);
		
		
		while (getOffset < eventCount) {
			JobResultsArgs resultArgs = new JobResultsArgs();
	        resultArgs.setOutputMode(JobResultsArgs.OutputMode.XML);
	        resultArgs.setFieldList(fields);
	        resultArgs.setCount(maxresults);
	        resultArgs.setOffset(getOffset);

			// Display results
	        InputStream results = job.getResults(resultArgs);
	        ResultsReaderXml resultsReader = new ResultsReaderXml(results);
	        tempstore.addAll(getLogVal(resultsReader,fields));
	        System.out.println(tempstore.size());
	        getOffset += maxresults;
		}
       
        
        int indexOfTime = -1;
        for(int i = 0 ; i < fields.length; ++i){
        	if (fields[i].equals("req_time")){
        		indexOfTime = i;
        		break;
        	}
        }
        String resultStr = "";
        
        if (indexOfTime == -1||fields[1].equals("sec")){
        	resultStr = tempStoreToResult(graph,tempstore);
        }
        else {
        	System.out.println(tempstore.size());
        	ArrayList<String> strdatestore= tempstore.get(indexOfTime);
        	ArrayList<Date> datestore = new ArrayList<Date>();
        	for(int i = 0; i < strdatestore.size(); ++i){
        		datestore.add(date_time(strdatestore.get(i)));
        	}
        	if(datestore.size()!=0){
            	Collections.sort(datestore);
            	int[] hour = new int[2];
            	int[] min = new int[2];
            	int[] sec = new int[2];
            	Calendar calendarmin  = Calendar.getInstance();
            	//get the hour,minute and sec of the min date
            	Date mindate = datestore.get(0);
            	calendarmin.setTime(mindate);
            	Calendar calendarmax  = Calendar.getInstance();
            	Date maxdate = datestore.get(datestore.size()-1);
            	calendarmax.setTime(maxdate);
            	
            	long difference=calendarmax.getTimeInMillis()-calendarmin.getTimeInMillis(); 
            	String[] cmmd=null;
            	String ind = findFromIndex(splitCommd[0]);
            	if (step[1].equals("h")){
            		cmmd = produceReqTimeCommd(ind,mindate,maxdate,"h");
            	}
            	else if (step[1].equals("min")){
            		cmmd = produceReqTimeCommd(ind,mindate,maxdate,"min");
            	}
            	for(int i = 0; i < tempstore.size(); ++i){
            		tempstore.get(i).clear();
            	}
            	for(int i = 0 ; i < cmmd.length; ++i){
            		getOffset = 0;
            		job = service.getJobs().create(cmmd[i]);
            		while (!job.isDone()) {
            			try
            	        {
            	            Thread.sleep(2000);
            	        }
            	        catch (InterruptedException e){}
            	        job.refresh();
            		}
            		eventCount = job.getEventCount();           		
            		//ToDo 3-D鎬庝箞鍔�!!!!!!!
            		if(indexOfTime == 0){
            			tempstore.get(0).add(smallerTime(cmmd[i]));
            			tempstore.get(1).add(String.valueOf(eventCount));
            		}
            		else{
            			tempstore.get(1).add(smallerTime(cmmd[i]));
            			tempstore.get(2).add(String.valueOf(eventCount));
            		}
            	}
            	resultStr = tempStoreToResult(graph,tempstore);
            	
            }
        }
        
		
		return resultStr;
	}
	private Date date_time(String time){
		SimpleDateFormat myFmt=new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z"); 
		Date date = new Date();
		try {
			date = myFmt.parse(time);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	private String findFromIndex(String cmmd){
		String str = "";
		int i = cmmd.indexOf("index");
		cmmd = cmmd.substring(i);
		i = cmmd.indexOf(' ');
		str = cmmd.substring(0, i);
		return str;
	}
	
	private ArrayList<ArrayList<String>> getLogVal(ResultsReaderXml reader, String[] fields){
		HashMap<String, String> event;
		ArrayList<ArrayList<String>> tempstore = new ArrayList< ArrayList<String> >();
		for(int i = 0; i < fields.length; ++i){
			tempstore.add(new ArrayList<String>());
		}
		try {
			int i = 0; 
			while ((event = reader.getNextEvent()) != null) {
				for(String key: event.keySet()){
					if (key.equals(fields[0])){
						System.out.println(event.get(key));
						tempstore.get(0).add(event.get(key));
					}
					else if (key.equals(fields[1])){
						tempstore.get(1).add(event.get(key));
					}
					else if ((fields.length==3)&&key.equals(fields[2])){
						tempstore.get(2).add(event.get(key));
					}
				}
				i ++;
			}
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		return tempstore;
	}
	private String tempStoreToResult(GraphFields graph, ArrayList<ArrayList<String>> tempstore){
		String resultStr = "";
		ArrayList<String> temparr = new ArrayList<String>();
		for(int i = 0; i < tempstore.get(0).size(); ++i){
			for(int j = 0; j < tempstore.size(); ++j){
				temparr.add(tempstore.get(j).get(i));
			}
		}
		String[] arr = new String[temparr.size()];
		arr = temparr.toArray(arr);
		graph.setValue(arr);
		Gson gson = new Gson();
		resultStr=gson.toJson(graph, GraphFields.class);
		return resultStr;
	}
	
	private String smallerTime(String cmmd){
		String str="";
		int i = cmmd.indexOf(">=");
		i += 2;
		cmmd = cmmd.substring(i);
		i = cmmd.indexOf("AND");
		str = cmmd.substring(0, i);
		return str;
	}
	
	private String[] produceReqTimeCommd(String ind, Date mindate, Date maxdate, String unit){
		String[] result = null;
		ArrayList<String> tempresult = new ArrayList<String>();
		TimeZone timezone = TimeZone.getTimeZone("GMT-0:00");
		SimpleDateFormat reqfmt = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z");
		reqfmt.setTimeZone(timezone);
		if (unit.equals("h")){
			SimpleDateFormat fmt = new SimpleDateFormat("dd/MMM/yyyy:HH:00:00 Z");
			fmt.setTimeZone(timezone);
			String minAfterFmt = fmt.format(mindate);
			String maxAfterFmt = fmt.format(maxdate);
			try {
				Date mindate2 = fmt.parse(minAfterFmt);
				Calendar cmin = Calendar.getInstance();
				cmin.setTime(mindate2);
				
				Date maxdate2 = fmt.parse(maxAfterFmt);
				Calendar cmax = Calendar.getInstance();
				cmax.setTime(maxdate2);
				cmax.add(Calendar.HOUR_OF_DAY, 1);
				maxdate2 = cmax.getTime();
				while(mindate2.before(maxdate2)){
					String former = reqfmt.format(mindate2);
					cmin.add(Calendar.HOUR_OF_DAY, 1);
					mindate2 = cmin.getTime();
					String latter = reqfmt.format(mindate2);
					String reqstr = "search "+ind+" req_time>="+former+" AND "+"req_time<"+latter+"|fieldsummary ";
					tempresult.add(reqstr);
				}
				
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
		}
		else if(unit.equals("min")){
			SimpleDateFormat fmt = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:00 Z");
			fmt.setTimeZone(timezone);
			String minAfterFmt = fmt.format(mindate);
			String maxAfterFmt = fmt.format(maxdate);
			try {
				Date mindate2 = fmt.parse(minAfterFmt);
				Calendar cmin = Calendar.getInstance();
				cmin.setTime(mindate2);
				
				Date maxdate2 = fmt.parse(maxAfterFmt);
				Calendar cmax = Calendar.getInstance();
				cmax.setTime(maxdate2);
				cmax.add(Calendar.MINUTE, 1);
				maxdate2 = cmax.getTime();
				while(mindate2.before(maxdate2)){
					String former = reqfmt.format(mindate2);
					cmin.add(Calendar.MINUTE, 1);
					mindate2 = cmin.getTime();
					String latter = reqfmt.format(mindate2);
					String reqstr = "search "+ind+" req_time>="+former+" AND "+"req_time<="+latter+"|fieldsummary ";
					tempresult.add(reqstr);
				}
				
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
		}
		else if(unit.equals("sec")){
			
		}
		result = new String[tempresult.size()];
    	result = tempresult.toArray(result);
		return result;
	}
	
}
