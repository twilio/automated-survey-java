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
    } else if(input.equals("0")) {
      this.answer = "false";
    } else {
      this.answer = input;
    }
  }

  public Response() {
    this(null);
  }

  // Accessors

  public Object getAnswer() {
    return answer;
  }
  
  public void setAnswer(String input) {
    this.answer = input;
  }
  
  public String getRecordingUrl() {
    return recordingUrl;
  }
  
  

}
