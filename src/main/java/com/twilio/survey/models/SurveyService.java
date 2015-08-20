package com.twilio.survey.models;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.UpdateOperations;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.twilio.survey.Server;

public class SurveyService {
  // MongoClient and Morphia instances must be accessible to the entire object, so a Datastore can
  // be built.
  MongoClient mongoClient;
  Morphia morphia;

  // An instance of Datastore must be accessible to the entire object, so all instance methods can
  // persist to and read from the datastore.
  final Datastore datastore;

  // Constructor
  public SurveyService() {
    try {
      // Create MongoDB drivers
      mongoClient = new MongoClient();
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


  // Find, Update, and Create -- database operations.
  public Survey getSurvey(String phone) {
    try {
      Survey thisTest = datastore.find(Survey.class).field("phone").equal(phone).get();
      return thisTest;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
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
