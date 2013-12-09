package com.LogVisualization.CustomLog;    
    
import java.net.UnknownHostException;    
    
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor; 
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

import com.LogVisualization.Utils.SV;
    
public class MongoDBTest {    
    
 public static void main(String[] args) {    
	 try {    
		 MongoClient mongoclient = new MongoClient(SV.MONGODB_HOST, SV.MONGODB_PORT);    
		 DB db = mongoclient.getDB(SV.MONGODB_DBNAME);    
		 
		 DBCollection collection = db.getCollection("employees");    
		 BasicDBObject employee = new BasicDBObject();
		 employee.put("name", "Hannah");
		 employee.put("no", 2);
		 
		 BasicDBObject employee2 = new BasicDBObject();
		 employee2.put("name", "Hannah");
		 employee2.put("no", 3);
		 
		 collection.insert(employee);
		 collection.insert(employee2);

		 BasicDBObject searchEmployee = new BasicDBObject();
		 searchEmployee.put("no", new BasicDBObject("$gt", 1));
		 
		 DBCursor cursor = collection.find(searchEmployee);    

		 while (cursor.hasNext()) {    
			 System.out.println(cursor.next());    
		 }
		 
		 System.out.println("Count:" + collection.count(searchEmployee)); 
		 
		 System.out.println("The Search Query has Executed!");    
		 
	 } catch (UnknownHostException e) {    
		 e.printStackTrace();    
	 } catch (MongoException e) {    
		 e.printStackTrace();
	 }    
  }    
}   