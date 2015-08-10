package com.twilio.survey;

import static spark.Spark.*;

import java.util.Map;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import org.mongodb.morphia.*;
import org.mongodb.morphia.query.Query;

import com.twilio.survey.controllers.MessageController;
import com.twilio.survey.controllers.PersonController;
import com.twilio.survey.models.Person;

public class Server {

  public static Object config;

// Bootstrap our Java application
  public static void main(String[] args) {
      // Configure MongoDB and Morphia
	  Map<String, String> env = System.getenv();
	  
	  // Set defaults, and override with environment variables, if present.
	  Integer envPort = 3000;
	  String envMongoURL = "mongodb://brandon:avonmarie@ds029793.mongolab.com:29793/survey-java";
	  MongoClientURI url = new MongoClientURI(envMongoURL);
	  if (env.containsKey("PORT")) {
		  envPort = Integer.valueOf(env.get("PORT"));
	  }
	  if (env.containsKey("MONGOLAB_URI")) { // for production.
		  envMongoURL = env.get("MONGOLAB_URI");
	  } 
	  else if (env.containsKey("MONGO_URL")) { // TODO: for test.
		  envMongoURL = env.get("MONGO_URL");
	  }
	
	port(envPort);  
	MongoClient mongoClient = new MongoClient(new MongoClientURI(envMongoURL));
    Morphia morphia = new Morphia();
    morphia.mapPackage("com.twilio.survey");
    morphia.map(Person.class);
    Datastore datastore = morphia.createDatastore(mongoClient, "survey-java");
    datastore.ensureIndexes();
	
	// Define route handlers
    post("/", (req, res) -> {
    	return "not quite right! try /name/:name";
    });
    //post("/name/:name", PersonController.createPerson);
    get("/name/:name", (req, res) ->{
    	Query<Person> who = datastore.find(Person.class, "name", req.params(":name"));
    	return "Hi, " + who.get().name;
    });
  }
}
