package com.LogVisualization.CustomLog;

import java.net.UnknownHostException;
import java.util.Map;

import com.LogVisualization.Utils.SV;
import com.mongodb.BasicDBObject;    
import com.mongodb.DB;    
import com.mongodb.DBCollection;    
import com.mongodb.DBCursor;    
import com.mongodb.MongoClient;  
import com.mongodb.MongoException;

public class CustomLogService {
	private DB db;
	
	public CustomLogService(){
		MongoClient mongoclient;
		try {
			mongoclient = new MongoClient(SV.MONGODB_HOST, SV.MONGODB_PORT);
			db = mongoclient.getDB(SV.MONGODB_DBNAME);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public int Import(String cName, String filepath,
			String[] fieldsName, String timeformat){
		DBCollection collection = db.getCollection(cName);
		
		//1. Read the file
		
		//2. translate a line to a BasicDBObject and save into mongoDB
		//e.g. fieldsName = [name, job, timestamp]
		//     John student 1992/1/1
		//     
		//     BasicDBObject person = new BasicDBObject();
		//     person.put("name", "John");
		//     person.put("job", "student");
		//     person.put("timestamp", 1992/1/1);
		//     collection.insert(person);
		
		//3. Save timeformat to MySQL or add a redundant field (maybe timeformat)
		
		return 0;
	}
	
	//return the number of searched results
	public int Search(String cName, Map<String, Object> conditions){
		return 0;
	}
	
	
}
