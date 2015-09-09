package com.twilio.survey.models;

import com.twilio.survey.Server;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;

public class SurveyService {
  // MongoClient and Morphia instances must be accessible to the entire object, so a Datastore can
  // be built.
  MongoClient mongoClient;
  Morphia morphia;

  // An instance of Datastore must be accessible to the entire object, so all instance methods can
  // persist to and read from the datastore.
  Datastore datastore;

  // Constructor
  public SurveyService(MongoClientURI mongoURI) {
    try {
      // Create MongoDB drivers
      mongoClient = new MongoClient(mongoURI);
      morphia = new Morphia();

      // Ask the Morphia driver to scan the Models package for models.
      morphia.mapPackage("com.twilio.survey.models");

    } catch (Exception e) {
      // Catch any MongoDB configuration errors, and pass them back to STDERR.
      System.err.println(e.getMessage());
    } finally {
      // Create a datastore with the database name provided.
      datastore = morphia.createDatastore(mongoClient, Server.config.getMongoDBName());
    }
  }
  
  public SurveyService() {
    this(Server.config.getMongoURI());
  }

  // Find, Update, and Create -- database operations.
  public Survey getSurvey(String phone) {
    return datastore.find(Survey.class).field("phone").equal(phone).get();
  }

  public void updateSurvey(Survey survey) {
    UpdateOperations<Survey> updates = datastore.createUpdateOperations(Survey.class);
    updates.set("index", survey.getIndex());
    updates.set("responses", survey.getResponses());
    updates.set("done", survey.isDone());
    datastore.update(survey, updates);
  }

  public Survey createSurvey(String phone) {
    Survey existingSurvey = getSurvey(phone);
    if (existingSurvey == null) {
      Survey survey = new Survey(phone);
      datastore.save(survey);
      return survey;
    } else {
      return existingSurvey;
    }
  }

  public List<Survey> findAllFinishedSurveys() {
    return datastore.find(Survey.class).field("done").equal(true).asList();
  }
}
