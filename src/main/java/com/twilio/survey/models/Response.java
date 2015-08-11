package com.twilio.survey.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Response {
	// Use Morphia's annotations to create an object with an arbitrary ObjectId as its primary key
	@Id
	ObjectId id;
	
	// Morphia will automatically annotate the instance variables for use with MongoDB.
	String respondent;
	
	// Constructors
	public Response(String phone){
		id = new ObjectId();
		respondent = phone;
	}
	public Response(){
		id = new ObjectId();
	}
	
	// Accessors
	public String getRespondent(){
		return respondent;
	}
}
