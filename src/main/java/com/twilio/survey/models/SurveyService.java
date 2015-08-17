package com.twilio.survey.models;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.UpdateOperations;

import com.mongodb.MongoClient;
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

  // Find, Update, and Create -- database operations.
  public Survey getSurvey(String phone) {
    return datastore.find(Survey.class).field("phone").equal(phone).field("done").equal(false)
        .get();
  }

  public void updateSurvey(Survey survey) {
    UpdateOperations<Survey> updates =
        datastore.createUpdateOperations(Survey.class).set("done", survey.isDone())
            .set("responses", survey.getResponses());
    datastore.update(survey, updates);
  }

  public Survey createSurvey(String phone) {
    Survey survey = new Survey(phone);
    datastore.save(survey);
    return survey;
  }
}
