package com.twilio.survey;

import static spark.Spark.*;

import com.twilio.survey.controllers.SurveyController;


public class Server {
	// Create field to hold environment configuration.
	public static Config config;

	// Bootstrap our Java application
	public static void main(String[] args) {
		// Pull in environment variables for MongoDB and port configuration.
		config = new Config();
		
		///
		// SPARK CONFIGURATION
		///
		port(Server.config.port); // configure Spark to use the desired port. 
		
		// Map routes to controllers.
		get("/", SurveyController.index);
		get("/id/:id", SurveyController.getResponse);
		post("/new/:phone", SurveyController.respond);
	}
}
