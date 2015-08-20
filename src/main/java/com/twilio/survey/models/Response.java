package com.twilio.survey.models;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Response {

  // The content of Twilio's request.
  String answer;
  String recordingUrl;


  // Constructors
  public Response(String input) {
    if (input == null || input == "hangup") {
      throw new NullPointerException("An error occurred, because a user hung up, or did not respond");
    }
    if (input.contains("http:")) {
      this.recordingUrl = input;
    } else {
      this.answer = input;
    }
  }

  public Response() {}

  // Accessors

  public String getAnswer() {
    return answer;
  }
  
  public void setAnswer(String input) {
    this.answer = input;
  }
  
  public String getRecordingUrl() {
    return recordingUrl;
  }
  
  

}
