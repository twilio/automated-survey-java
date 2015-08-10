package com.twilio.survey.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Response {
	@Id
	ObjectId id;
	String respondent;
	
	public Response(String phone){
		id = new ObjectId();
		respondent = phone;
	}
	public Response(){
		id = new ObjectId();
	}
	public String getRespondent(){
		return respondent;
	}
}
