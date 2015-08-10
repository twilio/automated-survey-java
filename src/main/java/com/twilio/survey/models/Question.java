package com.twilio.survey.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

public class Question {
	@Id
	ObjectId id;
	
	public Question(){
		id = new ObjectId();
	}
}
