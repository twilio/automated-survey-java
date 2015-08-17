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
  public Survey() {
    this.phone = null;
    this.responses = new Response[Server.config.questions.length];
    this.done = false;
    this.index = 0;
  }

  public Survey(String phone) {
    this.responses = new Response[Server.config.questions.length];
    this.done = false;
    this.phone = phone;
    this.index = 0;
  }

  // Accessors
  public Object getId() {
    return id;
  }

  public String getPhone() {
    return phone;
  }

  public Response[] getResponses() {
    return responses;
  }

  public boolean isDone() {
    return done;
  }
  
  public int nextOpenQuestion() {
    return index;
  }

  // Mutators
  public void appendResponse(Response response) {
    this.responses[index++] = response;
    if (index > Server.config.questions.length) {
      this.markDone();
    }
  }

  public void markDone() {
    this.done = true;
  }
}
