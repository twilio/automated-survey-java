package com.twilio.survey;

import static spark.Spark.*;

import com.twilio.survey.controllers.SurveyController;
import com.twilio.survey.Config;

public class Server {
  // Create field to hold environment configuration.
  public static Config config;

  // Bootstrap our application
  public static void main(String[] args) {
    // Pull in environment variables for MongoDB and port configuration.
    config = new Config();

    // configure Spark to use the desired port.
    port(Server.config.port);

    // Map routes to controllers.
    get("/", SurveyController.index);
    post("/interview", SurveyController.interview);
  }
}
