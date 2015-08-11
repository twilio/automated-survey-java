package com.twilio.survey.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import com.mongodb.MongoClient;

import com.twilio.survey.Server;

public class SurveyService {	
	// MongoClient and Morphia instances must be accessible to the entire object, so a Datastore can be built.
	MongoClient mongoClient;
	Morphia morphia; 
	
	// An instance of Datastore must be accessible to the entire object, so all instance methods can persist to and read from the datastore.
	Datastore datastore;
	
	// Constructor
	public SurveyService(){
		try {
			// Create MongoDB drivers
			mongoClient = new MongoClient(Server.config.mongoURI);
			morphia = new Morphia();
			
			// Ask the Morphia driver to scan the Models package for models.
		    morphia.mapPackage("com.twilio.survey.models");
		    
		} catch (Exception e) {
			// Catch any MongoDB configuration errors, and pass them back to STDERR.
			System.err.println(e.getMessage());
		} finally {
			// TODO: Remember to make sure the name of the datastore matches the MongoLab datastore name.
			datastore = morphia.createDatastore(mongoClient, "survey-java");
		}
	}
	
	// Demo method: persist a Response object to the DB
	public Object saveResponse(Response res){
		return datastore.save(res).getId();
	}
	// Demo method: retrieve a Response object from the DB
	public Response getResponse(String id) {
		// Transforming the String to an ObjectId here keeps the DB logic in the Model.
		ObjectId objId = new ObjectId(id); 
		
		// Retrieve the object by its ID.
		return datastore.get(Response.class, objId);
	}
}
