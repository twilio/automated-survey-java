package com.twilio.survey.util;

// Represents the questions as imported from JSON by Config.java
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
