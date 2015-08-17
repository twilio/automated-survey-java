package com.twilio.survey.models;

public class Response {
  String phone;

  Question question;

  // At this point, we don't know what the input will be, so we'll need to leave this open to
  // interpretation. TODO: narrow this.
  Object input;


  // Constructors
  public Response(String phone, Question question, Object input) {
    this.phone = phone;
    this.input = input;
  }

  public Response() {}

  // Accessors
  public String getPhone() {
    return phone;
  }

  public Object getInput() {
    return input;
  }

}
