package com.twilio.survey;
import java.util.Map;

import com.mongodb.MongoClientURI;


public class Config {
	public int port;
	public MongoClientURI mongoURI;
	
	public Config(){
		Map<String, String> env = System.getenv();
		  
		// Set defaults, and override with environment variables, if present.
		port = 4567; // default to port 4567, the idiomatic Spark port.
		String envMongoURL = ""; // default to localhost, the idiomatic MongoDB host. The driver will use the idiomatic MongoDB port by default.
		if (env.containsKey("PORT")) {
			port = Integer.valueOf(env.get("PORT"));
		}
		if (env.containsKey("MONGOLAB_URI")) { // for production.
			envMongoURL = env.get("MONGOLAB_URI");
		} 
		else if (env.containsKey("MONGO_URL")) { // for test.
			envMongoURL = env.get("MONGO_URL");
		}
		mongoURI = new MongoClientURI(envMongoURL);
	}
}
