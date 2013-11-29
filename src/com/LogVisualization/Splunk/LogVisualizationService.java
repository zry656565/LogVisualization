package com.LogVisualization.Splunk;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.splunk.Index;
import com.splunk.IndexCollection;
import com.splunk.Job;
import com.splunk.Service;
import com.splunk.ServiceArgs;

import com.LogVisualization.SV;

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
			System.out.println(entity.getName());
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
		return originalCommand;
	}

	public String getSearchResult(String originalCommand) throws Exception {
		String command = Translate(originalCommand);

		// Create a simple search job
		String mySearch = command;
		Job job = service.getJobs().create(mySearch);

		// Wait for the job to finish
		while (!job.isDone()) {
			Thread.sleep(500);
		}

		// Display results
		InputStream results = job.getResults();
		String line = null;
		String resultStr = "Results from the search job as XML:\n";
		BufferedReader br = new BufferedReader(new InputStreamReader(results,
				"UTF-8"));
		while ((line = br.readLine()) != null) {
			resultStr += line + "\n";
		}
		br.close();
		
		return resultStr;
	}
}
