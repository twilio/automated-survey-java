package com.twilio.survey;

import static spark.Spark.*;

import com.twilio.survey.controllers.SurveyController;
import com.twilio.survey.util.Config;

public class Server {
  // Create field to hold environment configuration.
  public static Config config;

  // Bootstrap our application
  public static void main(String[] args) {
    // Pull in environment variables for MongoDB and port configuration.
    config = new Config();

    // configure Spark to use the desired port.
    port(Server.config.getPort());

    // Map routes to controllers.
    staticFileLocation("/public");
    post("/interview", SurveyController.interview);
    post("/interview/:phone/transcribe/:question", SurveyController.transcribe);
    get("/results", SurveyController.results);
    
  }
}
