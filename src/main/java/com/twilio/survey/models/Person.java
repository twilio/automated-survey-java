package com.twilio.survey.models;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
@Entity("people")
@Indexes(
	@Index(value = "salary", fields = @Field("salary"))
)
public class Person {
	@Id
	private ObjectId id;
	public String name;
	@Reference
	private Person manager;
	@Reference
	private List<Person> directReports;
	@Property("wage")
	private Double salary;
	
	public Person(String name){
		this.name = name;
	}
	
	public Person(){};
}
