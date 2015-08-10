package com.twilio.survey.models;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.twilio.survey.Server;

public class ModelHandler {
	MongoClient mongoClient = new MongoClient(new MongoClientURI(Server.config.MongoURL));
    Morphia morphia = new Morphia();
    morphia.mapPackage("com.twilio.survey");
    morphia.map(Person.class);
    Datastore datastore = morphia.createDatastore(mongoClient, "survey-java");
    datastore.ensureIndexes();
	public static ObjectId save(Object o) {
		
	}
}
