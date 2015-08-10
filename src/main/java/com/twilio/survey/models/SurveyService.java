package com.twilio.survey.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.twilio.survey.Server;

public class SurveyService {
	Datastore datastore;
	Morphia morphia;
	MongoClient mongoClient;
	public SurveyService(){
		try {
			mongoClient = new MongoClient(Server.config.mongoURI);
			morphia = new Morphia();
			// TODO: figure out which of these is necessary
			
		    morphia.mapPackage("com.twilio.survey");
		    morphia.map(Response.class);
		    
		    // TODO: make sure the name of the datastore matches whatever you called it in MongoLab...
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			datastore = morphia.createDatastore(mongoClient, "survey-java");
		}
	}
	public Object saveResponse(Response r){
		return datastore.save(r).getId();
	}
	public Object getResponse(ObjectId id) {
		return datastore.get(Response.class, id);
	}
}
