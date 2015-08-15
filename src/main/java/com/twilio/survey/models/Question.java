package com.twilio.survey.models;

public class Question {
  private String type;
  private String text;

  public Question(String type, String text) {
    this.type = type;
    this.text = text;
  }

  public String getType() {
    return this.type;
  }

  public String getText() {
    return this.text;
  }
}
