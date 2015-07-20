package com.twilio.kitchensink;

import static spark.Spark.get;

import com.twilio.kitchensink.controllers.PersonController;

public class Server {

  // Bootstrap our Java application
  public static void main(String[] args) {
    // Configure MongoDB and Morphia
    
    
    // Define route handlers
    get("/", PersonController.sayHello);
  }
}
