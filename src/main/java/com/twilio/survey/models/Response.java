package com.twilio.survey.models;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Response {

  // The content of Twilio's request.
  String input;


  // Constructors
  public Response(String input) {
    this.input = input;
  }

  public Response() {}

  // Accessors

  public String getInput() {
    return input;
  }

}
