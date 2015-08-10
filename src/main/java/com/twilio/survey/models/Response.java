package com.twilio.survey.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

public class Response {
	@Id
	ObjectId id;
	
	public Response(){
		id = new ObjectId();
	}
}
