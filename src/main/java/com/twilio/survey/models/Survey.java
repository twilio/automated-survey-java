package com.twilio.survey.models;

import com.twilio.survey.Server;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Survey {
  @Id
  private ObjectId id;

  private String phone;

  private boolean done;

  private int index;

  @Embedded
  private Response[] responses;

  // Constructors
  public Survey(String phone) {
    this.responses = new Response[Server.config.getQuestions().length];
    this.done = false;
    this.phone = phone;
    this.index = 0;
  }
  
  public Survey(Survey anotherSurvey) {
    System.arraycopy(anotherSurvey.responses, 0, this.responses, 0, anotherSurvey.responses.length);
    this.id = anotherSurvey.id;
    this.phone = anotherSurvey.phone;
    this.index = anotherSurvey.index;
    this.done = anotherSurvey.done;
  }
  
  public Survey() {
    this("+0000000000");
  }

  // Accessors
  public ObjectId getId() {
    return id;
  }

  public String getPhone() {
    return phone;
  }

  public Response[] getResponses() {
    return responses;
  }

  public boolean isDone() {
    if (index > Server.config.getQuestions().length - 1) {
      this.markDone();
    }
    return done;

  }

  public int getIndex() {
    return index;
  }

  // Mutators
  public void appendResponse(Response response) {
    if (!this.isDone()) {
      this.responses[index++] = response;
    }
  }

  public void markDone() {
    this.done = true;
  }
}
